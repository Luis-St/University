#include "Node.h"

Node::Node(int value) {
	this->val = value;
	this->next = nullptr;
}

Node::~Node() {
	delete &val;
	delete next;
}

void Node::insertFirst(int value) {
	Node *node = new Node(value);
	node->next = this->next;
	this->next = node;
}

void Node::insertLast(int value) {
	Node *node = this;
	while (node != nullptr) {
		Node *nextNode = node->next;
		if (nextNode == nullptr) {
			node->next = new Node(value);
			break;
		} else {
			node = nextNode;
		}
	}
}

void Node::insertSorted(int value) {
	if (value < this->val) {
		cerr << "Cannot insert a node before the root node" << endl;
	}
	Node *node = this;
	while (node != nullptr) {
		if (node->val == value) {
			cerr << "A new node with value " << value << " cannot be inserted, because a node with this value already exists" << endl;
			break;
		} else if (node->val < value) {
			Node *nextNode = node->next;
			if (nextNode->next == nullptr) {
				nextNode->next = new Node(value);
				break;
			} else if (value != nextNode->next->val && value < nextNode->next->val) {
				Node *newNode = new Node(value);
				newNode->next = nextNode->next;
				nextNode->next = newNode;
				break;
			}
		}
		node = node->next;
	}
}

Node *Node::remove(int value) {
	if (value == this->val) {
		cerr << "The root node cannot be removed" << endl;
		return nullptr;
	}
	Node *node = this;
	while (node != nullptr) {
		Node *nextNode = node->next;
		if (nextNode->val == value) {
			node->next = nextNode->next;
			nextNode->next = nullptr;
			return nextNode;
		} else {
			node = nextNode;
		}
	}
	return nullptr;
}

void Node::print() {
	string str = "";
	Node *node = this;
	while (node != nullptr) {
		if (node->next != nullptr) {
			str += " " + to_string(node->val) + ",";
		} else {
			str += " " + to_string(node->val);
		}
		node = node->next;
	}
	cout << "Nodes:" << str << endl;
}

int Node::length() {
	int length = 0;
	Node *node = this;
	while (node != nullptr) {
		node = node->next;
		length++;
	}
	return length;
}

int Node::value() {
	return this->val;
}
