import { Button, Card } from "react-bootstrap";
import {
  AiFillCheckCircle,
  AiOutlineCheckCircle,
  AiOutlineCode,
  AiOutlineSolution,
} from "react-icons/ai";
import Grade from "../Grade";

const TaskSnippet = ({ task }) => {
  debugger;
  return (
    <Card bg={"baby-powder"} className="mb-2 mt-1 border border-rich-black">
      <Card.Body>{task.taskText}</Card.Body>
      <Card.Footer className="d-flex">
        <span className="border-right border-rich-black text-wine p-1 mr-1">
          <strong>{task.author.username}</strong>
        </span>
        <span className="border-right border-rich-black  p-1 mr-1">
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
        <Button variant="outline-wine" className="ml-auto">
          {task.loggedInUserSolution && <AiFillCheckCircle></AiFillCheckCircle>}
          SOLVE
        </Button>
      </Card.Footer>
    </Card>
  );
};

export default TaskSnippet;
