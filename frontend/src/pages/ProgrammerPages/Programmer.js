import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { Redirect, useParams } from "react-router-dom";
import authActions from "../../authentication/actions/authActions";
import useAuth from "../../authentication/hook/useAuth";
import FollowButton from "../../components/FollowButton/FollowButton";
import Spinner from "../../components/Spinner";
import Feed from "../../components/Task/Feed";
import axiosInstance from "../../utils/axiosInstance";

const Programmer = () => {
  const [auth, authDispatch, history] = useAuth();
  const [programmer, setProgrammer] = useState();
  const [loading, setLoading] = useState(true);
  const { username } = useParams();

  const getProgrammer = async () => {
    try {
      let res = await axiosInstance(authDispatch, history).get(
        "/programmer/" + username
      );
      setProgrammer(res.data);
    } catch (err) {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  const preparePage = async () => {
    await getProgrammer();
    setLoading(false);
  };

  useEffect(() => {
    preparePage();
  }, []);

  if (username === auth.data.username)
    return <Redirect to="/profile"></Redirect>;

  if (loading) return <Spinner></Spinner>;

  return (
    <Container className="text-white h-100 mt-3">
      <Row>
        <Col md={3} className="bg-dark border-rich-black rounded p-2 sm-mt-2">
          <p>{username}</p>
          <hr className="bg-wine"></hr>
          <p>
            Total task points:
            <strong className="text-red-violet">{programmer.taskPoints}</strong>
          </p>
          <p>
            Total solution points:
            <strong className="text-red-violet">
              {programmer.solutionPoints}
            </strong>
          </p>
          <FollowButton
            programmerId={programmer.id}
            programmerName={username}
          ></FollowButton>
        </Col>
        <Col md={9}>
          <h2 className="text-white">Programmer's tasks</h2>
          <Feed text={username} loggedInUser={auth.data.username}></Feed>
        </Col>
      </Row>
    </Container>
  );
};

export default Programmer;
