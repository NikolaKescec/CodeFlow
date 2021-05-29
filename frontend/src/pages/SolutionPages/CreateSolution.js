import { FormControl, makeStyles, MenuItem, Select } from "@material-ui/core";
import { useEffect, useState } from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import { useParams } from "react-router";
import themes from "../../app/codethemes/codethemes";
import authActions from "../../authentication/actions/authActions";
import useAuth from "../../authentication/hook/useAuth";
import Editor from "../../components/Editor/Editor";
import EvaluateButton from "../../components/Solution/EvaluateButton";
import TaskAndConsoleView from "../../components/Solution/TaskAndConsoleView";
import Spinner from "../../components/Spinner";
import axiosInstance from "../../utils/axiosInstance";

const useStyles = makeStyles({
  select: {
    "& ul": {
      backgroundColor: "#40434e",
    },
    "& li": {
      fontSize: 12,
    },
  },
});

const CreateSolution = () => {
  const [auth, authDispatch, history] = useAuth();
  const { taskId } = useParams();

  const [task, setTask] = useState();
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);

  const classes = useStyles();
  const [theme, setTheme] = useState("vibrant_ink");
  const [mode, setMode] = useState("java");

  const [languageId, setLanguageId] = useState();
  const [judgeId, setJudgeId] = useState();
  const [code, setCode] = useState("");

  const [report, setReport] = useState(["Nothing to report."]);

  const submitSolution = () => {
    if (!code) {
      authDispatch({
        type: authActions.ERROR,
        payload: "You can not submit and empty solution!",
      });
      return;
    }
    setSaving(true);
    axiosInstance(authDispatch, history)
      .post("solution/create/" + taskId, {
        code: code,
        languageId: languageId,
      })
      .then((res) => {
        history.push(`/task/${taskId}/solution/` + res.data.solutionId);
      })
      .catch((err) => {
        setSaving(false);
        authDispatch({
          type: authActions.ERROR,
          payload: err.response ? err.response.data : "COULD NOT CONNECT",
        });
      });
  };

  const changeCode = (newValue) => {
    setCode(newValue);
  };

  const changeTheme = (e) => {
    setTheme(e.target.value);
  };

  const changeLanguage = (e) => {
    let wantedLanguage = task.writtenIn.filter((element) => {
      return element.languageId == e.target.value;
    });
    setLanguageId(wantedLanguage[0].languageId);
    if (
      wantedLanguage[0].language.toLowerCase() === "c" ||
      wantedLanguage[0].language.toLowerCase() === "cpp"
    ) {
      setMode("c_cpp");
    } else {
      setMode(wantedLanguage[0].language.toLowerCase());
    }
    if (
      window.confirm(
        "Do you wish to replace your code with this language preset?"
      )
    )
      setCode(wantedLanguage[0].imports + "" + wantedLanguage[0].main);
    setJudgeId(wantedLanguage[0].judgeId);
  };

  const getTask = async () => {
    try {
      let resTask = await axiosInstance(authDispatch, history).get(
        "task/detail/" + taskId
      );
      setTask(resTask.data);
      setLanguageId(resTask.data.writtenIn[0].languageId);
      if (
        resTask.data.writtenIn[0].language.toLowerCase() === "c" ||
        resTask.data.writtenIn[0].language.toLowerCase() === "cpp"
      ) {
        setMode("c_cpp");
      } else {
        setMode(resTask.data.writtenIn[0].language.toLowerCase());
      }
      setCode(
        resTask.data.writtenIn[0].imports + "" + resTask.data.writtenIn[0].main
      );
      setJudgeId(resTask.data.writtenIn[0].judgeId);
      setLoading(false);
    } catch (err) {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  useEffect(() => {
    getTask();
  }, []);

  if (loading) return <Spinner></Spinner>;

  return (
    <Container fluid className="h-100 bg-charcoal d-flex column p-0">
      <Row className="h-100">
        <Col md={6} className="d-flex column">
          <div
            className="flex-grow-1"
            style={{ height: "500px", overflow: "auto" }}
          >
            {" "}
            <TaskAndConsoleView
              report={report}
              task={task}
            ></TaskAndConsoleView>
          </div>
        </Col>
        <Col md={6} className="p-0">
          <Card className="h-100" bg={"charcoal"}>
            <Card.Body className="p-0">
              <Editor
                className="h-100"
                changeCode={changeCode}
                theme={theme}
                code={code}
                view={saving}
                mode={mode}
                purpose={"creation"}
              ></Editor>
            </Card.Body>
            <Card.Footer>
              <FormControl>
                <Select
                  value={theme}
                  onChange={changeTheme}
                  MenuProps={{ classes: { paper: classes.select } }}
                >
                  {themes.map((thema) => {
                    return (
                      <MenuItem value={thema} key={thema}>
                        {thema}
                      </MenuItem>
                    );
                  })}
                </Select>
              </FormControl>
              <FormControl>
                <Select
                  value={languageId}
                  onChange={changeLanguage}
                  MenuProps={{ classes: { paper: classes.select } }}
                >
                  {task.writtenIn.map((language) => {
                    return (
                      <MenuItem
                        value={language.languageId}
                        key={language.languageId}
                      >
                        {language.language}
                      </MenuItem>
                    );
                  })}
                </Select>
              </FormControl>
              <FormControl className="float-right">
                <EvaluateButton
                  judgeId={judgeId}
                  code={code}
                  updateReport={setReport}
                  testCases={task.testCases}
                  submitSolution={submitSolution}
                  buttonText={"Save solution"}
                  modifiedButtonText={"Saving..."}
                  modify={saving}
                  setModify={setSaving}
                ></EvaluateButton>
              </FormControl>
            </Card.Footer>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default CreateSolution;
