package net.luis.messenger;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public interface Sender {
	
	void send(@NotNull Message message, @NotNull String user);
}
