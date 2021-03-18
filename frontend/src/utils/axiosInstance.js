import axios from "axios";
import authActions from "../authentication/actions/authActions";

const axiosInstance = (dispatch = null, history = null) => {
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
      const originalRequest = error.config;
      debugger;
      if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        await axiosInstance.get("/refresh");
        return axiosInstance(originalRequest);
      }
      if (error.response.status === 403) {
        debugger;
        await axiosInstance.get("/deauthenticate");
        if (dispatch) {
          dispatch({ type: authActions.LOGOUT });
        } else {
        }
        if (history) {
          history.push("/");
        } else {
          window.location = "/";
        }
      } else {
        return Promise.reject(error);
      }
    }
  );

  return axiosInstance;
};

export default axiosInstance;
