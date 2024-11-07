package net.luis;

import org.jetbrains.annotations.*;

import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class Training {
	
	private static final Map<String, Training> TRAININGS = new HashMap<>();
	
	private final List<Training> requirements = new ArrayList<>();
	private String name;
	
	public static @NotNull @Unmodifiable List<String> getAllTrainingNames() {
		return List.copyOf(TRAININGS.keySet());
	}
	
	public static @Nullable Training getTraining(@NotNull String name) {
		return TRAININGS.get(name);
	}
	
	public @Nullable String getName() {
		return this.name;
	}
	
	public void setName(@NotNull String name) {
		this.name = name;
	}
	
	public void addRequirement(@NotNull Training training) {
		this.requirements.add(training);
	}
	
	public void removeRequirement(@NotNull Training training) {
		this.requirements.remove(training);
	}
	public boolean isRequirement(@NotNull Training training) {
		return this.requirements.contains(training);
	}
	
	public @NotNull @Unmodifiable List<Training> getRequirements() {
		return List.copyOf(this.requirements);
	}
}
