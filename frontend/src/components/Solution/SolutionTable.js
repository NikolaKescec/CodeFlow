import { useState } from "react";
import { Button, Table } from "react-bootstrap";
import { AiOutlineCheckCircle } from "react-icons/ai";
import { TiArrowSortedDown, TiArrowSortedUp } from "react-icons/ti";
import { Link } from "react-router-dom";
import Grade from "../Grade/Grade";

const SolutionTable = ({ task, solutions, loggedInUser }) => {
  const [tableSolutions, setTableSolutions] = useState([...solutions]);
  let [sortColumn, setSortColumn] = useState();

  const columnName = (column, name) => {
    if (sortColumn === undefined || sortColumn.column !== column)
      return (
        <>
          <span>{name}</span>
        </>
      );

    let icon = sortColumn.asc ? (
      <TiArrowSortedUp></TiArrowSortedUp>
    ) : (
      <TiArrowSortedDown></TiArrowSortedDown>
    );

    return (
      <>
        {icon}
        <span>{name}</span>
        {icon}
      </>
    );
  };

  const sortSolutions = (column) => {
    if (sortColumn === undefined) {
      sortColumn = { column, asc: true };
    }

    if (sortColumn.column === column) {
      sortColumn.asc = !sortColumn.asc;
    } else {
      sortColumn.column = column;
      sortColumn.asc = true;
    }

    let sortFunction;
    switch (column) {
      case 1:
        sortFunction = function (a, b) {
          let result = a.solutionId - b.solutionId;
          return result * (sortColumn.asc ? -1 : 1);
        };
        break;
      case 2:
        sortFunction = function (a, b) {
          let result = a.author.localeCompare(b.author);
          result = result * (sortColumn.asc ? -1 : 1);
          return result;
        };
        break;
      case 3:
        sortFunction = function (a, b) {
          return (
            a.language.language.localeCompare(b.language.language) *
            (sortColumn.asc ? -1 : 1)
          );
        };
        break;
      case 4:
        sortFunction = function (a, b) {
          let result = b.averageGrade - a.averageGrade;
          return result * (sortColumn.asc ? -1 : 1);
        };
        break;
      default:
        throw Error;
    }
    tableSolutions.sort(sortFunction);
    setTableSolutions([...tableSolutions]);
    setSortColumn(sortColumn);
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
          <th
            onClick={() => {
              sortSolutions(1);
            }}
            style={{ cursor: "pointer" }}
          >
            {columnName(1, "ID")}
          </th>
          <th
            onClick={() => {
              sortSolutions(2);
            }}
            style={{ cursor: "pointer" }}
          >
            {columnName(2, "Author")}
          </th>
          <th
            onClick={() => {
              sortSolutions(3);
            }}
            style={{ cursor: "pointer" }}
          >
            {columnName(3, "Language")}
          </th>
          <th
            onClick={() => {
              sortSolutions(4);
            }}
            style={{ cursor: "pointer" }}
          >
            {columnName(4, "Average grade")}
          </th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {tableSolutions.map((solution) => {
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
                {task.author === solution.author ? (
                  <span>{solution.author} (AUTHOR)</span>
                ) : (
                  solution.author
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
                      Inspect my solution
                    </Button>
                  </Link>
                ) : (
                  <Link
                    to={`/task/${solution.solvedTaskId}/solution/${solution.solutionId}`}
                  >
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
