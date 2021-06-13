import React from "react";
import { BrowserRouter as Router, Switch } from "react-router-dom";
import Renderer from "./renderer";
import routes from "./routes";

const CodeFlowRouter = () => {
  return (
    <Router>
      <Switch>
        {routes.map((route, index) => (
          <Renderer {...route} key={index} />
        ))}
      </Switch>
    </Router>
  );
};

export default CodeFlowRouter;
