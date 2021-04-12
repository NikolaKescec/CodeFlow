import { forwardRef, useEffect, useState } from "react";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import ReactMarkdown from "react-markdown";
import { useHistory, useParams } from "react-router";
import useAuth from "../authentication/hook/useAuth";
import Grade from "../components/Grade/Grade";
import Spinner from "../components/Spinner";
import axiosInstance from "../utils/axiosInstance";
import themes from "../app/codethemes/codethemes";
import CodeMirror from "@uiw/react-codemirror";

import "codemirror/theme/3024-day.css";
import "codemirror/theme/3024-night.css";
import "codemirror/theme/abcdef.css";
import "codemirror/theme/ambiance-mobile.css";
import "codemirror/theme/ambiance.css";
import "codemirror/theme/ayu-dark.css";
import "codemirror/theme/ayu-mirage.css";
import "codemirror/theme/base16-dark.css";
import "codemirror/theme/base16-light.css";
import "codemirror/theme/bespin.css";
import "codemirror/theme/blackboard.css";
import "codemirror/theme/cobalt.css";
import "codemirror/theme/colorforth.css";
import "codemirror/theme/darcula.css";
import "codemirror/theme/dracula.css";
import "codemirror/theme/duotone-dark.css";
import "codemirror/theme/duotone-light.css";
import "codemirror/theme/eclipse.css";
import "codemirror/theme/elegant.css";
import "codemirror/theme/erlang-dark.css";
import "codemirror/theme/gruvbox-dark.css";
import "codemirror/theme/hopscotch.css";
import "codemirror/theme/icecoder.css";
import "codemirror/theme/idea.css";
import "codemirror/theme/isotope.css";
import "codemirror/theme/lesser-dark.css";
import "codemirror/theme/liquibyte.css";
import "codemirror/theme/lucario.css";
import "codemirror/theme/material-darker.css";
import "codemirror/theme/material-ocean.css";
import "codemirror/theme/material-palenight.css";
import "codemirror/theme/material.css";
import "codemirror/theme/mbo.css";
import "codemirror/theme/mdn-like.css";
import "codemirror/theme/midnight.css";
import "codemirror/theme/monokai.css";
import "codemirror/theme/moxer.css";
import "codemirror/theme/neat.css";
import "codemirror/theme/neo.css";
import "codemirror/theme/night.css";
import "codemirror/theme/nord.css";
import "codemirror/theme/oceanic-next.css";
import "codemirror/theme/panda-syntax.css";
import "codemirror/theme/paraiso-dark.css";
import "codemirror/theme/paraiso-light.css";
import "codemirror/theme/pastel-on-dark.css";
import "codemirror/theme/railscasts.css";
import "codemirror/theme/rubyblue.css";
import "codemirror/theme/seti.css";
import "codemirror/theme/shadowfox.css";
import "codemirror/theme/solarized.css";
import "codemirror/theme/ssms.css";
import "codemirror/theme/the-matrix.css";
import "codemirror/theme/tomorrow-night-bright.css";
import "codemirror/theme/tomorrow-night-eighties.css";
import "codemirror/theme/ttcn.css";
import "codemirror/theme/twilight.css";
import "codemirror/theme/vibrant-ink.css";
import "codemirror/theme/xq-dark.css";
import "codemirror/theme/xq-light.css";
import "codemirror/theme/yeti.css";
import "codemirror/theme/yonce.css";
import "codemirror/theme/zenburn.css";
import {
  FormControl,
  InputLabel,
  makeStyles,
  MenuItem,
  Select,
} from "@material-ui/core";
import ReactCodemirror from "@uiw/react-codemirror";

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
  const { auth, authDispatch, history } = useAuth();
  const { id } = useParams();

  const [task, setTask] = useState();
  const [loading, setLoading] = useState(true);

  const classes = useStyles();
  const [theme, setTheme] = useState("darcula");

  const [languageId, setLanguageId] = useState();
  const [changedLanguage, setChangedLanguage] = useState(false);
  const [code, setCode] = useState("");
  let codeMirrorCode = code.repeat(1);

  debugger;

  const submitSolution = () => {
    if (!code) {
      alert("You cannot submit empty solution!");
      return;
    }
    console.log(code);
  };

  const changeCode = (instance, change) => {
    debugger;
    if (!changedLanguage) setCode(instance.getValue().repeat(1));
  };

  const changeTheme = (e) => {
    setTheme(e.target.value);
  };

  const changeLanguage = (e) => {
    if (
      !window.confirm(
        "Changing the language will result in discarding of any written code. Do you wish to proceed?"
      )
    )
      return;
    debugger;
    let wantedLanguage = task.writtenIn.filter((element) => {
      return element.languageId == e.target.value;
    });
    setLanguageId(wantedLanguage[0].languageId);
    setChangedLanguage(true);
    setCode(wantedLanguage[0].imports + "" + wantedLanguage[0].main);
  };

  const getTask = async () => {
    try {
      let resTask = await axiosInstance(authDispatch, history).get(
        "task/detail/" + id
      );
      setTask(resTask.data);
      setLanguageId(resTask.data.writtenIn[0].languageId);
      setCode(
        resTask.data.writtenIn[0].imports + "" + resTask.data.writtenIn[0].main
      );
      setLoading(false);
    } catch (e) {
      debugger;
      alert(e.message);
    }
  };

  useEffect(() => {
    getTask();
  }, []);

  useEffect(() => {
    setChangedLanguage(false);
  }, [languageId]);

  if (loading) return <Spinner></Spinner>;

  return (
    <Container fluid className="h-100">
      <Row className="h-100">
        <Col md={6}>
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
              <div>
                {task.testCases[0] && (
                  <>
                    <p>First test input and output: </p>
                    <p className="highlight">
                      Input: {task.testCases[0].input}
                    </p>
                    <p className="highlight">
                      Output: {task.testCases[0].output}
                    </p>{" "}
                  </>
                )}
              </div>
            </Card.Body>
          </Card>
        </Col>
        <Col md={6} className="p-0">
          <Card className="h-100" bg={"charcoal"}>
            <Card.Body className="p-0">
              <CodeMirror
                value={codeMirrorCode}
                onChanges={changeCode}
                options={{
                  mode: "javascript",
                  theme: theme,
                  keyMap: "sublime",
                }}
              ></CodeMirror>
            </Card.Body>
            <Card.Footer>
              <FormControl>
                <Select
                  value={theme}
                  onChange={changeCode}
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
                <Button onClick={submitSolution} variant="rich-black">
                  Save solution
                </Button>
              </FormControl>
            </Card.Footer>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default CreateSolution;
