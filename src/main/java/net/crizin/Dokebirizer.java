package net.crizin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Dokebirizer {
	public enum Policy {
		Split {
			private final Map<Jaso.Jungsung, Jaso.Jungsung[]> jungsungMap = new HashMap<Jaso.Jungsung, Jaso.Jungsung[]>() {
				{
					put(Jaso.Jungsung.ㅚ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅗ, Jaso.Jungsung.ㅣ});
					put(Jaso.Jungsung.ㅟ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅜ, Jaso.Jungsung.ㅣ});
					put(Jaso.Jungsung.ㅞ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅜ, Jaso.Jungsung.ㅔ});
					put(Jaso.Jungsung.ㅙ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅗ, Jaso.Jungsung.ㅐ});
					put(Jaso.Jungsung.ㅝ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅜ, Jaso.Jungsung.ㅓ});
					put(Jaso.Jungsung.ㅢ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅡ, Jaso.Jungsung.ㅣ});
				}
			};

			Map<Jaso.Jungsung, Jaso.Jungsung[]> getJungsungMap() {
				return jungsungMap;
			}
		},
		Copy {
			Map<Jaso.Jungsung, Jaso.Jungsung[]> getJungsungMap() {
				return Collections.emptyMap();
			}
		},
		Transform {
			private final Map<Jaso.Jungsung, Jaso.Jungsung[]> jungsungMap = new HashMap<Jaso.Jungsung, Jaso.Jungsung[]>() {
				{
					put(Jaso.Jungsung.ㅚ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅚ, Jaso.Jungsung.ㅔ});
					put(Jaso.Jungsung.ㅟ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅟ, Jaso.Jungsung.ㅣ});
					put(Jaso.Jungsung.ㅞ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅞ, Jaso.Jungsung.ㅔ});
					put(Jaso.Jungsung.ㅙ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅙ, Jaso.Jungsung.ㅐ});
					put(Jaso.Jungsung.ㅝ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅝ, Jaso.Jungsung.ㅓ});
					put(Jaso.Jungsung.ㅢ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅢ, Jaso.Jungsung.ㅣ});
				}
			};

			Map<Jaso.Jungsung, Jaso.Jungsung[]> getJungsungMap() {
				return jungsungMap;
			}
		};

		private final Map<Jaso.Jungsung, Jaso.Jungsung[]> commonJungsungMap = new HashMap<Jaso.Jungsung, Jaso.Jungsung[]>() {
			{
				put(Jaso.Jungsung.ㅑ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅑ, Jaso.Jungsung.ㅏ});
				put(Jaso.Jungsung.ㅒ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅒ, Jaso.Jungsung.ㅐ});
				put(Jaso.Jungsung.ㅕ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅕ, Jaso.Jungsung.ㅓ});
				put(Jaso.Jungsung.ㅖ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅖ, Jaso.Jungsung.ㅔ});
				put(Jaso.Jungsung.ㅛ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅛ, Jaso.Jungsung.ㅗ});
				put(Jaso.Jungsung.ㅠ, new Jaso.Jungsung[]{Jaso.Jungsung.ㅠ, Jaso.Jungsung.ㅜ});
			}
		};

		public String dokebirize(char character, Jaso.Chosung secondChosung) {
			Jaso jaso1 = new Jaso(character);
			Jaso jaso2 = new Jaso(character);

			Jaso.Jungsung[] jungsungs = splitJungsung(jaso1.getJungsung());

			jaso1.setJongsung(Jaso.Jongsung.None);
			jaso1.setJungsung(jungsungs[0]);
			jaso2.setJungsung(jungsungs[1]);
			jaso2.setChosung(secondChosung);

			return jaso1.toString() + jaso2.toString();
		}

		public Character unDokebirize(char character1, char character2, Jaso.Chosung secondChosung) {
			if (!Jaso.isValid(character1) || !Jaso.isValid(character2)) {
				return null;
			}

			Jaso j1 = new Jaso(character1);
			Jaso j2 = new Jaso(character2);

			if (j1.getJongsung() != Jaso.Jongsung.None) {
				return null;
			}

			Jaso.Jungsung jungsung = joinJungsung(j1.getJungsung(), j2.getJungsung());

			if (jungsung == null) {
				return null;
			}

			if (j2.getChosung() != secondChosung) {
				return null;
			}

			j1.setJungsung(jungsung);
			j1.setJongsung(j2.getJongsung());

			return j1.toCharacter();
		}

		private Jaso.Jungsung[] splitJungsung(Jaso.Jungsung jungsung) {
			return getJungsungMap().getOrDefault(jungsung, commonJungsungMap.getOrDefault(jungsung, new Jaso.Jungsung[]{jungsung, jungsung}));
		}

		private Jaso.Jungsung joinJungsung(Jaso.Jungsung jungsung1, Jaso.Jungsung jungsung2) {
			if (jungsung1 == jungsung2) {
				return jungsung1;
			}

			return Stream.concat(getJungsungMap().entrySet().stream(), commonJungsungMap.entrySet().stream())
					.filter(e -> e.getValue()[0] == jungsung1 && e.getValue()[1] == jungsung2)
					.map(Map.Entry::getKey)
					.findFirst()
					.orElseGet(() -> null);
		}

		abstract Map<Jaso.Jungsung, Jaso.Jungsung[]> getJungsungMap();
	}

	private final Policy policy;
	private final Jaso.Chosung secondChosung;

	public Dokebirizer() {
		this(Policy.Split, Jaso.Chosung.ᄇ);
	}

	public Dokebirizer(Policy policy, Jaso.Chosung secondChosung) {
		this.policy = policy;
		this.secondChosung = secondChosung;
	}

	public String encode(String string) {
		Objects.requireNonNull(string);

		StringBuilder sb = new StringBuilder(string.length() * 2);

		for (int i = 0, length = string.length(); i < length; i++) {
			char c = string.charAt(i);

			if (Jaso.isValid(c)) {
				sb.append(policy.dokebirize(c, secondChosung));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public String decode(String string) {
		Objects.requireNonNull(string);

		StringBuilder sb = new StringBuilder(string.length());

		for (int i = 0, length = string.length(); i < length; i++) {
			char c1 = string.charAt(i);

			if (i + 1 == length) {
				sb.append(c1);
				break;
			}

			char c2 = string.charAt(i + 1);

			Character result = policy.unDokebirize(c1, c2, secondChosung);

			if (result == null) {
				sb.append(c1);
			} else {
				sb.append(result);
				i++;
			}
		}

		return sb.toString();
	}
}