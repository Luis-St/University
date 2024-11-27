package net.luis.messenger;

import java.util.function.Function;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	public static void main(String[] args) {
		Function<User, Message> hello = sender -> Message.create("Hello", sender.getName());
		
		MessengerService messengerService = new MessengerService();
		
		User user1 = new User(messengerService, "User1", "*****");
		User user2 = new User(messengerService, "User2", "*****");
		
		messengerService.login(user1);
		messengerService.send(hello.apply(user1), user2.getName());
		
		messengerService.login(user2);
		messengerService.send(hello.apply(user2), user1.getName());
		messengerService.send(hello.apply(user1), user2.getName());
		
		messengerService.logout(user1);
		messengerService.send(hello.apply(user2), user1.getName());
		messengerService.logout(user2);
	}
}
