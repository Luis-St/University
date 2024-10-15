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
	if (stack::size() >= *maxSize) {
		stack::extends(*maxSize * 2);
		stack::push(c);
	} else {
		content[stack::size()] = c;
	}
}

void stack::extends(int newSize) {
	cout << "The stack has reached the maximum size, extending the stack to new size " << newSize << endl;
	char *newContent = new char[newSize];
	for (int i = 0; i < newSize; ++i) {
		newContent[i] = '\0';
	}
	for (int i = 0; i < *maxSize; ++i) {
		newContent[i] = content[i];
	}
	*maxSize = newSize;
	delete[] content;
	content = newContent;
}

int stack::size() {
	for (int i = 0; i < *maxSize; ++i) {
		if (content[i] == '\0') {
			return i;
		}
	}
	return *maxSize;
}

char stack::top() {
	if (stack::empty()) {
		return '\0';
	} else {
		return content[stack::size() - 1];
	}
}

void stack::clear() {
	for (int i = 0; i < *maxSize; ++i) {
		content[i] = '\0';
	}
}
