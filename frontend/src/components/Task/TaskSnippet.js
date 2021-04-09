import { Button, Card } from "react-bootstrap";
import {
  AiFillCheckCircle,
  AiOutlineCheckCircle,
  AiOutlineCode,
  AiOutlineSolution,
} from "react-icons/ai";
import { Link } from "react-router-dom";
import Grade from "../Grade/Grade";

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
      <Card.Footer className="d-flex">
        <span className=" text-baby-powder p-1 mr-1">
          <strong>{task.author}</strong>
        </span>
        <span className="  p-1 mr-1">
          {task.averageGrade == null ? (
            <Grade grade={0}></Grade>
          ) : (
            <Grade grade={task.averageGrade}></Grade>
          )}
          {task.loggedInUserGrade && (
            <span>
              (<AiOutlineCheckCircle></AiOutlineCheckCircle>)
            </span>
          )}
        </span>

        <div className="ml-auto">
          <span>
            {task.loggedInUserSolution && loggedInUser !== task.author && (
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
