import "../../styles/feed.css";
import "../../styles/scoreboard.css";
import axiosInstance from "../../utils/axiosInstance";
import { useEffect, useState } from "react";
import { Button, Container, Spinner, Table } from "react-bootstrap";

const ScoreBoard = ({ text }) => {
  const [programmers, setProgrammers] = useState([]);
  const [loading, setLoading] = useState(true);

  const getProgrammers = async () => {
    try {
      debugger;
      let res = await axiosInstance().get(
        `/programmer/top/${text.toLowerCase()}`
      );
      setProgrammers([...res.data]);
      setLoading(false);
    } catch (e) {
      alert("Unable to fetch!");
      setProgrammers([]);
      setLoading(false);
    }
  };

  useEffect(() => {
    getProgrammers();
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
                    <Button variant="wine" className="border border-rich-black">
                      Follow
                    </Button>
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
