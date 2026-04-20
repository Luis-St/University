import axiosInstance from "./axiosInstance";
import type { User } from "../types/User";

export const checkAuth = async (): Promise<User> => {
  const response = await axiosInstance.get("/cookbook/saved");
  return response.data;
};
