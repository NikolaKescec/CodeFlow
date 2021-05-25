import { useEffect, useState } from "react";
import { Button, Modal, Spinner } from "react-bootstrap";
import useTextCoding from "../../app/hook/useTextCoding";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";

const EvaluateButton = ({
  judgeId,
  code,
  testCases,
  updateReport,
  submitSolution,
  buttonText,
  modifiedButtonText,
  modify,
  setModify,
}) => {
  const [auth, authDispatch, history] = useAuth();
  const [evaluated, setEvaluated] = useState(false);
  const [evaluating, setEvaluating] = useState(false);
  const [report, setReport] = useState([]);
  const [encode, decode] = useTextCoding();

  const evaluate = async () => {
    setEvaluating(true);
    setModify(true);
    let evaluatedTests = [];
    let passed = false;
    let numbersOfFailed = 0;
    try {
      for (let i = 0; i < testCases.length; i++) {
        let res = await axiosInstance(authDispatch, history).post(
          "/submissions/?wait=true&base64_encoded=true",
          {
            source_code: encode(code),
            language_id: judgeId,
            stdin: encode(testCases[i].input),
            expected_output: encode(testCases[i].output),
          },
          {
            headers: {
              "content-type": "application/json",
              "x-rapidapi-key": process.env.REACT_APP_JUDGE0_API_KEY,
              "x-rapidapi-host": process.env.REACT_APP_JUDGE0_HOST,
              useQueryString: true,
            },
            baseURL: process.env.REACT_APP_JUDGE0_SERVER_URL,
            withCredentials: false,
          }
        );
        if (res.data.stderr) {
          throw decode(res.data.stderr);
        }
        if (res.data.status) {
          switch (res.data.status.description) {
            case "Wrong Answer":
              numbersOfFailed++;
              evaluatedTests.push(
                "TEST FAILED: \n" +
                  "EXPECTED: " +
                  testCases[i].output +
                  "\nGIVEN OUTPUT: " +
                  decode(res.data.stdout) +
                  "\n"
              );
              break;
            case "Accepted":
              evaluatedTests.push(
                "TEST PASSED: \n" +
                  "EXPECTED: " +
                  testCases[i].output +
                  "\nGIVEN OUTPUT: " +
                  decode(res.data.stdout) +
                  "\n"
              );
              break;
            case "Compilation Error":
              throw decode(res.data.compile_output);
            default:
              throw "Compilation error! Please recheck your code!";
          }
        } else {
          throw "Compilation error! Please recheck your code!";
        }
      }
    } catch (err) {
      updateReport([...report, err]);
      setEvaluating(false);
      setModify(false);
      return;
    }
    updateReport([...report, ...evaluatedTests]);
    if (numbersOfFailed === 0) passed = true;
    if (passed) {
      setEvaluated(true);
    }
    setEvaluating(false);
    setModify(false);
  };

  useEffect(() => {
    setEvaluated(false);
  }, [code]);

  if (evaluating) {
    return (
      <>
        <Button variant="danger" disabled className="d-flex align-items-center">
          Evaluating
          <Spinner
            as="span"
            animation="border"
            size="sm"
            role="status"
            aria-hidden="true"
            className="ml-1"
          />
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

  if (modify) {
    return (
      <>
        <Button variant="danger" disabled className="d-flex align-items-center">
          {modifiedButtonText}
          <Spinner
            as="span"
            animation="grow"
            size="sm"
            role="status"
            aria-hidden="true"
            className="ml-1"
          />
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
