import axiosInstance from "./axiosInstance";

export const rateRecipe = async (recipeId: string, rating: number) => {
	const response = await axiosInstance.put("/cookbook/rate/recipe", { recipe: recipeId, rating });
	return response.data;
};
