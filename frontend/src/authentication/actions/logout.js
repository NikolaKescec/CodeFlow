import axiosInstance from "../../utils/axiosInstance";
import authActions from "./authActions";

const logout = (history) => (authDispatch) => {
  //CHANGE TO HTTP COOKIE REMOVE

  localStorage.removeItem("auth");

  authDispatch({
    type: authActions.LOGOUT,
  });

  axiosInstance(authDispatch, history)
    .get("/deauthenticate")
    .then((res) => history.push("/"))
    .catch((err) => {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    });
};

export default logout;
