package net.luis.messenger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Luis-St
 *
 */

public class User implements Sender, Receiver {
	
	private final MessengerService service;
	private final String name;
	private final String password;
	private final Map<String, List<Message>> sentMessages = Maps.newHashMap();
	private final Map<String, List<Message>> receivedMessages = Maps.newHashMap();
	
	public User(@NotNull MessengerService service, @NotNull String name, @NotNull String password) {
		this.service = service;
		this.name = name;
		this.password = password;
	}
	
	public @NotNull String getName() {
		return this.name;
	}
	
	public @NotNull String getPassword() {
		return this.password;
	}
	
	public @NotNull @Unmodifiable Map<String, List<Message>> getSentMessages() {
		return Map.copyOf(this.sentMessages);
	}
	
	public @NotNull @Unmodifiable List<Message> getSentMessages(@NotNull String name) {
		return List.copyOf(this.sentMessages.get(name));
	}
	
	public @NotNull @Unmodifiable Map<String, List<Message>> getReceivedMessages() {
		return Map.copyOf(this.receivedMessages);
	}
	
	public @NotNull @Unmodifiable List<Message> getReceivedMessages(@NotNull String name) {
		return List.copyOf(this.receivedMessages.get(name));
	}
	
	@Override
	public void receive(@NotNull Message message, @NotNull String sender) {
		this.receivedMessages.computeIfAbsent(sender, k -> Lists.newArrayList()).add(message);
		System.out.println(this.name + " received a message from " + sender + ": " + message.getString());
	}
	
	@Override
	public void send(@NotNull Message message, @NotNull String receiver) {
		this.sentMessages.computeIfAbsent(receiver, k -> Lists.newArrayList()).add(message);
		this.service.send(message, receiver);
	}
}
