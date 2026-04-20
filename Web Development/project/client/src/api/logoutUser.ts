import axiosInstance from "./axiosInstance";

export const logoutUser = async () => {
  const response = await axiosInstance.post("/logout");
  return response.data;
};
