import authActions from "./authActions";
import axiosInstance from "../../utils/axiosInstance";

const logout = (history) => (authDispatch) => {
  //CHANGE TO HTTP COOKIE REMOVE

  debugger;

  localStorage.removeItem("auth");

  authDispatch({
    type: authActions.LOGOUT,
  });

  axiosInstance(authDispatch, history)
    .get("/deauthenticate")
    .then((res) => history.push("/"))
    .catch((err) => console.log(err));
};

export default logout;
