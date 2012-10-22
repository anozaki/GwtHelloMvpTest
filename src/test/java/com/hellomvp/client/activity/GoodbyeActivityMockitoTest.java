package com.hellomvp.client.activity;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import net.tanoshi.test.junit.JruJunitRunner;
import net.tanoshi.test.junit.annotation.JruRunWith;
import net.tanoshi.test.junit.annotation.JruRunnerFactoryDelegate;
import net.tanoshi.test.junit.factory.JruGwtRunnerFactory;
import net.tanoshi.test.junit.runner.JruGwtTestUtilRunner;
import net.tanoshi.test.junit.runner.JruMockitoRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import com.hellomvp.client.ClientFactory;
import com.hellomvp.client.place.GoodbyePlace;
import com.hellomvp.client.ui.GoodbyeView;

@RunWith(JruJunitRunner.class)
@JruRunWith({JruMockitoRunner.class, JruGwtTestUtilRunner.class})
@JruRunnerFactoryDelegate(JruGwtRunnerFactory.class)
@GwtModule("com.hellomvp.HelloMVP")
public class GoodbyeActivityMockitoTest extends GwtTest {
	@Mock
	GoodbyePlace place;

	@Mock
	ClientFactory factory;

	@Mock
	AcceptsOneWidget panel;

	@Mock
	GoodbyeView view;
	
	@Mock
	Widget widget;
	
	String testName;

	/**
	 * help setup the mocks. Extend this if you want something to do different
	 * things.
	 */
	public class TestMocks {

		public TestMocks setupMock() {
			setupPlace();
			setupView();

			return this;
		}
		
		public void setupPlace() {
			testName = UUID.randomUUID().toString();
			when(place.getGoodbyeName()).thenReturn(testName);
		}

		public void setupView() {
			when(factory.getGoodbyeView()).thenReturn(view);
			
			when(view.asWidget()).thenReturn(widget);
		}

	}
	
	@Test
	public void testInitialization() {
		new TestMocks().setupMock();

		GoodbyeActivity activity = new GoodbyeActivity(place, factory);
		activity.start(panel, mock(EventBus.class)); // <-- testing

		// make sure a view was created.
		verify(factory).getGoodbyeView();

		// make sure name was set from the place.
		verify(view).setName(eq(testName));

		// verify set widget was called for the view.
		verify(panel).setWidget(eq(widget));
	}

}
