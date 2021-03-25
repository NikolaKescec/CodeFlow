import "../../styles/feed.css";
import "../../styles/scoreboard.css";
import axiosInstance from "../../utils/axiosInstance";
import { useEffect, useState } from "react";
import { Button, Container, Spinner, Table } from "react-bootstrap";

const ScoreBoard = ({ text }) => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  const getUsers = async () => {
    try {
      debugger;
      let res = await axiosInstance().get(`/user/top/${text.toLowerCase()}`);
      setUsers([...res.data]);
      setLoading(false);
    } catch (e) {
      alert("Unable to fetch!");
      setUsers([]);
      setLoading(false);
    }
  };

  useEffect(() => {
    getUsers();
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
            {users.map((user, index) => {
              return (
                <tr key={user.id}>
                  <th className="align-middle">{index + 1}</th>
                  <th className="align-middle">{user.username}</th>
                  <th className="align-middle">
                    {text.toLowerCase() === "taskers"
                      ? user.taskPoints
                      : user.solutionPoints}
                  </th>
                  <th>
                    <Button variant="wine">Follow</Button>
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
