import { Form, Formik, FieldArray, getIn, Field } from "formik";
import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { Prompt, Redirect, useHistory } from "react-router";
import { toast, ToastContainer } from "react-toastify";
import useAuth from "../authentication/hook/useAuth";
import axiosInstance from "../utils/axiosInstance";
import * as Yup from "yup";

import Spinner from "../components/Spinner";

import "react-toastify/dist/ReactToastify.css";
import {
  FormControl,
  FormGroup,
  FormLabel,
  TextField,
} from "@material-ui/core";

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
    .min(1, "At least one tast case should be added!"),
});

const CreateTask = () => {
  const [auth, authDispatch, history] = useAuth();
  const [availableLanguages, setAvailableLanguages] = useState([]);
  const [loading, setLoading] = useState(true);
  const [creatingTask, setCreatingTask] = useState(true);

  const notify = (message) => {
    debugger;
    toast.error(message, {
      autoClose: 8000,
      position: toast.POSITION.TOP_RIGHT,
      className: "bg-wine",
    });
  };

  useEffect(() => {
    axiosInstance(authDispatch, history)
      .get("language")
      .then((res) => {
        debugger;
        setAvailableLanguages(res.data);
        setLoading(false);
      })
      .catch((err) => {
        debugger;
        notify("An error occurred: " + err.message);
      });
  }, []);

  const values = {
    author: auth.data.id,
    taskText: "",
    inputFormat: "",
    outputFormat: "",
    language: [],
    testCase: [{ input: "", output: "" }],
  };

  return (
    <Container className="bg-dark h-100 overflow-auto p-1">
      {loading && <Spinner></Spinner>}
      <Prompt
        when={creatingTask}
        message="Are you sure you want to change site? This action will diminish your task making progress."
      ></Prompt>
      <ToastContainer />
      <Formik
        initialValues={values}
        validationSchema={ValidationSchema}
        onSubmit={(values) => {
          axiosInstance(authDispatch, history)
            .post("/task/create-task", values)
            .then((res) => {
              setCreatingTask(false);
              history.push("/home");
            })
            .catch((err) => {
              notify("While creating an error ocurred: " + err.message);
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
              Available languages
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

            <label htmlFor="language" className="text-white mt-2">
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
              Create
            </Button>
          </Form>
        )}
      </Formik>
    </Container>
  );
};

export default CreateTask;
