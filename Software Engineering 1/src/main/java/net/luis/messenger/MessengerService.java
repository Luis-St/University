package net.luis.messenger;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 *
 * @author Luis-St
 *
 */

public class MessengerService {
	
	private final Map<String, User> users = Maps.newHashMap();
	
	public boolean login(@NotNull User user) {
		if (this.users.containsKey(user.getName())) {
			return false;
		}
		System.out.println(user.getName() + " has logged in.");
		this.users.put(user.getName(), user);
		return true;
	}
	
	public boolean logout(@NotNull User user) {
		if (this.users.containsKey(user.getName())) {
			this.users.remove(user.getName());
			System.out.println(user.getName() + " has logged out.");
			return true;
		}
		return false;
	}
	
	public boolean isOnline(@NotNull String name) {
		return this.users.containsKey(name);
	}
	
	public boolean send(@NotNull Message message) {
		this.users.values().forEach(user -> this.send(message, user.getName()));
		return !this.users.isEmpty();
	}
	
	public boolean send(@NotNull Message message, @NotNull String receiver) {
		if (this.users.containsKey(receiver)) {
			this.users.get(receiver).receive(message, message.getSender());
			return true;
		}
		System.out.println("Message '" + message.getString() + "' could not be sent to " + receiver);
		return false;
	}
}
