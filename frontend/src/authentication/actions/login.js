import authActions from "./authActions";
import axiosInstance from "../../utils/axiosInstance";

// konvencije radi, dispatch se predaje kao drugi poziv funkcije (poput middlewarea)

const login = ({ username, password }) => (authDispatch) => {
  authDispatch({
    type: authActions.LOGIN_LOADING,
  });

  axiosInstance
    .post("/authenticate", {
      username,
      password,
    })
    .then((res) => {
      //WILL CHANGE TO HTTP COOKIE
      localStorage.token = res.data.jwt;

      authDispatch({
        type: authActions.LOGIN_SUCCESS,
        payload: res.data,
      });
    })
    .catch((err) => {
      authDispatch({
        type: authActions.LOGIN_ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    });
};

export default login;
