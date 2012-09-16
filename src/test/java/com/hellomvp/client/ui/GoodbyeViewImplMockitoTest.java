package com.hellomvp.client.ui;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.Test;

import com.google.gwt.user.client.Element;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;

@GwtModule("com.hellomvp.HelloMVP")
public class GoodbyeViewImplMockitoTest extends GwtTestWithMockito {

	public void testInitialize() {
		// no way to test constructor... should fix code.
	}

	@Test
	public void testSetName() {
		GoodbyeViewImpl view = new GoodbyeViewImpl();
		Element nameSpan = spy(view.nameSpan);

		String name = UUID.randomUUID().toString();

		view.setName(name);

		// final method so it doesn't actually test... PowerMock... hopefully in
		// future.
		// verify(nameSpan).setInnerText(eq("Good-bye, " + name));
	}

}
