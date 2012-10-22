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
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import com.hellomvp.client.ClientFactory;
import com.hellomvp.client.place.HelloPlace;
import com.hellomvp.client.ui.HelloView;

@RunWith(JruJunitRunner.class)
@JruRunWith({JruMockitoRunner.class, JruGwtTestUtilRunner.class})
@JruRunnerFactoryDelegate(JruGwtRunnerFactory.class)
@GwtModule("com.hellomvp.HelloMVP")
public class HelloActivityMockitoTest extends GwtTest {

	@Mock
	public HelloPlace place;
	public String name;

	@Mock
	public ClientFactory factory;
	@Mock
	public HelloView helloView;
	@Mock
	public Widget helloWidget;
	@Mock
	public PlaceController placeController;

	@Mock
	public AcceptsOneWidget containerWidget;
	@Mock
	public EventBus eventBus;

	/**
	 * Helper class to setup the mock object. Makes it easier to make changes
	 * for other tests.
	 */
	public class TestMocks {

		public TestMocks setupMock() {
			setupHelloPlace();
			setupClientFactory();

			return this;
		}

		public void setupHelloPlace() {
			name = UUID.randomUUID().toString();

			when(place.getHelloName()).thenReturn(name);
		}

		public void setupClientFactory() {
			// clientFactory.getHelloView();
			setupHelloView();
			when(factory.getHelloView()).thenReturn(helloView);

			// clientFactory.getPlaceController().goTo(place);
			when(factory.getPlaceController()).thenReturn(placeController);
		}

		public void setupHelloView() {
			when(helloView.asWidget()).thenReturn(helloWidget);
		}
	}

	@Test
	public void testInitialization() {
		new TestMocks().setupMock();

		HelloActivity activity = new HelloActivity(place, factory);
		activity.start(containerWidget, eventBus);

		verify(factory).getHelloView();
		verify(helloView).setName(eq(name));
		verify(helloView).setPresenter(eq(activity));
		verify(containerWidget).setWidget(eq(helloWidget));
	}

	@Test
	public void testMayStop() {
		new TestMocks().setupMock();

		HelloActivity activity = new HelloActivity(place, factory);
		activity.start(containerWidget, eventBus);

		activity.mayStop();
	}

	@Test
	public void testGoTo() {
		new TestMocks().setupMock();

		HelloActivity activity = new HelloActivity(place, factory);
		activity.start(containerWidget, eventBus);

		Place place = mock(Place.class);
		activity.goTo(place);

		verify(placeController).goTo(eq(place));

	}

}
