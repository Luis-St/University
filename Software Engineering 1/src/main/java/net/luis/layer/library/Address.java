package net.luis.layer.library;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public record Address(
	@NotNull String street,
	int houseNumber,
	@NotNull String postalCode,
	@NotNull String city,
	@NotNull String state,
	@NotNull String country
) {}
