import api from "./api";

export const register = (data) => api.post("/auth/register", data);
export const login = async (data) => {
  const response = await api.post("/auth/login", data);
  localStorage.setItem("user", JSON.stringify(response.data));
  return response.data;
};
export const logout = () => localStorage.removeItem("user");
