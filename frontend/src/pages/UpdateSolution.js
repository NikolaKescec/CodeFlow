import { useEffect, useState } from "react";
import { Button, Card, Col, Collapse, Container, Row } from "react-bootstrap";
import ReactMarkdown from "react-markdown";
import { useParams } from "react-router";
import useAuth from "../authentication/hook/useAuth";
import Grade from "../components/Grade/Grade";
import Spinner from "../components/Spinner";
import axiosInstance from "../utils/axiosInstance";
import themes from "../app/codethemes/codethemes";

import {
  FormControl,
  InputLabel,
  makeStyles,
  MenuItem,
  Select,
} from "@material-ui/core";
import Editor from "../components/Editor/Editor";
import SolutionTaskPreview from "../components/Solution/SolutionTaskPreview";

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

const UpdateSolution = () => {
  const [auth, authDispatch, history] = useAuth();
  const { solutionId } = useParams();
  const { taskId } = useParams();

  const [task, setTask] = useState();
  const [loading, setLoading] = useState(true);

  const classes = useStyles();
  const [theme, setTheme] = useState("vibrant_ink");
  const [mode, setMode] = useState("java");

  const [languageId, setLanguageId] = useState();
  const [code, setCode] = useState("");

  debugger;

  const updateSolution = () => {
    if (!code) {
      alert("You cannot update empty solution!");
      return;
    }
    axiosInstance(authDispatch, history)
      .put("solution/update/" + solutionId, {
        code: code,
        languageId: languageId,
        solutionId: solutionId,
      })
      .then((res) => {
        debugger;
        history.push(`/task/${taskId}/solution/` + res.data.solutionId);
      })
      .catch((err) => {
        alert(err.message);
      });
  };

  const changeCode = (newValue) => {
    debugger;
    setCode(newValue);
  };

  const changeTheme = (e) => {
    debugger;
    setTheme(e.target.value);
  };

  const changeLanguage = (e) => {
    debugger;
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
  };

  const getTask = async () => {
    let resTask = await axiosInstance(authDispatch, history).get(
      "task/detail/" + taskId
    );
    setTask(resTask.data);
  };

  const getSolution = async () => {
    let resSolution = await axiosInstance(authDispatch, history).get(
      "solution/detail/" + solutionId
    );
    setCode(resSolution.data.code);
    setLanguageId(resSolution.data.language.languageId);
    if (
      resSolution.data.language.language.toLowerCase() === "c" ||
      resSolution.data.language.language.toLowerCase() === "cpp"
    ) {
      setMode("c_cpp");
    } else {
      setMode(resSolution.data.language.language.toLowerCase());
    }
  };

  const getTaskAndSolution = async () => {
    try {
      await getTask();
      await getSolution();
      setLoading(false);
    } catch (e) {
      debugger;
      alert(e.message);
    }
  };

  useEffect(() => {
    getTaskAndSolution();
  }, []);

  if (loading) return <Spinner></Spinner>;

  return (
    <Container fluid className="h-100 bg-charcoal">
      <Row className="h-100">
        <Col md={6}>
          <SolutionTaskPreview task={task}></SolutionTaskPreview>
        </Col>
        <Col md={6} className="p-0">
          <Card className="h-100" bg={"charcoal"}>
            <Card.Body className="p-0">
              <Editor
                changeCode={changeCode}
                theme={theme}
                code={code}
                view={false}
                mode={mode}
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
                <Button onClick={updateSolution} variant="rich-black">
                  Update solution
                </Button>
              </FormControl>
            </Card.Footer>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default UpdateSolution;
