#ifndef SHEET_13_NODE_H
#define SHEET_13_NODE_H

#include <iostream>
#include <string>

using namespace std;

class Node {
private:
	int val;
	Node *next;
public:
	explicit Node(int value);
	~Node();
	void insertFirst(int value);
	void insertLast(int value);
	void insertSorted(int value);
	Node *remove(int value);
	void print();
	int length();
	int value();
};

#endif //SHEET_13_NODE_H
