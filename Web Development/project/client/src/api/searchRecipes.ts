import axiosInstance from "./axiosInstance";
import type { Recipe } from "../types/Recipe";

export const searchRecipes = async (params: {
  tags?: string;
  category?: string;
}): Promise<Recipe[]> => {
  const query = new URLSearchParams();
  if (params.tags) query.set("tags", params.tags);
  if (params.category) query.set("category", params.category);
  const response = await axiosInstance.get(`/cookbook/search?${query.toString()}`);
  return response.data;
};
