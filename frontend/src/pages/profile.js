import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import logout from "../authentication/actions/logout";
import { Link, useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";
import useAuth from "../authentication/hook/useAuth";
import useApp from "../app/hook/useApp";
import Feed from "../components/Task/Feed";
import FeedButton from "../components/Task/FeedButton";

const Profile = () => {
  const [auth, authDispatch, history] = useAuth();
  const [text, setText] = useState(auth.data.username);

  const changeText = (newText) => {
    setText(newText);
  };

  return (
    <Container className="text-white h-100 mt-3">
      <Row>
        <Col md={3} className="bg-dark border-rich-black rounded p-2 sm-mt-2">
          <p>Programmer name: {auth.data.username}</p>
          <hr className="bg-wine"></hr>
          <Link to="edit-profile">
            <Button variant="wine" className="w-100">
              Edit your profile
            </Button>
          </Link>
        </Col>
        <Col md={6}>
          <div className="d-flex flex-column bg-dark border-rich-black rounded p-2 h-100 justify-content-center sm-mt-2">
            <p>
              Total task points:
              <strong className="text-red-violet">
                {auth.data.taskPoints}
              </strong>
            </p>
            <p>
              Total solution points:
              <strong className="text-red-violet">
                {auth.data.solutionPoints}
              </strong>
            </p>
            <div>
              <FeedButton
                name="Your tasks"
                link={auth.data.username}
                selectFunction={changeText}
                activeElement={text}
              ></FeedButton>
              <FeedButton
                name="Solved tasks"
                link={"solved/" + auth.data.username}
                selectFunction={changeText}
                activeElement={text}
              ></FeedButton>
            </div>
          </div>
        </Col>
        <Col
          md={3}
          className="d-flex flex-column bg-dark border-rich-black rounded p-2 justify-content-center sm-mt-2"
        >
          <Link to="/create-task">
            <Button variant="wine" className="w-100">
              Create a new task!
            </Button>
          </Link>
        </Col>
      </Row>
      <Container fluid className="mt-3">
        <h2 className="text-white">Selected tasks</h2>
        <Feed text={text} loggedInUser={auth.data.username}></Feed>
      </Container>
    </Container>
  );
};

export default Profile;
