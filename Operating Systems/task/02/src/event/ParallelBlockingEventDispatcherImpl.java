package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Luis-St
 *
 */

class ParallelBlockingEventDispatcherImpl implements EventDispatcher {
	
	private final List<ActionListener> listeners = new ArrayList<>();
	private final Map<ActionListener, Lock> locks = new HashMap<>();
	
	@Override
	public void addActionListener(ActionListener listener) {
		this.listeners.add(listener);
		this.locks.put(listener, new ReentrantLock());
	}
	
	@Override
	public void processActionEvent(ActionEvent event) {
		for (ActionListener listener : this.listeners) {
			Thread thread = new Thread(() -> {
				Lock lock = this.locks.get(listener);
				lock.lock();
				try {
					listener.actionPerformed(event);
				} finally {
					lock.unlock();
				}
			});
			thread.start();
		}
	}
}
