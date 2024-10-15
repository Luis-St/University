package net.luis.sheet7;

import net.luis.util.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 *
 * @author Luis-St
 *
 */

@Task("1")
public class IntNodeList implements Iterable<Integer> {
	
	private Node first = null;
	private Node last = null;
	
	public IntNodeList() {
	
	}
	
	public IntNodeList(int data) {
		this.first = new Node(data);
	}
	
	public void add(int data) {
		if (this.first == null) {
			this.first = new Node(data);
			this.last = this.first;
		} else {
			Node next = new Node(data);
			this.last.setNext(next);
			this.last = next;
		}
	}
	
	public int size() {
		if (this.first == null) {
			return 0;
		} else {
			int size = 1;
			Node current = this.first;
			while (current.getNext() != null) {
				size++;
				current = current.getNext();
			}
			return size;
		}
	}
	
	public int get(int index) {
		if (this.first == null) {
			throw new IndexOutOfBoundsException();
		} else {
			Node current = this.first;
			for (int i = 0; i < index; i++) {
				if (current.getNext() == null) {
					throw new IndexOutOfBoundsException();
				}
				current = current.getNext();
			}
			return current.get();
		}
	}
	
	public void remove(int index) {
		if (this.first == null) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			this.first = this.first.getNext();
			if (this.first == null) {
				this.last = null;
			}
		} else {
			Node current = this.first;
			for (int i = 0; i < index - 1; i++) {
				if (current.getNext() == null) {
					throw new IndexOutOfBoundsException();
				}
				current = current.getNext();
			}
			current.setNext(current.getNext().getNext());
			if (current.getNext() == null) {
				this.last = current;
			}
		}
	}
	
	//region Iterable
	@Override
	public @NotNull Iterator<Integer> iterator() {
		return new NodeListIterator(this.first);
	}
	
	private static class NodeListIterator implements Iterator<Integer> {
		
		private Node current;
		
		public NodeListIterator(Node first) {
			this.current = first;
		}
		
		@Override
		public boolean hasNext() {
			return this.current != null;
		}
		
		@Override
		public Integer next() {
			int data = this.current.get();
			this.current = this.current.getNext();
			return data;
		}
	}
	//endregion
	
	private static class Node {
		
		private final int data;
		private Node next = null;
		
		public Node(int data) {
			this.data = data;
		}
		
		public int get() {
			return this.data;
		}
		
		public Node getNext() {
			return this.next;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
	}
}
