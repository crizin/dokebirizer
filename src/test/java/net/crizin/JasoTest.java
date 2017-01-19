package net.crizin;

import org.junit.Test;

import static org.junit.Assert.*;

public class JasoTest {
	@Test
	public void test() {
		assertTrue(Jaso.isValid('얍'));
		assertTrue(Jaso.isValid('ㅎ'));
		assertFalse(Jaso.isValid('A'));
		assertFalse(Jaso.isValid(' '));

		Jaso jaso = new Jaso('얍');
		assertEquals("얍", jaso.toString());
		assertEquals(Jaso.Chosung.ᄋ, jaso.getChosung());
		assertEquals(Jaso.Jungsung.ㅑ, jaso.getJungsung());
		assertEquals(Jaso.Jongsung.ᆸ, jaso.getJongsung());

		jaso = new Jaso('하');
		assertEquals("하", jaso.toString());
		assertEquals(Jaso.Chosung.ᄒ, jaso.getChosung());
		assertEquals(Jaso.Jungsung.ㅏ, jaso.getJungsung());
		assertEquals(Jaso.Jongsung.None, jaso.getJongsung());

		jaso = new Jaso('강');

		assertEquals(Jaso.Chosung.ᄀ, jaso.setChosung(Jaso.Chosung.ᄉ));
		assertEquals("상", jaso.toString());

		assertEquals(Jaso.Jungsung.ㅏ, jaso.setJungsung(Jaso.Jungsung.ㅗ));
		assertEquals("송", jaso.toString());

		assertEquals(Jaso.Jongsung.ᆼ, jaso.setJongsung(Jaso.Jongsung.ᆷ));
		assertEquals("솜", jaso.toString());

		assertEquals(Jaso.Jongsung.ᆷ, jaso.setJongsung(Jaso.Jongsung.None));
		assertEquals("소", jaso.toString());
	}
}