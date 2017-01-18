package net.crizin;

import java.io.Serializable;
import java.util.Arrays;

public class Jaso implements Serializable, Comparable<Jaso>, CharSequence {
	private static final long serialVersionUID = -613436564625446761L;


	public enum Chosung {
		ㄱ(0), ㄲ(1), ㄴ(2), ㄷ(3), ㄸ(4), ㄹ(5), ㅁ(6), ㅂ(7), ㅃ(8), ㅅ(9), ㅆ(10), ㅇ(11), ㅈ(12), ㅉ(13), ㅊ(14), ㅋ(15), ㅌ(16), ㅍ(17), ㅎ(18);

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
		None(0), ㄱ(1), ㄲ(2), ㄳ(3), ㄴ(4), ㄵ(5), ㄶ(6), ㄷ(7), ㄹ(8), ㄺ(9), ㄻ(10), ㄼ(11), ㄽ(12), ㄾ(13), ㄿ(14), ㅀ(15), ㅁ(16), ㅂ(17), ㅄ(18), ㅅ(19), ㅆ(20), ㅇ(21), ㅈ(22), ㅊ(23), ㅋ(24), ㅌ(25), ㅍ(26), ㅎ(27);

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

	private static final int hangulUnicodeBase = 0xAC00;
	private static final int jungsungCount = Jungsung.values().length;
	private static final int jongsungCount = Jongsung.values().length;

	private Chosung chosung;
	private Jungsung jungsung;
	private Jongsung jongsung;

	public Jaso(char character) {
		int code = character - hangulUnicodeBase;

		chosung = Chosung.find(code / (jungsungCount * jongsungCount));
		jungsung = Jungsung.find(code % (jungsungCount * jongsungCount) / jongsungCount);
		jongsung = Jongsung.find(code % jongsungCount);
	}

	public static boolean isValid(char character) {
		return (character >= 'ㄱ' && character <= '힣');
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

		return (char) (code + hangulUnicodeBase);
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
		if (index != 0) {
			throw new StringIndexOutOfBoundsException(index);
		}

		return toCharacter();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		if (start < 0) {
			throw new StringIndexOutOfBoundsException(start);
		}

		if (end > 1) {
			throw new StringIndexOutOfBoundsException(end);
		}

		return toString().subSequence(start, end);
	}

	@Override
	public String toString() {
		return String.valueOf(toCharacter());
	}
}