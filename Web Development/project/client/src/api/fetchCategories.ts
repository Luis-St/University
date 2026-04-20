import axiosInstance from "./axiosInstance";
import type { Category } from "../types/Category";

export const fetchCategories = async (): Promise<Category[]> => {
  const response = await axiosInstance.get("/cookbook/categories");
  return response.data;
};
