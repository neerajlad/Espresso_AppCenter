package com.simplemobiletools.calculator.core.control;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;

import com.simplemobiletools.calculator.R;
import com.simplemobiletools.calculator.core.pages.IPage;
import com.simplemobiletools.calculator.core.loader.Identifier;
import com.simplemobiletools.calculator.core.javautils.Platform;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public abstract class Control implements IControl {

    private ViewInteraction element;

    public IPage getPage() {
        return page;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPage(IPage page) {
        this.page = page;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    private IPage page = null;
    private Platform platform = null;


    public Control(IPage page,ViewInteraction element) {
        this.setPage(page);
        this.setPlatform(page.getPlatform());
        this.setElement(element);
    }

    public static IControl createControl(IPage page,String name, Identifier by) throws Exception {
        try {
            return createMobileElement(page, name, by);

        } catch (Exception e) {
            throw e;
        }
    }

    private static IControl createMobileElement(IPage page, String name, Identifier by) throws Exception {
        return new MobileControl(page, identify(name, by));
    }

    private static ViewInteraction identify(String name, Identifier by) throws Exception {
        ViewInteraction element = null;
        String elementValue = by.getValue();
        final Context context = InstrumentationRegistry.getTargetContext();
        switch (by.getIdType()) {
            case WITHID:
                element = onView(withId(parseResourceId(context, elementValue)));
                break;
            case WITHTAG:
                int tagKey = Integer.parseInt(elementValue);
                element = onView(withTagKey(tagKey));
                break;
            case WITHTEXT:
                element = onView(withText(by.getValue()));
                break;
            case WITHDESCRIPTION:
                element = onView(withContentDescription(by.getValue()));
                break;

            default:
                throw new Exception("Wrong identifier passed. Identifier Type : " + by.getIdType());
        }
        return element;
    }

    private static int parseResourceId(@NonNull Context context, @NonNull String string) {
        return context.getResources().getIdentifier(string, "id", context.getPackageName());
    }
    @Override
    public void press() {
        element.perform(click());
    }

    @Override
    public void longPress() {
        element.perform(longClick());
    }

    @Override
    public void enterText(String text) {
        element.perform(typeText(text));
    }

    @Override
    public void goBack() {
    element.perform(pressBack());
    }

    @Override
    public void closeKeypad() {
        element.perform(closeSoftKeyboard());
    }

    @Override
    public IControl getControl(String name) {
     //   onView(withId(R.id.result)).check(matches(withText(desired)));
        Identifier id = getPage().getIdentifier(name);
        return null;
    }

    public ViewInteraction getElement() {
        return element;
    }

    public void setElement(ViewInteraction element) {
        this.element = element;
    }
}
