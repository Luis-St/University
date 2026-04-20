import axiosInstance from "./axiosInstance";

export const unsaveRecipe = async (recipeId: string) => {
	const response = await axiosInstance.put("/cookbook/save/delete/recipe", { recipe: recipeId });
	return response.data;
};
