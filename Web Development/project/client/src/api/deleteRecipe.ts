import axiosInstance from "./axiosInstance";

export const deleteRecipe = async (recipeId: string) => {
  const response = await axiosInstance.delete(`/cookbook/delete/${recipeId}`);
  return response.data;
};
