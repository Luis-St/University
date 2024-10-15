package task1;

/**
 *
 * @author Luis-St
 *
 */

public interface Wort {
	char position(int k);
	int laenge();
	Wort concat(Wort w);
	int anzahl(char c);
	Wort tausche(char c1, char c2);
	int istTeilwortVon(Wort w);
	Wort teilwort(int start, int laenge);
	Wort ersetze(Wort w1, Wort w2);
}
