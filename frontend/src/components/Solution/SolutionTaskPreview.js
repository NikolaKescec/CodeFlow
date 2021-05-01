import { useState } from "react";
import { Button, Card, Collapse, Container } from "react-bootstrap";
import ReactMarkdown from "react-markdown";
import Grade from "../Grade/Grade";

const SolutionTaskPreview = ({ task }) => {
  const [open, setOpen] = useState(false);

  return (
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
          )}
        </div>
        <hr className="bg-wine"></hr>
        <div className="text-center highlight">
          <Button
            onClick={() => setOpen(!open)}
            aria-controls="test"
            aria-expanded={open}
            variant={"rich-black"}
            block
          >
            See tests
          </Button>
        </div>
        <Collapse in={open}>
          <div id="test">
            {task.testCases.map((testCase) => {
              return (
                <>
                  <hr></hr>
                  <p>Input and output: </p>
                  <p className="highlight">Input: {testCase.input}</p>
                  <p className="highlight">Output: {testCase.output}</p>
                </>
              );
            })}
          </div>
        </Collapse>
      </Card.Body>
    </Card>
  );
};

export default SolutionTaskPreview;
