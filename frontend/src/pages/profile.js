import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import logout from "../authentication/actions/logout";
import { useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";
import useAuth from "../authentication/hook/useAuth";
import useApp from "../app/hook/useApp";
import Feed from "../components/Task/Feed";

const Profile = () => {
  const [auth, authDispatch, checking] = useAuth();
  const history = useHistory();

  return (
    <Container className="text-white h-100 mt-3">
      <Row>
        <Col md={3} className="bg-dark border-rich-black rounded p-2">
          <p>User name: {auth.data.username}</p>
          <hr className="bg-wine"></hr>
          <Button variant="wine">Edit your profile</Button>
        </Col>
        <Col md={6}>
          <div className="d-flex flex-column bg-dark border-rich-black rounded p-2 h-100 justify-content-center">
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
          </div>
        </Col>
        <Col
          md={3}
          className="d-flex flex-column bg-dark border-rich-black rounded p-2 justify-content-center"
        >
          <Button variant="wine">Create a new task!</Button>
        </Col>
      </Row>
      <Container fluid className="mt-3">
        <h2 className="text-white">Your tasks</h2>
        <Feed text={auth.data.username}></Feed>
      </Container>
    </Container>
  );
};

export default Profile;
