package com.hellomvp.client.ui;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;

@GwtModule("com.hellomvp.HelloMVP")
public class GoodbyeViewImplMockitoTest extends GwtTestWithMockito {

	public void testInitialize() {
		// no way to test constructor... should fix code.
	}

	@Test
	public void testSetName() {
		String name = UUID.randomUUID().toString();

		GoodbyeViewImpl view = new GoodbyeViewImpl();
		view.setName(name);

		// final method so it doesn't actually test... PowerMock... hopefully in
		// future.
		// verify(nameSpan).setInnerText(eq("Good-bye, " + name));
		
		// we can check if get inner text returns the same value.
		assertEquals("Good-bye, " + name, view.nameSpan.getInnerText());
	}

}
