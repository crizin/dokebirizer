package net.crizin;

import java.io.Serializable;
import java.util.Arrays;

public class Jaso implements Serializable, Comparable<Jaso>, CharSequence {
	private static final long serialVersionUID = -613436564625446761L;

	public enum Chosung {
		ᄀ(0), ᄁ(1), ᄂ(2), ᄃ(3), ᄄ(4), ᄅ(5), ᄆ(6), ᄇ(7), ᄈ(8), ᄉ(9), ᄊ(10), ᄋ(11), ᄌ(12), ᄍ(13), ᄎ(14), ᄏ(15), ᄐ(16), ᄑ(17), ᄒ(18);

		private final int sequence;

		Chosung(int sequence) {
			this.sequence = sequence;
		}

		public int getSequence() {
			return sequence;
		}

		public Character getChar() {
			return name().charAt(0);
		}

		public static Chosung find(int sequence) {
			return Arrays.stream(values()).filter(e -> e.sequence == sequence).findFirst().orElseThrow(IllegalArgumentException::new);
		}
	}

	public enum Jungsung {
		ㅏ(0), ㅐ(1), ㅑ(2), ㅒ(3), ㅓ(4), ㅔ(5), ㅕ(6), ㅖ(7), ㅗ(8), ㅘ(9), ㅙ(10), ㅚ(11), ㅛ(12), ㅜ(13), ㅝ(14), ㅞ(15), ㅟ(16), ㅠ(17), ㅡ(18), ㅢ(19), ㅣ(20);

		private final int sequence;

		Jungsung(int sequence) {
			this.sequence = sequence;
		}

		public int getSequence() {
			return sequence;
		}

		public Character getChar() {
			return name().charAt(0);
		}

		public static Jungsung find(int sequence) {
			return Arrays.stream(values()).filter(e -> e.sequence == sequence).findFirst().orElseThrow(IllegalArgumentException::new);
		}
	}

	public enum Jongsung {
		None(0), ᆨ(1), ᆩ(2), ᆪ(3), ᆫ(4), ᆬ(5), ᆭ(6), ᆮ(7), ᆯ(8), ᆰ(9), ᆱ(10), ᆲ(11), ᆳ(12), ᆴ(13), ᆵ(14), ᆶ(15), ᆷ(16), ᆸ(17), ᆹ(18), ᆺ(19), ᆻ(20), ᆼ(21), ᆽ(22), ᆾ(23), ᆿ(24), ᇀ(25), ᇁ(26), ᇂ(27);

		private final int sequence;

		Jongsung(int sequence) {
			this.sequence = sequence;
		}

		public int getSequence() {
			return sequence;
		}

		public Character getChar() {
			return (sequence == None.sequence) ? null : name().charAt(0);
		}

		public static Jongsung find(int sequence) {
			return Arrays.stream(values()).filter(e -> e.sequence == sequence).findFirst().orElseThrow(IllegalArgumentException::new);
		}
	}

	private static final int jungsungCount = Jungsung.values().length;
	private static final int jongsungCount = Jongsung.values().length;

	private Chosung chosung;
	private Jungsung jungsung;
	private Jongsung jongsung;

	public Jaso(char character) {
		int code = character - 0xAC00;

		chosung = Chosung.find(code / (jungsungCount * jongsungCount));
		jungsung = Jungsung.find(code % (jungsungCount * jongsungCount) / jongsungCount);
		jongsung = Jongsung.find(code % jongsungCount);
	}

	public static boolean isValid(char character) {
		return (character >= 0xAC00 && character <= 0xD7A3);
	}

	public Chosung getChosung() {
		return chosung;
	}

	public Chosung setChosung(Chosung chosung) {
		Chosung oldValue = this.chosung;
		this.chosung = chosung;
		return oldValue;
	}

	public Jungsung getJungsung() {
		return jungsung;
	}

	public Jungsung setJungsung(Jungsung jungsung) {
		Jungsung oldValue = this.jungsung;
		this.jungsung = jungsung;
		return oldValue;
	}

	public Jongsung getJongsung() {
		return jongsung;
	}

	public Jongsung setJongsung(Jongsung jongsung) {
		Jongsung oldValue = this.jongsung;
		this.jongsung = jongsung;
		return oldValue;
	}

	public char toCharacter() {
		int code = chosung.sequence * jungsungCount * jongsungCount +
				jungsung.sequence * jongsungCount +
				jongsung.sequence;

		return (char) (code + 0xAC00);
	}

	@Override
	public int compareTo(Jaso o) {
		return Character.compare(toCharacter(), o.toCharacter());
	}

	@Override
	public boolean equals(Object o) {
		return this == o || !(o == null || getClass() != o.getClass()) && toCharacter() == ((Jaso) o).toCharacter();
	}

	@Override
	public int hashCode() {
		return Character.hashCode(toCharacter());
	}

	@Override
	public int length() {
		return 1;
	}

	@Override
	public char charAt(int index) {
		return toString().charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return toString().subSequence(start, end);
	}

	@Override
	public String toString() {
		return String.valueOf(toCharacter());
	}
}