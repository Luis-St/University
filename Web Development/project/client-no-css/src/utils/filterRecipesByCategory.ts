import type { Recipe } from "../types/Recipe";

export const filterRecipesByCategory = (
	recipes: Recipe[],
	categoryId: string,
): Recipe[] => {
	if (!categoryId) return recipes;
	return recipes.filter((recipe) => recipe.categories.includes(categoryId));
};
