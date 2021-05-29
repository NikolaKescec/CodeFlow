import { TextField } from "@material-ui/core";
import { Field, FieldArray, Form, Formik, getIn } from "formik";
import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { Prompt, useParams } from "react-router";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import * as Yup from "yup";
import authActions from "../../authentication/actions/authActions";
import useAuth from "../../authentication/hook/useAuth";
import Spinner from "../../components/Spinner";
import axiosInstance from "../../utils/axiosInstance";

const ValidationSchema = Yup.object().shape({
  author: Yup.number().required("Author id is required."),
  taskText: Yup.string()
    .min(
      25,
      "Too short, task description should be at least 25 characters long."
    )
    .required("Task decription is a required part of task creation."),
  inputFormat: Yup.string().required(
    "Input format is a required part of task creation."
  ),
  outputFormat: Yup.string().required(
    "Output format is a required part of task creation."
  ),
  language: Yup.array()
    .of(Yup.number())
    .min(1, "At least one tast case should be added!"),
  testCase: Yup.array()
    .of(
      Yup.object().shape({
        input: Yup.string().required("Input is required!"),
        output: Yup.string().required("Output is required!"),
      })
    )
    .min(
      1,
      "At least one tast case should be added and input and output must be filled out!"
    ),
});

const UpdateTask = () => {
  const [auth, authDispatch, history] = useAuth();
  const [task, setTask] = useState({});
  const [oldWrittenIn, setOldWrittenIn] = useState([]);
  const [loading, setLoading] = useState(true);
  const [availableLanguages, setAvailableLanguages] = useState([]);
  const [creatingTask, setCreatingTask] = useState(true);
  const { taskId } = useParams();

  const notify = (message) => {
    toast.error(message, {
      autoClose: 8000,
      position: toast.POSITION.TOP_RIGHT,
      className: "bg-wine",
    });
  };

  const getTaskAndAvailableLanguages = async () => {
    try {
      let task = await axiosInstance(authDispatch, history).get(
        "task/detail/" + taskId
      );
      let availableLanguages = await axiosInstance(authDispatch, history).get(
        "language"
      );
      setAvailableLanguages(availableLanguages.data);
      setTask(task.data);
      task.data.writtenIn.forEach((language) => {
        oldWrittenIn.push(language.languageId + "");
      });
      setOldWrittenIn([...oldWrittenIn]);
      setLoading(false);
    } catch (err) {
      authDispatch({
        type: authActions.ERROR,
        payload: err.response ? err.response.data : "COULD NOT CONNECT",
      });
    }
  };

  useEffect(() => {
    getTaskAndAvailableLanguages();
  }, []);

  if (loading) {
    return <Spinner></Spinner>;
  }

  const values = {
    author: auth.data.id,
    taskId: task.taskId,
    taskText: task.taskText,
    inputFormat: task.inputFormat,
    outputFormat: task.outputFormat,
    language: [...oldWrittenIn],
    testCase: [...task.testCases],
  };

  return (
    <Container className="bg-dark h-100 overflow-auto p-1">
      {loading && <Spinner></Spinner>}
      <Prompt
        when={creatingTask}
        message="Are you sure you want to change site? This action will diminish your task updating progress."
      ></Prompt>
      <ToastContainer />
      <Formik
        initialValues={values}
        validationSchema={ValidationSchema}
        onSubmit={(values) => {
          axiosInstance(authDispatch, history)
            .put("/task/update-task/" + taskId, values)
            .then((res) => {
              setCreatingTask(false);
              history.push("/task/" + taskId);
            })
            .catch((err) => {
              notify("While updating an error ocurred: " + err.message);
            });
        }}
      >
        {({ values, touched, errors, handleChange, handleBlur, isValid }) => (
          <Form>
            <label className="text-white mt-2" htmlFor="taskText">
              Task decription:
            </label>
            <TextField
              fullWidth
              multiline={true}
              rows={6}
              label={null}
              id="taskText"
              name="taskText"
              type="text"
              variant="filled"
              required
              value={values.taskText}
              onChange={handleChange}
              onBlur={handleBlur}
              error={touched.taskText && Boolean(errors.taskText)}
              helperText={touched.taskText && errors.taskText}
            ></TextField>
            <label htmlFor="taskText" className="text-white mt-2">
              Input format example:
            </label>
            <TextField
              fullWidth
              multiline={true}
              id="inputFormat"
              name="inputFormat"
              type="text"
              variant="filled"
              required
              value={values.inputFormat}
              onChange={handleChange}
              onBlur={handleBlur}
              error={touched.inputFormat && Boolean(errors.inputFormat)}
              helperText={touched.inputFormat && errors.inputFormat}
            ></TextField>
            <label htmlFor="taskText" className="text-white mt-2">
              Output format example:
            </label>
            <TextField
              fullWidth
              multiline={true}
              id="outputFormat"
              name="outputFormat"
              type="text"
              variant="filled"
              required
              value={values.outputFormat}
              onChange={handleChange}
              onBlur={handleBlur}
              error={touched.outputFormat && Boolean(errors.outputFormat)}
              helperText={touched.outputFormat && errors.outputFormat}
            ></TextField>

            <label component="label" className="text-white mt-2">
              Available languages (only language addition is allowed)
            </label>
            <div role="group" aria-labelledby="checkbox-group">
              {availableLanguages.map((lang) => {
                return (
                  <label
                    className="mr-3 text-white"
                    key={lang.languageId + "" + lang.language}
                  >
                    <Field
                      type="checkbox"
                      name="language"
                      checked={values.language.includes(lang.languageId + "")}
                      disabled={oldWrittenIn.includes(lang.languageId + "")}
                      value={lang.languageId + ""}
                    />
                    <span> {lang.language}</span>
                  </label>
                );
              })}
            </div>
            {touched.language && Boolean(errors.language) && (
              <p className="text-error">Please add at least one language!</p>
            )}

            <label htmlFor="testCase" className="text-white mt-2">
              Test cases:
            </label>
            <FieldArray name="testCase">
              {({ remove, push }) => (
                <Container className="w-100">
                  {values.testCase.map((test, index) => {
                    const input = `testCase[${index}].input`;
                    const touchedInput = getIn(touched, input);
                    const errorInput = getIn(errors, input);

                    const output = `testCase[${index}].output`;
                    const touchedOutput = getIn(touched, output);
                    const errorOutput = getIn(errors, output);

                    return (
                      <Row key={index}>
                        <Col md={5}>
                          <TextField
                            name={input}
                            fullWidth
                            value={test.input}
                            type="text"
                            label="Input"
                            required
                            helperText={
                              touchedInput && errorInput ? errorInput : ""
                            }
                            error={Boolean(touchedInput && errorInput)}
                            onChange={handleChange}
                            onBlur={handleBlur}
                          ></TextField>
                        </Col>
                        <Col md={5}>
                          <TextField
                            name={output}
                            fullWidth
                            value={test.output}
                            type="text"
                            label="Output"
                            required
                            helperText={
                              touchedOutput && errorOutput ? errorOutput : ""
                            }
                            error={Boolean(touchedOutput && errorOutput)}
                            onChange={handleChange}
                            onBlur={handleBlur}
                          ></TextField>
                        </Col>
                        <Col md={2}>
                          <Button
                            type="button"
                            variant="wine"
                            className="w-100 mt-2"
                            onClick={() => remove(index)}
                          >
                            Remove
                          </Button>
                        </Col>
                      </Row>
                    );
                  })}
                  <Button
                    type="button"
                    className="mt-2"
                    variant="wine"
                    onClick={() => push({ input: "", output: "" })}
                  >
                    Add a test case
                  </Button>
                </Container>
              )}
            </FieldArray>
            {touched.testCase && Boolean(errors.testCase) && (
              <p className="text-error">Please add at least one test case!</p>
            )}
            <hr></hr>
            <Button
              type="submit"
              variant="wine"
              className="mt-2 float-right w-25"
            >
              Update
            </Button>
          </Form>
        )}
      </Formik>
    </Container>
  );
};

export default UpdateTask;
