import axiosInstance from "./axiosInstance";
import type { SignupPayload } from "../types/AuthPayload";

export const signupUser = async (payload: SignupPayload) => {
  const response = await axiosInstance.post("/signup", payload);
  return response.data;
};
