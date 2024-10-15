package net.luis.sheet7;

import net.luis.util.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 *
 * @author Luis-St
 *
 */

@Task("3")
public class GenericNodeList<T> implements Iterable<T> {
	
	private Node<T> first = null;
	private Node<T> last = null;
	
	public GenericNodeList() {
	
	}
	
	public GenericNodeList(T data) {
		this.first = new Node<>(data);
	}
	
	public void add(T data) {
		if (this.first == null) {
			this.first = new Node<>(data);
			this.last = this.first;
		} else {
			Node<T> next = new Node<>(data);
			this.last.setNext(next);
			this.last = next;
		}
	}
	
	public int size() {
		if (this.first == null) {
			return 0;
		} else {
			int size = 1;
			Node<T> current = this.first;
			while (current.getNext() != null) {
				size++;
				current = current.getNext();
			}
			return size;
		}
	}
	
	public T get(int index) {
		if (this.first == null) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<T> current = this.first;
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
			Node<T> current = this.first;
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
	public @NotNull Iterator<T> iterator() {
		return new NodeListIterator<>(this.first);
	}
	
	private static class NodeListIterator<T> implements Iterator<T> {
		
		private Node<T> current;
		
		public NodeListIterator(Node<T> first) {
			this.current = first;
		}
		
		@Override
		public boolean hasNext() {
			return this.current != null;
		}
		
		@Override
		public T next() {
			T data = this.current.get();
			this.current = this.current.getNext();
			return data;
		}
	}
	//endregion
	
	private static class Node<T> {
		
		private final T data;
		private Node<T> next = null;
		
		public Node(T data) {
			this.data = data;
		}
		
		public T get() {
			return this.data;
		}
		
		public Node<T> getNext() {
			return this.next;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;
		}
	}
}
