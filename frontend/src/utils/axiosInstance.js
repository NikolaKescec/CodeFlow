import axios from "axios";
import authState from "../authentication/state";

debugger;
const backend_url = "http://localhost:8080";

const axiosInstance = axios.create({
  baseURL: backend_url,
  withCredentials: true,
});

axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  async function (error) {
    debugger;
    const originalRequest = error.config;
    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      await refreshAccessToken();
      return axiosInstance(originalRequest);
    }
    if (error.response.status === 403) {
      localStorage.auth = JSON.stringify(authState);
      window.location = "/";
    }
    return Promise.reject(error);
  }
);

async function refreshAccessToken() {
  let result = await axiosInstance.get("/refresh");
}

export default axiosInstance;
