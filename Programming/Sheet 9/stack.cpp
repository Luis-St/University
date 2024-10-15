#include "stack.h"

bool stack::empty() {
	return stack::size() == 0;
}

void stack::pop() {
	if (!stack::empty()) {
		if (stack::size() == 1) {
			stack::clear();
		} else {
			content[stack::size() - 1] = '\0';
		}
	}
}

void stack::push(char c) {
	if (stack::size() >= maxSize) {
		cout << "The stack has reached the maximum size" << endl;
	} else {
		content[stack::size()] = c;
	}
}

int stack::size() {
	for (int i = 0; i < maxSize; ++i) {
		if (content[i] == '\0') {
			return i;
		}
	}
	return maxSize;
}

char stack::top() {
	if (stack::empty()) {
		return '\0';
	} else {
		return content[stack::size() - 1];
	}
}

void stack::clear() {
	for (int i = 0; i < maxSize; ++i) {
		content[i] = '\0';
	}
}
/*
bool stack::empty() {
	return str.empty();
}

void stack::pop() {
	if (!stack::empty()) {
		if (stack::size() == 1) {
			stack::clear();
		} else {
			str = str.substr(0, str.length() - 1);
		}
	}
}

void stack::push(char c) {
	str += c;
}

int stack::size() {
	return str.length();
}

char stack::top() {
	if (stack::empty()) {
		return '\0';
	} else {
		return str[stack::size() - 1];
	}
}

void stack::clear() {
	str = "";
}
*/