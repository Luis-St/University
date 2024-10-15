#include "SafeArray.h"

SafeArray::SafeArray() {

}

SafeArray::SafeArray(int defaultValue) {
	for (int i = 0; i < 100; ++i) {
		values[i] = defaultValue;
	}
}

bool SafeArray::setAt(int value, int pos) {
	if (pos < 0 || 100 <= pos) {
		return false;
	}
	values[pos] = value;
	return true;
}

int SafeArray::getAt(int pos) {
	if (pos < 0 || 100 <= pos) {
		return 0;
	}
	return values[pos];
}

int SafeArray::getMinimum() {
	int min = SafeArray::getAt(0);
	for (int i = 1; i < 100; ++i) {
		int value = SafeArray::getAt(i);
		if (value < min) {
			min = value;
		}
	}
	return min;
}

bool SafeArray::setAt(int value, int startPos, int endPos) {
	if (endPos < startPos) {
		return false;
	} else if (startPos == endPos) {
		return setAt(value, startPos);
	}
	bool flag = true;
	for (int i = startPos; i < endPos; ++i) {
		flag &= setAt(value, i);
	}
	return flag;
}

