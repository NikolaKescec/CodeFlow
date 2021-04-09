import { useEffect, useState } from "react";
import { Button, Card, Container } from "react-bootstrap";
import { useHistory } from "react-router";
import axiosInstance from "../../utils/axiosInstance";
import Grade from "../Grade/Grade";
import Spinner from "../Spinner";
import CodeMirror from "@uiw/react-codemirror";
import "codemirror/addon/comment/comment";
import "codemirror/addon/edit/matchbrackets";
import "codemirror/keymap/sublime";
import "codemirror/theme/monokai.css";

const SolutionDetails = ({ id, authDispatch, loggedInUser }) => {
  const [solution, setSolution] = useState();
  const [loading, setLoading] = useState(true);
  const history = useHistory();

  useEffect(() => {
    axiosInstance(authDispatch, history)
      .get("solution/detail/" + id)
      .then((res) => {
        console.log(res.data);
        setSolution(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  if (loading) {
    return <Spinner></Spinner>;
  }

  return (
    <Container fluid>
      <Card
        bg={"charcoal"}
        className="mb-2 mt-1 border border-rich-black text-baby-powder"
      >
        <Card.Header>
          Solution by:
          <span className=" text-baby-powder p-1 mr-1">
            <strong>{solution.author}</strong>
          </span>
          <p>
            Written in: <strong>{solution.language.language}</strong>
          </p>
        </Card.Header>
        <Card.Body>
          <CodeMirror
            value={solution.code}
            options={{
              mode: solution.language.language.toLowerCase(),
              theme: "monokai",
              keyMap: "sublime",
              readOnly: true,
            }}
          ></CodeMirror>
        </Card.Body>
        <Card.Footer>
          <div className="mb-2">
            <strong>Average grade:</strong>
            {solution.averageGrade === null ? (
              <Grade grade={0}></Grade>
            ) : (
              <Grade grade={solution.averageGrade}></Grade>
            )}
          </div>
          {solution.author !== loggedInUser.username && (
            <p>
              {solution.loggedInUserGrade ? (
                <>
                  <strong>Your grade: </strong>
                  <Grade grade={solution.loggedInUserGrade}></Grade>
                  <Button variant="rich-black" className="ml-1">
                    Edit grade
                  </Button>
                </>
              ) : (
                <>
                  <span>You havent graded yet. </span>
                  <Button variant="rich-black">Grade task</Button>
                </>
              )}
            </p>
          )}
        </Card.Footer>
      </Card>
    </Container>
  );
};

export default SolutionDetails;
