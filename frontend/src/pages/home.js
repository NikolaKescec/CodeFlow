import { useEffect, useState } from "react";
import useAuth from "../authentication/hook/useAuth";
import { Button, Col, Container, Row } from "react-bootstrap";
import { AuthContext } from "../authentication/context/AuthProvider";
import logout from "../authentication/actions/logout";
import { Link, useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";
import useApp from "../app/hook/useApp";
import Feed from "../components/Feed";
import Scoreboard from "../components/Scoreboard";

const Home = () => {
  debugger;
  const [auth, authDispatch, checking] = useAuth();
  const [tasks, setTasks] = useState([]);
  const [users, setUsers] = useState([]);
  const [feed, setFeed] = useState("fresh");

  return (
    <Container fluid className="flex-grow-1">
      <Row className="h-100">
        <Col xs={12} md={9} className="bg-baby-powder">
          <Feed parameter={feed}></Feed>
        </Col>
        <Col xs={0} md={3}>
          <Scoreboard></Scoreboard>
        </Col>
      </Row>
    </Container>
  );
};

export default Home;
