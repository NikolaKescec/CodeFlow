import { Button, Table } from "react-bootstrap";
import { AiOutlineCheckCircle } from "react-icons/ai";
import { Link } from "react-router-dom";
import Grade from "../Grade";

const SolutionTable = ({ task, solutions, changeSolutions, loggedInUser }) => {
  const sortSolutions = (column) => {
    //TODO
  };

  return (
    <Table
      striped
      bordered
      hover
      responsive
      size="sm"
      variant="dark"
      className="bg-charcoal text-baby-powder rounded border-rich-black"
    >
      <thead>
        <tr>
          <th>ID</th>
          <th>Author</th>
          <th>Language</th>
          <th>Average grade</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {solutions.map((solution) => {
          return (
            <tr key={solution.solutionId + "-" + solution.author.username}>
              <td>
                {task.author === solution.author.username ? (
                  <span className="text-rich-black">{solution.solutionId}</span>
                ) : (
                  solution.solutionId
                )}
              </td>
              <td>
                {task.author === solution.author.username ? (
                  <span>{solution.author.username} (AUTHOR)</span>
                ) : (
                  solution.author.username
                )}
              </td>
              <td>{solution.language.language}</td>
              <td>
                {solution.averageGrade === null ? (
                  <Grade grade={0}></Grade>
                ) : (
                  <Grade grade={solution.averageGrade}></Grade>
                )}
                {solution.loggedInUserGrade && (
                  <span>
                    (<AiOutlineCheckCircle></AiOutlineCheckCircle>)
                  </span>
                )}
              </td>
              <td>
                {loggedInUser.id === solution.author.id ? (
                  <Link to={"edit-solution/" + solution.solutionId}>
                    <Button variant="rich-black" block>
                      Edit
                    </Button>
                  </Link>
                ) : (
                  <Link to={"solution/" + solution.solutionId}>
                    <Button variant="rich-black" block>
                      Inspect
                    </Button>
                  </Link>
                )}
              </td>
            </tr>
          );
        })}
      </tbody>
    </Table>
  );
};

export default SolutionTable;
