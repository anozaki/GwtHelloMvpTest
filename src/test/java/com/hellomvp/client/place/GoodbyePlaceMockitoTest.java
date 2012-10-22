package com.hellomvp.client.place;

import static org.junit.Assert.assertEquals;
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

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import com.googlecode.gwt.test.utils.GwtReflectionUtils;
import com.hellomvp.client.place.GoodbyePlace.Tokenizer;

@RunWith(JruJunitRunner.class)
@JruRunWith({JruMockitoRunner.class, JruGwtTestUtilRunner.class})
@JruRunnerFactoryDelegate(JruGwtRunnerFactory.class)
@GwtModule("com.hellomvp.HelloMVP")
public class GoodbyePlaceMockitoTest extends GwtTest {
	
	@Test
	public void testInitializationOfGoodbyePlace() {
		String token = UUID.randomUUID().toString();
		
		GoodbyePlace place = new GoodbyePlace(token);
		
		assertEquals(token, GwtReflectionUtils.getPrivateFieldValue(place, "goodbyeName"));
		assertEquals(token, place.getGoodbyeName());
	}
	
	@Test
	public void testTokenizerGetToken() {
		String token = UUID.randomUUID().toString();
		
		Tokenizer tokenizer = new Tokenizer();
		
		GoodbyePlace place = mock(GoodbyePlace.class);
		when(place.getGoodbyeName()).thenReturn(token);
		
		assertEquals(token, tokenizer.getToken(place));
		verify(place).getGoodbyeName();
	}
	
	@Test
	public void testTokenizerGetPlace() {
		String token = UUID.randomUUID().toString();
		
		Tokenizer tokenizer = new Tokenizer();
		
		assertEquals(token, tokenizer.getPlace(token).getGoodbyeName());
	}
	
}
