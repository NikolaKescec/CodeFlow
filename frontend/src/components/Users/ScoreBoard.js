import "../../styles/feed.css";
import "../../styles/scoreboard.css";
import axiosInstance from "../../utils/axiosInstance";
import { useEffect, useState } from "react";
import { Button, Container, Spinner, Table } from "react-bootstrap";
import useAuth from "../../authentication/hook/useAuth";
import FollowButton from "../FollowButton/FollowButton";

const ScoreBoard = ({ text }) => {
  const [auth, authDispatch, history] = useAuth();
  const [programmers, setProgrammers] = useState([]);
  const [followed, setFollowed] = useState([]);
  const [loading, setLoading] = useState(true);

  const getProgrammersAndFollowed = async () => {
    try {
      debugger;
      let resProgrammers = await axiosInstance().get(
        `/programmer/top/${text.toLowerCase()}`
      );
      setProgrammers([...resProgrammers.data]);

      let resFollowed = await axiosInstance().get(`/programmer/followed`);
      setFollowed([...resFollowed.data]);
      setLoading(false);
    } catch (e) {
      alert("Unable to fetch!");
      setProgrammers([]);
      setLoading(false);
    }
  };

  useEffect(() => {
    getProgrammersAndFollowed();
  }, [text]);

  return (
    <Container className="scrollable-feed p-2">
      {loading && <Spinner></Spinner>}
      {!loading && (
        <Table
          striped
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
                  <th className="align-middle">{programmer.username}</th>
                  <th className="align-middle">
                    {text.toLowerCase() === "taskers"
                      ? programmer.taskPoints
                      : programmer.solutionPoints}
                  </th>
                  <th>
                    {auth.data.id !== programmer.id && (
                      <FollowButton
                        programmerName={programmer.username}
                        programmerId={programmer.id}
                        followed={followed}
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
