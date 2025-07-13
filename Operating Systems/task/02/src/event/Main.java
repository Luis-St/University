package event;

import java.awt.event.ActionListener;

/**
 *
 * @author Luis-St
 *
 */

class Main {
	
	public static void main(String[] args) {
		ActionListener listener1 = new ActionListenerImpl1();
		ActionListener listener2 = new ActionListenerImpl2();
		
		EventDispatcherImpl dispatcher = new EventDispatcherImpl();
		
		dispatcher.addActionListener(listener1);
		dispatcher.addActionListener(listener2);
		
		dispatcher.processActionEvent(null);
	}
}
