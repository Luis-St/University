package net.luis.messenger;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;

/**
 *
 * @author Luis-St
 *
 */

public class Message {
	
	private final byte[] content;
	private final Charset encoding;
	private final String type;
	private final String sender;
	
	private Message(byte @NotNull [] content, @NotNull Charset encoding, @NotNull String type, @NotNull String sender) {
		this.content = content;
		this.encoding = encoding;
		this.type = type;
		this.sender = sender;
	}
	
	public static @NotNull Message create(@NotNull String conten, @NotNull String sender) {
		return new Message(conten.getBytes(), Charset.defaultCharset(), "text/plain", sender);
	}
	
	public static @NotNull Message create(@NotNull String conten, @NotNull Charset encoding, @NotNull String sender) {
		return new Message(conten.getBytes(encoding), encoding, "text/plain", sender);
	}
	
	public static @NotNull Message create(@NotNull String conten, @NotNull Charset encoding, @NotNull String type, @NotNull String sender) {
		return new Message(conten.getBytes(encoding), encoding, type, sender);
	}
	
	public byte @NotNull [] getContent() {
		return this.content;
	}
	
	public @NotNull Charset getEncoding() {
		return this.encoding;
	}
	
	public @NotNull String getType() {
		return this.type;
	}
	
	public boolean isType(@NotNull String type) {
		return this.type.equals(type);
	}
	
	public @NotNull String getSender() {
		return this.sender;
	}
	
	public @NotNull String getString() {
		return new String(this.content, this.encoding);
	}
}
