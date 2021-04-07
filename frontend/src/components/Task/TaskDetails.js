import { useEffect, useState } from "react";
import { Button, Card, Container } from "react-bootstrap";
import ReactMarkdown from "react-markdown";
import { useHistory, useParams } from "react-router";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "../../utils/axiosInstance";
import Grade from "../Grade";
import SolutionTable from "../Solution/SolutionTable";
import Spinner from "../Spinner";

const TaskDetails = ({ id, authDispatch, loggedInUser }) => {
  const [task, setTask] = useState();
  const [loading, setLoading] = useState(true);
  const history = useHistory();
  const [solutions, setSolutions] = useState();

  const getTaskAndSolutions = async () => {
    try {
      let resTask = await axiosInstance(authDispatch, history).get(
        "task/detail/" + id
      );
      setTask(resTask.data);
      let resSolutions = await axiosInstance(authDispatch, history).get(
        "task/solutions/" + id
      );
      setSolutions(resSolutions.data);
      setLoading(false);
    } catch (e) {
      toast(e.message);
    }
  };

  console.log(solutions);

  useEffect(() => {
    getTaskAndSolutions();
  }, []);

  if (loading) return <Spinner></Spinner>;

  return (
    <Container fluid>
      <Card
        bg={"charcoal"}
        className="mb-2 mt-1 border border-rich-black text-baby-powder"
      >
        <Card.Header>
          Task created by:
          <span className=" text-baby-powder p-1 mr-1">
            <strong>{task.author}</strong>
          </span>
        </Card.Header>
        <Card.Body>
          <div>
            <ReactMarkdown children={task.taskText}></ReactMarkdown>
          </div>
          <div className="highlight p-1 border border-wine mb-3">
            <div>
              <strong>Input format: </strong>
              <ReactMarkdown children={task.inputFormat}></ReactMarkdown>
            </div>
            <hr className="bg-wine m-0"></hr>
            <div>
              <strong>Output format: </strong>
              <ReactMarkdown children={task.outputFormat}></ReactMarkdown>
            </div>
          </div>
          <div className="mb-2">
            <span>
              <strong>Allowed languages: </strong>
            </span>
            {task.writtenIn.map((lang) => {
              return (
                <span key={lang.languageId + lang.language}>
                  {lang.language}{" "}
                </span>
              );
            })}
          </div>
          <hr className="bg-wine"></hr>
          <div className="mb-2">
            <strong>Average grade:</strong>{" "}
            {task.averageGrade === null ? (
              <Grade grade={0}></Grade>
            ) : (
              <Grade grade={task.averageGrade}></Grade>
            )}{" "}
          </div>
          {task.author !== loggedInUser.username && (
            <p>
              {task.loggedInUserGrade ? (
                <>
                  <strong>Your grade: </strong>
                  <Grade grade={task.loggedInUserGrade}></Grade>
                  <Button variant="rich-black" className="ml-1">
                    Edit grade
                  </Button>
                </>
              ) : (
                <>
                  <span>You havent graded yet. </span>
                  <Button
                    variant="rich-black"
                    disabled={task.loggedInUserSolution !== undefined}
                  >
                    Grade task
                  </Button>
                </>
              )}
            </p>
          )}
        </Card.Body>
        <Card.Footer className="d-flex">
          {task.loggedInUserSolution ? (
            <Button variant="rich-black">Inspect my solution</Button>
          ) : (
            <Button variant="rich-black">Add solution</Button>
          )}
        </Card.Footer>
      </Card>
      {task.authorSolution && (
        <Card
          bg={"charcoal"}
          className="mb-2 mt-1 border border-rich-black text-baby-powder"
        >
          <Card.Body className="text-center">
            Check out task author's{" "}
            <Link to={"solution/" + task.authorSolution}>solution</Link>.
          </Card.Body>
        </Card>
      )}
      {solutions.length !== 0 && (
        <SolutionTable
          solutions={solutions}
          changeSolutions={setSolutions}
          loggedInUser={loggedInUser}
          task={task}
        ></SolutionTable>
      )}
    </Container>
  );
};

export default TaskDetails;
