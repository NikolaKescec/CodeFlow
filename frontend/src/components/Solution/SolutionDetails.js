import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Dropdown, Row } from "react-bootstrap";
import axiosInstance from "../../utils/axiosInstance";
import Grade from "../Grade/Grade";
import Spinner from "../Spinner";
import useAuth from "../../authentication/hook/useAuth";
import Editor from "../Editor/Editor";
import { Link } from "react-router-dom";

const SolutionDetails = ({ id }) => {
  const [auth, authDispatch, history] = useAuth();
  const [solution, setSolution] = useState();
  const [loading, setLoading] = useState(true);

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
          <Row>
            <Col md={11}>
              Solution by:
              <span className=" text-baby-powder p-1 mr-1">
                <strong>{solution.author}</strong>
              </span>
              <p>
                Written in: <strong>{solution.language.language}</strong>
              </p>
            </Col>
            <Col>
              {auth.data.username === solution.author && (
                <Dropdown className="d-inline float-right">
                  <Dropdown.Toggle
                    variant="charcoal"
                    className="bg-charcoal text-baby-powder btn-sm border border-rich-black"
                    id="dropdown-basic"
                    split={true}
                  ></Dropdown.Toggle>

                  <Dropdown.Menu>
                    <Dropdown.Item href="#/action-1">Edit</Dropdown.Item>
                    <Dropdown.Item href="#/action-2">Delete</Dropdown.Item>
                  </Dropdown.Menu>
                </Dropdown>
              )}
            </Col>
          </Row>
        </Card.Header>
        <Card.Body>
          <Editor
            code={solution.code}
            view={true}
            mode={
              solution.language.language.toLowerCase() == "c" ||
              solution.language.language.toLowerCase() == "cpp"
                ? "c_cpp"
                : solution.language.language.toLowerCase()
            }
          ></Editor>
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
          {solution.author !== auth.data.username && (
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
                  <Button variant="rich-black">Grade solution</Button>
                </>
              )}
            </p>
          )}
          <hr></hr>
          <Link
            to={"/task/" + solution.solvedTaskId}
            className="d-inline float-right"
          >
            <Button variant="rich-black">Return to task</Button>
          </Link>
        </Card.Footer>
      </Card>
    </Container>
  );
};

export default SolutionDetails;
