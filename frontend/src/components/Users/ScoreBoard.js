import "../../styles/spinner.css";
import "../../styles/feed.css";
import axiosInstance from "../../utils/axiosInstance";
import { useEffect, useState } from "react";
import { Button, Container, Table } from "react-bootstrap";

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
    <Container className="scrollable-feed p-0">
      {loading && <div class="loader">Loading...</div>}
      {!loading && (
        <Table bordered hover variant="darker-wine text-baby-powder">
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
                  <th>{index}</th>
                  <th>{user.username}</th>
                  <th>
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
