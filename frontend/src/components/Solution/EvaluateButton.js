import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";

const EvaluateButton = ({
  judgeId,
  code,
  testCases,
  updateReport,
  submitSolution,
  buttonText,
}) => {
  const [auth, authDispatch, history] = useAuth();
  const [evaluated, setEvaluated] = useState(false);
  const [evaluating, setEvaluating] = useState(false);
  const [report, setReport] = useState([]);

  const evaluate = async () => {
    setEvaluating(true);
    let evaluatedTests = [];
    let passed = false;
    let numbersOfFailed = 0;
    try {
      for (let i = 0; i < testCases.length; i++) {
        let res = await axiosInstance(authDispatch, history).post(
          "submissions/?wait=true",
          {
            source_code: code,
            language_id: judgeId,
            stdin: testCases[i].input,
            expected_output: testCases[i].output,
          },
          { baseURL: "http://localhost:2358/", withCredentials: false }
        );
        if (res.data.stderr) {
          throw res.data.stderr;
        }
        if (res.data.status) {
          if (res.data.status.description === "Wrong Answer") {
            numbersOfFailed++;
            evaluatedTests.push(
              "TEST FAILED: \n" +
                "EXPECTED: " +
                testCases[i].output +
                "\nGIVEN OUTPUT: " +
                res.data.stdout +
                "\n"
            );
          } else if (res.data.status.description === "Accepted") {
            evaluatedTests.push(
              "TEST PASSED: \n" +
                "EXPECTED: " +
                testCases[i].output +
                "\nGIVEN OUTPUT: " +
                res.data.stdout +
                "\n"
            );
          }
        } else {
          throw "Compilation error! Please recheck your code!";
        }
      }
    } catch (err) {
      updateReport([...report, err]);
      setEvaluating(false);
      return;
    }
    updateReport([...report, ...evaluatedTests]);
    if (numbersOfFailed === 0) passed = true;
    if (passed) {
      setEvaluated(true);
    }
    setEvaluating(false);
  };

  useEffect(() => {
    setEvaluated(false);
  }, [code]);

  if (evaluating) {
    return (
      <>
        <Button variant="danger" disabled>
          Evaluating...
        </Button>
      </>
    );
  }

  if (!evaluated) {
    return (
      <>
        <Button variant="danger" onClick={evaluate}>
          Evaluate
        </Button>
      </>
    );
  }

  return (
    <Button onClick={submitSolution} variant="rich-black">
      {buttonText}
    </Button>
  );
};

export default EvaluateButton;
