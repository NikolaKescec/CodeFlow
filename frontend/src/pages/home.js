import { useEffect, useState } from "react";
import useAuth from "../authentication/hook/useAuth";
import { Button, Col, Container, Row } from "react-bootstrap";
import { AuthContext } from "../authentication/context/AuthProvider";
import logout from "../authentication/actions/logout";
import { Link, useHistory } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";

const Home = () => {
  debugger;
  const [auth, dispatch, checking] = useAuth();
  const [users, setUsers] = useState([]);

  const getUsers = async () => {
    debugger;
    try {
      let res = await axiosInstance(dispatch).get("/user");
      setUsers(res.data);
    } catch (err) {}
  };

  useEffect(() => {
    if (!checking) getUsers();
  }, [checking]);

  return (
    <Container fluid className="flex-grow-1">
      <Row className="h-100">
        <Col xs={12} md={9} className="bg-baby-powder">
          <div>
            <p>Dobar dan gospodo!</p>
          </div>
        </Col>
        <Col xs={0} md={3}></Col>
      </Row>
    </Container>
  );
};

export default Home;
