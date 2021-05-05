import { Button, Card } from "react-bootstrap";
import {
  AiFillCheckCircle,
  AiOutlineCheckCircle,
  AiOutlineCode,
  AiOutlineSolution,
} from "react-icons/ai";
import { Link } from "react-router-dom";
import Grade from "../Grade/Grade";
import LinkToUser from "../Users/LinkToUser";

const TaskSnippet = ({ task, loggedInUser }) => {
  debugger;

  const inspectUser = () => {
    if (task.author === loggedInUser) return <span>INSPECT</span>;
    if (task.loggedInUserSolution) return <span>SOLVED</span>;
    else return <span>SOLVE</span>;
  };

  return (
    <Card
      bg={"charcoal"}
      className="mb-2 mt-1 border border-rich-black text-baby-powder"
    >
      <Card.Body>{task.taskText}</Card.Body>
      <Card.Footer className="d-flex ">
        <span className=" text-baby-powder p-1 mr-1">
          <LinkToUser name={task.author}></LinkToUser>
        </span>
        <span className=" p-1 mr-1 d-flex">
          {task.averageGrade == null ? (
            <Grade grade={0}></Grade>
          ) : (
            <Grade grade={task.averageGrade}></Grade>
          )}
          {task.loggedInUserGrade && (
            <span className="mb-2 align-top">
              (<AiOutlineCheckCircle></AiOutlineCheckCircle>)
            </span>
          )}
        </span>

        <div className="ml-auto">
          <span>
            {task.loggedInUserSolution && (
              <AiFillCheckCircle className="ml-auto mr-2"></AiFillCheckCircle>
            )}
          </span>
          <Link to={"/task/" + task.taskId}>
            <Button variant="rich-black">{inspectUser()}</Button>
          </Link>
        </div>
      </Card.Footer>
    </Card>
  );
};

export default TaskSnippet;
