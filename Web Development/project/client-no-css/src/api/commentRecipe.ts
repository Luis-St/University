import axiosInstance from "./axiosInstance";

export const commentRecipe = async (recipeId: string, comment: string) => {
	const response = await axiosInstance.put("/cookbook/comment/recipe", { recipe: recipeId, comment });
	return response.data;
};
