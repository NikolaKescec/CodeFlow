import authActions from "./authActions";
import axios from "axios";

// konvencije radi, dispatch se predaje kao drugi poziv funkcije (poput middlewarea)

const register = ({ username, email, password }, history) => (authDispatch) => {
  authDispatch({
    type: authActions.REGISTER_LOADING,
  });

  axios
    .post(
      "/programmer/register",
      {
        username,
        email,
        password,
      },
      { baseURL: "http://localhost:8080", withCredentials: true }
    )
    .then((res) => {
      authDispatch({
        type: authActions.REGISTER_SUCCESS,
        payload: undefined,
      });
      history.push("/");
    })
    .catch((err) => {
      authDispatch({
        type: authActions.REGISTER_ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    });
};

export default register;
