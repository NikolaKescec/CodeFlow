import { Route, Redirect } from "react-router-dom";
import { isLoggedIn } from "../authentication/auth";

const Renderer = (route) => {
  debugger;

  document.title = route.name || "CodeFlow";
  if (route.authenticated && !isLoggedIn()) {
    return <Redirect to="/" />;
  }

  if ((route.path === "/" || route.path === "/register") && isLoggedIn()) {
    return <Redirect to="/home" />;
  }

  return <Route path={route.path} exact component={route.component} />;
};

export default Renderer;
