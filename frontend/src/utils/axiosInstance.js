import axios from "axios";

debugger;
const backend_url = "http://localhost:8080";

const axiosInstance = axios.create({
  baseURL: backend_url,
});

axiosInstance.interceptors.request.use(
  (config) => {
    config.headers.Authorization = localStorage.token
      ? "Bearer " + localStorage.token
      : {};
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

export default axiosInstance;
