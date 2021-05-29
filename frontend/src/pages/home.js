import { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import useAuth from "../authentication/hook/useAuth";
import Feed from "../components/Task/Feed";
import FeedButton from "../components/Task/FeedButton";
import ScoreBoard from "../components/Users/ScoreBoard";
import "../styles/home.css";

const Home = () => {
  const [auth, authDispatch, history] = useAuth();
  const [userFeed, setUserFeed] = useState("Taskers");
  const [taskFeed, setTaskFeed] = useState("recommended");

  const changeFunctionTasks = (text) => {
    setTaskFeed(text);
  };

  const changeFunctionUsers = (text) => {
    setUserFeed(text);
  };

  return (
    <>
      <Row className="d-flex h-100">
        <Col
          xs={12}
          md={9}
          className="bg-wine-darker p-0 border-lg border-right border-rich-black flex-grow-1 d-flex column"
        >
          <Container
            fluid
            className="d-flex m-0 p-0 justify-content-between text-center border-bottom border-rich-black"
          >
            <FeedButton
              name={"recommended"}
              link={"recommended"}
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
              name={"fresh"}
              link={"fresh"}
              selectFunction={changeFunctionTasks}
              activeElement={taskFeed}
            ></FeedButton>
          </Container>
          <div
            className="flex-grow-1"
            style={{ height: "500px", overflow: "auto" }}
          >
            <Feed text={taskFeed} loggedInUser={auth.data.username}></Feed>
          </div>
        </Col>
        <Col xs={0} md={3} className="d-none d-md-inline p-0 bg-wine-darker">
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
    </>
  );
};

export default Home;
