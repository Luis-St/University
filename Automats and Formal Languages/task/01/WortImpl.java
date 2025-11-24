package task1;

import java.util.Objects;

public class WortImpl implements Wort {
	
	private final String internal;
	
	public WortImpl(char[] internal) {
		this.internal = new String(internal);
	}
	
	public WortImpl(String internal) {
		this.internal = internal;
	}
	
	@Override
	public char position(int k) {
		if (k <= 0) {
			throw new IllegalArgumentException("Index must not be greater than 0");
		}
		if (k > this.internal.length()) {
			throw new IndexOutOfBoundsException("Index must not be greater than the length of the word");
		}
		return this.internal.charAt(k - 1);
	}
	
	@Override
	public int laenge() {
		return this.internal.length();
	}
	
	@Override
	public Wort concat(Wort w) {
		return new WortImpl(this.internal + w.toString());
	}
	
	@Override
	public int anzahl(char c) {
		return (int) this.internal.chars().filter(ch -> ch == c).count();
	}
	
	@Override
	public Wort tausche(char c1, char c2) {
		return new WortImpl(this.internal.replace(c1, c2));
	}
	
	@Override
	public int istTeilwortVon(Wort w) {
		return this.internal.indexOf(w.toString()) + 1;
	}
	
	@Override
	public Wort teilwort(int start, int laenge) {
		return new WortImpl(this.internal.substring(start - 1, start - 1 + laenge));
	}
	
	@Override
	public Wort ersetze(Wort w1, Wort w2) {
		return new WortImpl(this.internal.replace(w1.toString(), w2.toString()));
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WortImpl wort)) return false;
		
		return this.internal.equals(wort.internal);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.internal);
	}
	
	@Override
	public String toString() {
		return this.internal;
	}
}
