package com.hellomvp.client.ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.gwt.event.dom.client.ClickEvent;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.utils.GwtReflectionUtils;
import com.googlecode.gwt.test.utils.events.Browser;
import com.hellomvp.client.place.GoodbyePlace;
import com.hellomvp.client.ui.HelloView.Presenter;

@GwtModule("com.hellomvp.HelloMVP")
public class HelloViewImplMockitoTest extends GwtTestWithMockito {

	@Test
	public void testSetName() {
		HelloViewImpl view = new HelloViewImpl();

		// SpanElement nameSpan = PowerMockito.spy(view.nameSpan);

		String name = UUID.randomUUID().toString();

		view.setName(name);

		// hopefully gwt-test-util will support power mock... then we can verify
		// setInnerText was called and name was saved.
		// verify(nameSpan).setInnerText(eq(name)); // <-- final method :(
		// assertEquals(name, Whitebox.getInternalState(view, "name"));

		// gwt-test-util way.
		assertEquals(name,
				GwtReflectionUtils.getPrivateFieldValue(view, "name"));
		assertEquals(name, view.nameSpan.getInnerText());

	}

	@Test
	public void testPresenter() {
		Presenter presenter = mock(Presenter.class);

		HelloViewImpl view = new HelloViewImpl();
		view.setPresenter(presenter);

		// hopefully gwt-test-util will support power mock... then we can verify
		// name was saved.
		// assertEquals(presenter, Whitebox.getInternalState(view, "listener"));
		
		assertEquals(presenter, GwtReflectionUtils.getPrivateFieldValue(view, "listener"));
		
	}

	@Test
	public void testClickGoodbye() {
		ClickEvent event = mock(ClickEvent.class);
		Presenter presenter = mock(Presenter.class);

		final String name = UUID.randomUUID().toString();

		// make sure the param is correct... seems a little hacky to use Answer
		// but it works.
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GoodbyePlace place = (GoodbyePlace) invocation.getArguments()[0];
				assertEquals(name, place.getGoodbyeName());

				return null;
			}

		}).when(presenter).goTo(any(GoodbyePlace.class));

		HelloViewImpl view = new HelloViewImpl();
		view.setPresenter(presenter);
		view.setName(name);
		view.onClickGoodbye(event);

		// doesn't do any good testing name if goTo didn't get called.
		verify(presenter).goTo(any(GoodbyePlace.class));

	}

	@Test
	public void testGoodbyeClick() {
		Presenter presenter = mock(Presenter.class);

		final String name = UUID.randomUUID().toString();

		// make sure the param is correct... seems a little hacky to use Answer
		// but it works.
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GoodbyePlace place = (GoodbyePlace) invocation.getArguments()[0];
				assertEquals(name, place.getGoodbyeName());

				return null;
			}

		}).when(presenter).goTo(any(GoodbyePlace.class));

		HelloViewImpl view = new HelloViewImpl();
		view.setPresenter(presenter);
		view.setName(name);

		Browser.click(view.goodbyeLink);
		// almost the same test as he onClick but making sure goodbyeLink is
		// connected properly.
		// view.onClickGoodbye(event);

		// doesn't do any good testing name if goTo didn't get called.
		verify(presenter).goTo(any(GoodbyePlace.class));
	}
}
