package net.crizin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DokebirizerTest {
	@Test
	public void test() {
		Dokebirizer dokebirizer = new Dokebirizer();

		String original = "한국어의 언어유희의 일종이다. 구어체에서 주로 사용하는데, 언어는 알려져 있어도 말할 때의 불편함 때문에 사용하지 않는 학생들이 대부분이라 알아듣는 학생들만이 알아듣는다. 처음에는 무슨 말인가 싶어 하다가도 듣고 말하는 데 익숙해지면 금방 알아들을 수 있으니 너무 걱정하지는 말자.";
		String dokebirized = "하반구북어버으비 어번어버유부흐비으비 이빌조봉이비다바. 구부어버체베에베서버 주부로보 사바요봉하바느븐데베, 어번어버느븐 아발려버져버 이빘어버도보 마발하발 때배으비 부불펴번하밤 때배무분에베 사바요봉하바지비 아밚느븐 하박새뱅드블이비 대배부부부분이비라바 아발아바드븓느븐 하박새뱅드블마반이비 아발아바드븓느븐다바. 처버으븜에베느븐 무부스븐 마발이빈가바 시빞어버 하바다바가바도보 드븓고보 마발하바느븐 데베 이빅수북해배지비며번 그븜바방 아발아바드블으블 수부 이빘으브니비 너버무부 거벅저벙하바지비느븐 마발자바.";
		assertEquals(dokebirized, dokebirizer.encode(original));
		assertEquals(original, dokebirizer.decode(dokebirized));

		dokebirizer = new Dokebirizer(Dokebirizer.Policy.Split, Jaso.Chosung.ᄇ);
		assertEquals("오비우비우베오배우버으비", dokebirizer.encode("외위웨왜워의"));
		assertEquals("오비하발머버니비르블 보비러버 가밨다바", dokebirizer.encode("외할머니를 뵈러 갔다"));
		assertEquals("외할머니를 뵈러 갔다", dokebirizer.decode("오비하발머버니비르블 보비러버 가밨다바"));

		dokebirizer = new Dokebirizer(Dokebirizer.Policy.Copy, Jaso.Chosung.ᄇ);
		assertEquals("외뵈위뷔웨붸왜봬워붜의븨", dokebirizer.encode("외위웨왜워의"));
		assertEquals("외뵈하발머버니비르블 뵈뵈러버 가밨다바", dokebirizer.encode("외할머니를 뵈러 갔다"));
		assertEquals("외할머니를 뵈러 갔다", dokebirizer.decode("외뵈하발머버니비르블 뵈뵈러버 가밨다바"));

		dokebirizer = new Dokebirizer(Dokebirizer.Policy.Transform, Jaso.Chosung.ᄇ);
		assertEquals("외베위비웨베왜배워버의비", dokebirizer.encode("외위웨왜워의"));
		assertEquals("외베하발머버니비르블 뵈베러버 가밨다바", dokebirizer.encode("외할머니를 뵈러 갔다"));
		assertEquals("외할머니를 뵈러 갔다", dokebirizer.decode("외베하발머버니비르블 뵈베러버 가밨다바"));

		dokebirizer = new Dokebirizer(Dokebirizer.Policy.Split, Jaso.Chosung.ᄉ);
		original = "귀신말은 도깨비말에서 비읍을 시옷으로 변형해서 사용하는 말이다. 두번째 글자의 자음이 ㅂ에서 ㅅ으로 변하는 것을 제외하고는 도깨비말의 사용방법을 그대로 사용할 수 있다.";
		dokebirized = "구시시신마살으슨 도소깨새비시마살에세서서 비시으습으슬 시시오솟으스로소 벼선혀성해새서서 사사요송하사느슨 마살이시다사. 두수버선째새 그슬자사으시 자사으슴이시 ㅂ에세서서 ㅅ으스로소 벼선하사느슨 거섯으슬 제세오시하사고소느슨 도소깨새비시마살으시 사사요송바상버섭으슬 그스대새로소 사사요송하살 수수 이싰다사.";
		assertEquals(dokebirized, dokebirizer.encode(original));
		assertEquals(original, dokebirizer.decode(dokebirized));
	}
}