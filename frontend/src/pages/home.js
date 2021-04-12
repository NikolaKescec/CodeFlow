import { useEffect, useState } from "react";
import useAuth from "../authentication/hook/useAuth";
import {
  Button,
  ButtonGroup,
  Col,
  Container,
  Row,
  Spinner,
} from "react-bootstrap";
import { AuthContext } from "../authentication/context/AuthProvider";
import logout from "../authentication/actions/logout";
import { Link, useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";
import useApp from "../app/hook/useApp";
import ScoreBoard from "../components/Users/ScoreBoard";
import Feed from "../components/Task/Feed";
import FeedButton from "../components/Task/FeedButton";

import "../styles/home.css";

const Home = () => {
  debugger;
  const [auth, authDispatch, history] = useAuth();
  const [userFeed, setUserFeed] = useState("Taskers");
  const [taskFeed, setTaskFeed] = useState("fresh");

  const changeFunctionTasks = (text) => {
    console.log(text);
    setTaskFeed(text);
  };

  const changeFunctionUsers = (text) => {
    console.log(text);
    setUserFeed(text);
  };

  return (
    <Container fluid className="flex-grow-1">
      <Row className="h-100">
        <Col
          xs={12}
          md={9}
          className="home-pattern p-0 border-lg border-right border-rich-black"
        >
          <Container
            fluid
            className="d-flex m-0 p-0 justify-content-between text-center"
          >
            <FeedButton
              name={"fresh"}
              link={"fresh"}
              selectFunction={changeFunctionTasks}
              activeElement={taskFeed}
            ></FeedButton>
            <FeedButton
              name={"following"}
              link={"following"}
              selectFunction={changeFunctionTasks}
              activeElement={taskFeed}
              middle={true}
            ></FeedButton>
            <FeedButton
              name={"best"}
              link={"best"}
              selectFunction={changeFunctionTasks}
              activeElement={taskFeed}
            ></FeedButton>
          </Container>
          <hr className="bg-rich-black p-0 mt-1 mb-0"></hr>
          <div className="scrollable-feed">
            <Feed text={taskFeed} loggedInUser={auth.data.username}></Feed>
          </div>
        </Col>
        <Col xs={0} md={3} className="d-none d-md-inline p-0 home-pattern">
          <Container
            fluid
            className="d-flex m-0 p-0 justify-content-between text-center"
          >
            <FeedButton
              name={"Taskers"}
              link={"Taskers"}
              selectFunction={changeFunctionUsers}
              activeElement={userFeed}
            ></FeedButton>
            <FeedButton
              name={"Solvers"}
              link={"Solvers"}
              selectFunction={changeFunctionUsers}
              activeElement={userFeed}
            ></FeedButton>
          </Container>
          <ScoreBoard text={userFeed}></ScoreBoard>
        </Col>
      </Row>
    </Container>
  );
};

export default Home;
