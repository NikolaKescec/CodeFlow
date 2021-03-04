import authActions from "./authActions";

const logout = (history) => (authDispatch) => {
  //CHANGE TO HTTP COOKIE REMOVE

  debugger;

  localStorage.removeItem("token");
  localStorage.removeItem("auth");
  authDispatch({
    type: authActions.LOGOUT,
  });
  history.push("/");
};

export default logout;
