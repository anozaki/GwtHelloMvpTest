package com.hellomvp.client.ui;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import net.tanoshi.test.junit.JruJunitRunner;
import net.tanoshi.test.junit.annotation.JruRunWith;
import net.tanoshi.test.junit.annotation.JruRunnerFactoryDelegate;
import net.tanoshi.test.junit.factory.JruGwtRunnerFactory;
import net.tanoshi.test.junit.runner.JruGwtTestUtilRunner;
import net.tanoshi.test.junit.runner.JruMockitoRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

@RunWith(JruJunitRunner.class)
@JruRunWith({JruMockitoRunner.class, JruGwtTestUtilRunner.class})
@JruRunnerFactoryDelegate(JruGwtRunnerFactory.class)
@GwtModule("com.hellomvp.HelloMVP")
public class JruGwtTest extends GwtTest {
    
    @Test
    public void testSetName() {
        String name = UUID.randomUUID().toString();

        GoodbyeViewImpl view = new GoodbyeViewImpl();
        view.setName(name);

        // we can check if get inner text returns the same value.
        assertEquals("Good-bye, " + name, view.nameSpan.getInnerText());
    }
}
