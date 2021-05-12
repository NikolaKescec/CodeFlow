import "../../styles/feed.css";
import "../../styles/scoreboard.css";
import axiosInstance from "../../utils/axiosInstance";
import { useEffect, useState } from "react";
import { Button, Container, Table } from "react-bootstrap";
import useAuth from "../../authentication/hook/useAuth";
import FollowButton from "../FollowButton/FollowButton";
import authActions from "../../authentication/actions/authActions";
import Spinner from "../Spinner";
import LinkToUser from "./LinkToUser";

const ScoreBoard = ({ text }) => {
  const [auth, authDispatch, history] = useAuth();
  const [programmers, setProgrammers] = useState([]);
  const [loading, setLoading] = useState(true);

  const getProgrammers = async () => {
    try {
      debugger;
      let resProgrammers = await axiosInstance().get(
        `/programmer/top/${text.toLowerCase()}`
      );
      setProgrammers([...resProgrammers.data]);
      setLoading(false);
    } catch (err) {
      setProgrammers([]);
      setLoading(false);
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  useEffect(() => {
    getProgrammers();
  }, [text]);

  return (
    <Container className="p-2">
      {loading && <Spinner></Spinner>}
      {!loading && (
        <Table
          striped
          responsive={true}
          size="sm"
          variant="charcoal"
          className="bg-charcoal text-baby-powder rounded"
        >
          <thead>
            <tr>
              <th>Position</th>
              <th>Username</th>
              <th>Points</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {programmers.map((programmer, index) => {
              return (
                <tr key={programmer.id}>
                  <th className="align-middle">{index + 1}</th>
                  <th className="align-middle">
                    {" "}
                    <LinkToUser name={programmer.username}></LinkToUser>
                  </th>
                  <th className="align-middle">
                    {text.toLowerCase() === "taskers"
                      ? programmer.taskPoints
                      : programmer.solutionPoints}
                  </th>
                  <th>
                    {auth.data.id !== programmer.id && (
                      <FollowButton
                        programmerId={programmer.id}
                        programmerName={programmer.username}
                      ></FollowButton>
                    )}
                  </th>
                </tr>
              );
            })}
          </tbody>
        </Table>
      )}
    </Container>
  );
};

export default ScoreBoard;
