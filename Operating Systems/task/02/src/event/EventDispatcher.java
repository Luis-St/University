package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis-St
 *
 */

interface EventDispatcher {
	
	public void addActionListener(ActionListener listener);
	
	public void processActionEvent(ActionEvent event);
}
