import axiosInstance from "./axiosInstance";

export const commentAndRateRecipe = async (recipeId: string, comment: string, rating: number) => {
  const response = await axiosInstance.put("/cookbook/commentrate/recipe", {
    recipe: recipeId,
    comment,
    rating,
  });
  return response.data;
};
