import { useEffect, useState } from "react";
import { Button, Card, Container } from "react-bootstrap";
import ReactMarkdown from "react-markdown";
import { useHistory, useParams } from "react-router";
import { toast } from "react-toastify";
import axiosInstance from "../../utils/axiosInstance";
import Grade from "../Grade";
import Spinner from "../Spinner";

const TaskDetails = ({ id, authDispatch, loggedInUser }) => {
  const [task, setTask] = useState();
  const [loading, setLoading] = useState(true);
  const history = useHistory();

  const notify = (message) => {
    debugger;
    toast.error(message, {
      autoClose: 8000,
      position: toast.POSITION.TOP_RIGHT,
      className: "bg-wine",
    });
  };

  useEffect(() => {
    axiosInstance(authDispatch, history)
      .get("task/detail/" + id)
      .then((res) => {
        setTask(res.data);
        setLoading(false);
      })
      .catch((err) => {
        notify(err.message);
      });
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
          <p>
            <ReactMarkdown children={task.taskText}></ReactMarkdown>
          </p>
          <p className="highlight p-1 border border-wine">
            <p>
              <strong>Input format: </strong>
              <ReactMarkdown children={task.inputFormat}></ReactMarkdown>
            </p>
            <hr className="bg-wine m-0"></hr>

            <p>
              <strong>Output format: </strong>
              <ReactMarkdown children={task.outputFormat}></ReactMarkdown>
            </p>
          </p>
          <p>
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
          </p>
          <p>
            <strong>Average grade:</strong>{" "}
            <Grade grade={task.averageGrade}></Grade>
          </p>
          {task.author !== loggedInUser.username && (
            <p>
              {task.loggedInUserGrade ? (
                <>
                  <strong>Your grade: </strong>
                  <Grade grade={task.loggedInUserGrade.grade}></Grade>
                  <Button variant="wine" className="ml-1">
                    Edit grade
                  </Button>
                </>
              ) : (
                <>
                  <Button
                    variant="wine"
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
            <Button variant="wine">Edit solution</Button>
          ) : (
            <Button variant="wine">Add solution</Button>
          )}
        </Card.Footer>
      </Card>
    </Container>
  );
};

export default TaskDetails;
