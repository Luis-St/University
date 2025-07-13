package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis-St
 *
 */

class ParallelEventDispatcherImpl implements EventDispatcher {
	
	private final List<ActionListener> listeners = new ArrayList<>();
	
	@Override
	public void addActionListener(ActionListener listener) {
		this.listeners.add(listener);
	}
	
	@Override
	public void processActionEvent(ActionEvent event) {
		for (ActionListener listener : this.listeners) {
			Thread thread = new Thread(() -> {
				listener.actionPerformed(event);
			});
			thread.start();
		}
	}
}
