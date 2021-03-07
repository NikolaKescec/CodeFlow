import React from "react";
// react router
import { BrowserRouter as Router, Switch } from "react-router-dom";

// import Route renderer
import Renderer from "./renderer";

// Layout
import TopBar from "../components/TopBar";
import Footer from "../components/Footer";

// pages put in a list
import routes from "./routes";

import { Container } from "react-bootstrap";

const CodeFlowRouter = () => {
  return (
    <Router>
      <Container
        fluid
        className="d-flex justify-content-between column full-height pattern p-0"
      >
        <TopBar></TopBar>

        <Switch>
          {routes.map((route, index) => (
            <Renderer {...route} key={index} />
          ))}
        </Switch>
        <Footer></Footer>
      </Container>
    </Router>
  );
};

export default CodeFlowRouter;
