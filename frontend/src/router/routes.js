// pages
import Register from "../pages/register";
import Welcome from "../pages/welcome";
import Error from "../pages/error";
import Home from "../pages/home";
import Profile from "../pages/profile";

const routes = [
  {
    path: "/",
    exact: true,
    component: Welcome,
    title: "Welcome",
    authenticated: false,
  },
  {
    path: "/register",
    exact: true,
    component: Register,
    title: "Register",
    authenticated: false,
  },
  {
    path: "/home",
    exact: true,
    component: Home,
    title: "Home page",
    authenticated: true,
  },
  {
    path: "/profile",
    exact: true,
    component: Profile,
    title: "Profile page",
    authenticated: true,
  },
  {
    path: "*",
    exact: true,
    component: Error,
    title: "Error page",
    authenticated: false,
  },
];

export default routes;
