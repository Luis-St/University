import axiosInstance from "./axiosInstance";
import type { LoginPayload } from "../types/AuthPayload";

export const loginUser = async (payload: LoginPayload) => {
  const response = await axiosInstance.post("/login", payload);
  return response.data;
};
