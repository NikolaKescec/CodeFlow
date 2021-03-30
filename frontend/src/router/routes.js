// pages
import Register from "../pages/Register";
import Welcome from "../pages/Welcome";
import Error from "../pages/Error";
import Home from "../pages/Home";
import Profile from "../pages/Profile";
import CreateTask from "../pages/CreateTask";

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
    path: "/create-task",
    exact: true,
    component: CreateTask,
    title: "Create task",
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
