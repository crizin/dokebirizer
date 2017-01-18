package net.crizin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DokebirizerTest {
	@Test
	public void test() {
		Dokebirizer dokebirizer = new Dokebirizer();
		assertEquals("아반녀벙하바세베요보.", dokebirizer.encode("안녕하세요."));
		assertEquals("안녕하세요.", dokebirizer.decode("아반녀벙하바세베요보."));

		dokebirizer = new Dokebirizer(Dokebirizer.Policy.Copy, Jaso.Chosung.ㅂ);
		assertEquals("아반녀벙하바세베요보.", dokebirizer.encode("안녕하세요."));
		assertEquals("안녕하세요.", dokebirizer.decode("아반녀벙하바세베요보."));

		dokebirizer = new Dokebirizer(Dokebirizer.Policy.Transform, Jaso.Chosung.ㅂ);
		assertEquals("아반녀벙하바세베요보.", dokebirizer.encode("안녕하세요."));
		assertEquals("안녕하세요.", dokebirizer.decode("아반녀벙하바세베요보."));
	}
}