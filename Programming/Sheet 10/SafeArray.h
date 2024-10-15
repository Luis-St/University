#ifndef SHEET_10_SAFEARRAY_H
#define SHEET_10_SAFEARRAY_H

class SafeArray {
private:
	int values[100];
public:
	SafeArray();
	explicit SafeArray(int defaultValue);
	bool setAt(int value, int pos);
	int getAt(int pos);
	int getMinimum();
	bool setAt(int value, int startPos, int endPos);
};

#endif //SHEET_10_SAFEARRAY_H
