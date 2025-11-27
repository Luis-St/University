package net.luis.messenger;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public interface Receiver {
	
	void receive(@NotNull Message message, @NotNull String sender);
}
