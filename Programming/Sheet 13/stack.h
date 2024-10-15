#ifndef SHEET_9_STACK_H
#define SHEET_9_STACK_H

#include <iostream>
#include <string>

using namespace std;

class stack {
private:
	int *maxSize;
	char *content;
	void extends(int newSize);
public:
	explicit stack(int size) {
		*maxSize = size;
		content = new char[size];
	}
    ~stack() {
        delete maxSize;
        delete[] content;
    };
	bool empty();
	void pop();
	void push(char c);
	int size();
	char top();
	void clear();
};

#endif //SHEET_9_STACK_H
