#include <iostream>
#include "stack.h"
#include "Node.h"

using namespace std;

void task1();
void task2();

void task1() {
	stack stack(2);
	stack.clear();
	stack.push('a');
	stack.push('b');
	cout << "Stack top: " << stack.top() << endl; // b
	stack.push('c'); // Expected expansion of the stack size
	cout << "Stack top: " << stack.top() << endl; // c
	stack.pop();
	cout << "Stack size: " << stack.size() << endl; // 2
	stack.pop();
	cout << "Stack top: " << stack.top() << endl; // a
}

void task2() {
	Node* node = new Node(2);
	node->insertFirst(3);
	node->insertLast(4);
	node->insertLast(6);
	node->insertLast(7);
	node->insertLast(8);
	node->print();
	node->insertSorted(9);
	node->print();
	cout << "Removed node: " << node->remove(4)->value() << endl;
	node->print();
	node->insertSorted(4);
	node->print();
}

int main(int argc, char *argv[]) {
	task2();
}
