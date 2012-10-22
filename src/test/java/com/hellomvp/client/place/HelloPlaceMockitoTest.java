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
import com.hellomvp.client.place.HelloPlace.Tokenizer;

@RunWith(JruJunitRunner.class)
@JruRunWith({JruMockitoRunner.class, JruGwtTestUtilRunner.class})
@JruRunnerFactoryDelegate(JruGwtRunnerFactory.class)
@GwtModule("com.hellomvp.HelloMVP")
public class HelloPlaceMockitoTest extends GwtTest{
	
	@Test
	public void testInitializationOfHelloPlace() {
		String token = UUID.randomUUID().toString();
		
		HelloPlace place = new HelloPlace(token);
		
		assertEquals(token, GwtReflectionUtils.getPrivateFieldValue(place, "helloName"));
		assertEquals(token, place.getHelloName());
	}
	
	@Test
	public void testTokenizerGetToken() {
		String token = UUID.randomUUID().toString();
		
		Tokenizer tokenizer = new Tokenizer();
		
		HelloPlace place = mock(HelloPlace.class);
		when(place.getHelloName()).thenReturn(token);
		
		assertEquals(token, tokenizer.getToken(place));
		verify(place).getHelloName();
	}
	
	@Test
	public void testTokenizerGetPlace() {
		String token = UUID.randomUUID().toString();
		
		Tokenizer tokenizer = new Tokenizer();
		
		assertEquals(token, tokenizer.getPlace(token).getHelloName());
	}
	

}
