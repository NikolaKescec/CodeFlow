import { useState } from "react";
import { Button, Collapse } from "react-bootstrap";
import SolutionTaskPreview from "./SolutionTaskPreview";

const TaskAndConsoleView = ({ report, task }) => {
  const [openDetails, setOpenDetails] = useState(true);
  const [openReport, setOpenReport] = useState(false);

  return (
    <>
      <Button
        className="mt-1"
        onClick={() => setOpenDetails(!openDetails)}
        block
        variant="wine"
        aria-controls="preview"
        aria-expanded={openDetails}
      >
        Inspect task details
      </Button>
      <Collapse in={openDetails}>
        <div id="preview">
          <SolutionTaskPreview task={task}></SolutionTaskPreview>
        </div>
      </Collapse>
      <hr className=""></hr>
      <Button
        onClick={() => setOpenReport(!openReport)}
        block
        variant="wine"
        aria-controls="reports"
        aria-expanded={openReport}
      >
        Console output
      </Button>
      <Collapse in={openReport}>
        <div id="reports">
          <div className="bg-rich-black mt-1 text-white rounded p-2">
            {report.map((line) => {
              return <div>> {line}</div>;
            })}
          </div>{" "}
        </div>
      </Collapse>
    </>
  );
};

export default TaskAndConsoleView;
