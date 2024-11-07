package net.luis;

import org.jetbrains.annotations.*;

import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class Clerk {
	
	private static final Map<String, Clerk> CLERKS = new HashMap<>();
	
	private final Map<String, Training> attendedTrainings = new HashMap<>();
	private final Map<String, Training> finishedTrainings = new HashMap<>();
	private String username;
	private String password;
	private boolean isAdmin;
	
	public static @NotNull @Unmodifiable List<String> getAllClerkUsernames() {
		return List.copyOf(CLERKS.keySet());
	}
	
	public static @Nullable Clerk getClerk(@NotNull String username) {
		return CLERKS.get(username);
	}
	
	public static void addClerk(@NotNull Clerk clerk) {
		CLERKS.put(clerk.getUsername(), clerk);
	}
	
	public static void removeClerk(@NotNull Clerk clerk) {
		CLERKS.remove(clerk.getUsername());
	}
	
	public @Nullable String getUsername() {
		return this.username;
	}
	
	public void setUsername(@NotNull String username) {
		this.username = username;
	}
	
	public @Nullable String getPassword() {
		return this.password;
	}
	
	public void setPassword(@NotNull String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	public void setPermission(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
