import { useEffect, useState } from "react";
import { Button, Card, Container, Dropdown } from "react-bootstrap";
import ReactMarkdown from "react-markdown";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "../../utils/axiosInstance";
import Grade from "../Grade/Grade";
import SolutionTable from "../Solution/SolutionTable";
import Spinner from "../Spinner";
import useAuth from "../../authentication/hook/useAuth";

const TaskDetails = ({ id }) => {
  const [task, setTask] = useState();
  const [loading, setLoading] = useState(true);
  const [auth, authDispatch, history] = useAuth();
  const [solutions, setSolutions] = useState();

  const deleteTask = () => {
    axiosInstance(authDispatch, history)
      .delete("/task/delete-task/" + task.taskId)
      .then((res) => {
        history.push("/home");
      })
      .catch((err) => {
        alert(err.message);
      });
  };

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
          <Container fluid className="p-0">
            <span>
              Task created by:
              <span className=" text-baby-powder p-1 mr-1">
                <strong>{task.author}</strong>
              </span>
            </span>
            {auth.data.username === task.author && (
              <Dropdown className="d-inline float-right">
                <Dropdown.Toggle
                  variant="charcoal"
                  className="bg-charcoal text-baby-powder btn-sm border border-rich-black"
                  id="dropdown-basic"
                  split={true}
                ></Dropdown.Toggle>

                <Dropdown.Menu>
                  <Dropdown.Item
                    as={Link}
                    to={"/task/update-task/" + task.taskId}
                  >
                    Edit
                  </Dropdown.Item>
                  <Dropdown.Item onClick={deleteTask}>Delete</Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
            )}
          </Container>
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
          {task.author !== auth.data.username && (
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
                  <span>You haven't graded yet. </span>
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
            <Link
              to={`/task/${task.taskId}/solution/` + task.loggedInUserSolution}
            >
              <Button variant="rich-black">Inspect my solution</Button>
            </Link>
          ) : (
            <Link to={`/task/${task.taskId}/create-solution`}>
              <Button variant="rich-black">Add solution</Button>
            </Link>
          )}
        </Card.Footer>
      </Card>
      {task.authorSolution && task.author !== auth.data.username && (
        <Card
          bg={"charcoal"}
          className="mb-2 mt-1 border border-rich-black text-baby-powder"
        >
          <Card.Body className="text-center">
            Check out task author's{" "}
            <Link
              to={`/task/${task.taskId}/solution/` + task.authorSolution}
              className="text-rich-black"
            >
              solution
            </Link>
            .
          </Card.Body>
        </Card>
      )}
      {solutions.length !== 0 && (
        <SolutionTable
          solutions={solutions}
          changeSolutions={setSolutions}
          loggedInUser={auth.data}
          task={task}
        ></SolutionTable>
      )}
    </Container>
  );
};

export default TaskDetails;
