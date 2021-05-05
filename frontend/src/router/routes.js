// pages
import Register from "../pages/Register";
import Welcome from "../pages/Welcome";
import Error from "../pages/Error";
import Home from "../pages/Home";
import Profile from "../pages/ProfilePages/Profile";
import CreateTask from "../pages/TaskPages/CreateTask";
import Task from "../pages/TaskPages/Task";
import Solution from "../pages/SolutionPages/Solution";
import CreateSolution from "../pages/SolutionPages/CreateSolution";
import UpdateSolution from "../pages/SolutionPages/UpdateSolution";
import EditProfile from "../pages/ProfilePages/EditProfile";
import UpdateTask from "../pages/TaskPages/UpdateTask";
import Programmer from "../pages/ProgrammerPages/Programmer";

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
    path: "/edit-profile",
    exact: true,
    component: EditProfile,
    title: "Edit profile",
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
    path: "/task/:taskId",
    exact: true,
    component: Task,
    title: "Inspect task",
    authenticated: true,
  },
  {
    path: "/task/update-task/:taskId",
    exact: true,
    component: UpdateTask,
    title: "Update task",
    authenticated: true,
  },
  {
    path: "/task/:taskId/solution/:solutionId",
    exact: true,
    component: Solution,
    title: "Inspect solution",
    authenticated: true,
  },
  {
    path: "/task/:taskId/create-solution",
    exact: true,
    component: CreateSolution,
    title: "Create solution for task",
    authenticated: true,
  },
  {
    path: "/task/:taskId/update-solution/:solutionId",
    exact: true,
    component: UpdateSolution,
    title: "Update solution for task",
    authenticated: true,
  },
  {
    path: "/programmer/:username",
    exact: true,
    component: Programmer,
    title: "Programmer",
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
