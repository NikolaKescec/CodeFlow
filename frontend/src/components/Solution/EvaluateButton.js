import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
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
}) => {
  const [auth, authDispatch, history] = useAuth();
  const [evaluated, setEvaluated] = useState(false);
  const [evaluating, setEvaluating] = useState(false);
  const [report, setReport] = useState([]);
  const [encode, decode] = useTextCoding();

  const evaluate = async () => {
    setEvaluating(true);
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
              "x-rapidapi-key":
                "eac21ae196msh68d010c8e50cd62p1e0ba0jsn77678c6d4101",
              "x-rapidapi-host": "judge0-ce.p.rapidapi.com",
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
