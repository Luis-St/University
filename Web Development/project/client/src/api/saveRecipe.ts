import axiosInstance from "./axiosInstance";

export const saveRecipe = async (recipeId: string) => {
  const response = await axiosInstance.put("/cookbook/save/recipe", { recipe: recipeId });
  return response.data;
};
