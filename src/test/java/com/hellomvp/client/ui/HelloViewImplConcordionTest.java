package com.hellomvp.client.ui;

import net.tanoshi.test.junit.JruJunitRunner;
import net.tanoshi.test.junit.annotation.JruRunWith;
import net.tanoshi.test.junit.annotation.JruRunnerFactoryDelegate;
import net.tanoshi.test.junit.factory.JruGwtRunnerFactory;
import net.tanoshi.test.junit.runner.JruConcordionRunner;
import net.tanoshi.test.junit.runner.JruGwtTestUtilRunner;
import net.tanoshi.test.junit.runner.JruMockitoRunner;

import org.junit.runner.RunWith;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

@RunWith(JruJunitRunner.class)
@JruRunWith({ JruMockitoRunner.class, JruGwtTestUtilRunner.class, JruConcordionRunner.class })
@JruRunnerFactoryDelegate(JruGwtRunnerFactory.class)
@GwtModule("com.hellomvp.HelloMVP")
public class HelloViewImplConcordionTest extends GwtTest {

    public String getGreeting(String name) {
        HelloViewImpl view = new HelloViewImpl();

        view.setName(name);

        return "Hello, " + view.nameSpan.getInnerText();
    }
    
}
