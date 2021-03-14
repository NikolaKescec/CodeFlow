import { useContext } from "react";
import { Route, Redirect } from "react-router-dom";
import { isLoggedIn } from "../authentication/auth";
import { AuthContext } from "../authentication/context/AuthProvider";

const Renderer = (route) => {
  const { auth, dispatch } = useContext(AuthContext);

  debugger;

  document.title = route.title || "CodeFlow";
  if (route.authenticated && !auth.data) {
    return <Redirect to="/" />;
  }

  if ((route.path === "/" || route.path === "/register") && auth.data) {
    return <Redirect to="/home" />;
  }

  return <Route path={route.path} exact component={route.component} />;
};

export default Renderer;
