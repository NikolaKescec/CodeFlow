import { TextField } from "@material-ui/core";
import { useFormik } from "formik";
import { useEffect } from "react";
import {
  Accordion,
  Alert,
  Button,
  Col,
  Container,
  Form,
  Row,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import authActions from "../authentication/actions/authActions";
import useAuth from "../authentication/hook/useAuth";
import axiosInstance from "../utils/axiosInstance";

const validate = (values) => {
  debugger;
  const errors = {};

  if (!values.username) {
    errors.username = "Required";
  }

  if (!values.password) {
    errors.password = "Required for any change";
  }

  if (values.newPassword && values.newPassword < 8) {
    errors.newPassword = "New password has to be longer than 8 characters!";
  }

  if (values.newPassword !== values.repeatedNewPassword) {
    errors.repeatedPassword = "Passwords do not match!";
  }

  return errors;
};

const EditProfile = () => {
  const [auth, authDispatch, history] = useAuth();

  const formik = useFormik({
    initialValues: {
      userId: auth.data.id,
      username: auth.data.username,
      password: "",
      newPassword: undefined,
      repeatedNewPassword: undefined,
    },
    validate,
    onSubmit: (values) => {
      axiosInstance(authDispatch, history)
        .put("/programmer/update", values)
        .then((res) => {
          authDispatch({
            type: authActions.LOGIN_SUCCESS,
            payload: res.data,
          });
          history.push("/profile");
        })
        .catch((err) => {
          authDispatch({
            type: authActions.REGISTER_ERROR,
            payload: err.response ? err.response.data : "COULD NOT CONNECT",
          });
        });
    },
  });

  useEffect(() => {
    return () => {
      if (auth.error) authDispatch({ type: "REMOVE_ERROR" });
    };
  }, [auth]);

  return (
    <Container className="bg-dark border-rich-black rounded rounded p-2 text-white">
      <Row>
        <Col className="d-flex column justify-content-center ">
          <h2 className="text-red-violet">Update</h2>
          {auth.error && (
            <Alert
              variant="wine"
              onClose={() => authDispatch({ type: "REMOVE_ERROR" })}
              dismissible
            >
              <p>{auth.error.message}</p>
            </Alert>
          )}
          <Form onSubmit={formik.handleSubmit} className="p-2 form">
            <Accordion>
              <div>
                <Accordion.Toggle
                  as={Link}
                  className="text-red-violet"
                  eventKey="0"
                >
                  Change username
                </Accordion.Toggle>
                <Accordion.Collapse eventKey="0">
                  <Form.Group>
                    <TextField
                      fullWidth
                      id="username"
                      name="username"
                      label="Username"
                      variant="filled"
                      value={formik.values.username}
                      onChange={formik.handleChange}
                      error={
                        formik.touched.username &&
                        Boolean(formik.errors.username)
                      }
                      helperText={
                        formik.touched.username && formik.errors.username
                      }
                    ></TextField>
                  </Form.Group>
                </Accordion.Collapse>
                <hr></hr>
              </div>
              <div>
                <Accordion.Toggle
                  as={Link}
                  className="text-red-violet"
                  eventKey="1"
                >
                  Change password
                </Accordion.Toggle>
                <Accordion.Collapse eventKey="1">
                  <>
                    <Form.Group>
                      <TextField
                        fullWidth
                        id="newPassword"
                        name="newPassword"
                        label="New password"
                        type="password"
                        variant="filled"
                        value={formik.values.newPassword}
                        onChange={formik.handleChange}
                        error={
                          formik.touched.newPassword &&
                          Boolean(formik.errors.newPassword)
                        }
                        helperText={
                          formik.touched.newPassword &&
                          formik.errors.newPassword
                        }
                      ></TextField>
                    </Form.Group>
                    <Form.Group>
                      <TextField
                        fullWidth
                        id="repeatedNewPassword"
                        name="repeatedNewPassword"
                        label="Repeat your new password"
                        type="password"
                        variant="filled"
                        value={formik.values.repeatedNewPassword}
                        onChange={formik.handleChange}
                        error={
                          formik.touched.repeatedNewPassword &&
                          Boolean(formik.errors.repeatedNewPassword)
                        }
                        helperText={
                          formik.touched.repeatedNewPassword &&
                          formik.errors.repeatedNewPassword
                        }
                      ></TextField>
                    </Form.Group>
                  </>
                </Accordion.Collapse>
              </div>
            </Accordion>
            <hr></hr>
            <Form.Group>
              <TextField
                fullWidth
                id="password"
                name="password"
                label="Original password"
                type="password"
                variant="filled"
                value={formik.values.password}
                onChange={formik.handleChange}
                error={
                  formik.touched.password && Boolean(formik.errors.password)
                }
                helperText={formik.touched.password && formik.errors.password}
              ></TextField>
            </Form.Group>
            <Form.Group>
              <Button type="submit" variant="red-violet mr-2">
                Update
              </Button>
              <Link to="/profile">
                <Button variant="red-violet">Return</Button>
              </Link>
            </Form.Group>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default EditProfile;
