#ifndef SHEET_9_STACK_H
#define SHEET_9_STACK_H

#include <iostream>
#include <string>

using namespace std;

class stack {
private:
	const int maxSize = 1000;
	char *content;
	// string str = "";
public:
	stack() {
		content = new char[maxSize];
		stack::clear();
	};
	bool empty();
	void pop();
	void push(char c);
	int size();
	char top();
	void clear();
};

#endif //SHEET_9_STACK_H
