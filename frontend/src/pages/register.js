import { TextField } from "@material-ui/core";
import { useFormik } from "formik";
import { Alert, Button, Col, Container, Form, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import { AuthContext } from "../authentication/context/AuthProvider";
import "../styles/register.css";
import { useContext, useEffect } from "react";
import register from "../authentication/actions/register";
import { useHistory } from "react-router-dom";

const validate = (values) => {
  debugger;
  const errors = {};

  if (!values.username) {
    errors.username = "Required";
  }

  if (!values.email) {
    errors.email = "Required";
  }

  if (!values.password) {
    errors.password = "Required";
  } else if (values.password.length < 8) {
    errors.password = "Password must be longer than 8 characters!";
  }

  if (values.password !== values.repeatedPassword) {
    errors.repeatedPassword = "Passwords do not match!";
  }

  return errors;
};

const Register = () => {
  const { auth, dispatch } = useContext(AuthContext);
  const history = useHistory();

  const formik = useFormik({
    initialValues: {
      username: "",
      email: "",
      password: "",
      repeatedPassword: "",
    },
    validate,
    onSubmit: (values) => {
      register(values, history)(dispatch);
    },
  });

  useEffect(() => {
    return () => {
      if (auth.error) dispatch({ type: "REMOVE_ERROR" });
    };
  }, [auth]);

  return (
    <Container className="bg-baby-powder border-rich-black rounded p-2">
      <Row>
        <Col className="d-sm-none d-xs-none d-md-block border-wine border-right">
          <Container fluid className="bg-register"></Container>
        </Col>
        <Col className="d-flex column justify-content-center ">
          <h2 className="text-wine">Register</h2>
          {auth.error && (
            <Alert
              variant="wine"
              onClose={() => dispatch({ type: "REMOVE_ERROR" })}
              dismissible
            >
              <p>{auth.error.message}</p>
            </Alert>
          )}
          <Form onSubmit={formik.handleSubmit} className="  p-2 form">
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
                  formik.touched.username && Boolean(formik.errors.username)
                }
                helperText={formik.touched.username && formik.errors.username}
              ></TextField>
            </Form.Group>
            <Form.Group>
              <TextField
                fullWidth
                id="email"
                name="email"
                label="Email"
                type="email"
                variant="filled"
                value={formik.values.email}
                onChange={formik.handleChange}
                error={formik.touched.email && Boolean(formik.errors.email)}
                helperText={formik.touched.email && formik.errors.email}
              ></TextField>
            </Form.Group>
            <Form.Group>
              <TextField
                fullWidth
                id="password"
                name="password"
                label="Password"
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
              <TextField
                fullWidth
                id="repeatedPassword"
                name="repeatedPassword"
                label="Repeat your password"
                type="password"
                variant="filled"
                value={formik.values.repeatedPassword}
                onChange={formik.handleChange}
                error={
                  formik.touched.repeatedPassword &&
                  Boolean(formik.errors.repeatedPassword)
                }
                helperText={
                  formik.touched.repeatedPassword &&
                  formik.errors.repeatedPassword
                }
              ></TextField>
            </Form.Group>
            <Form.Group>
              <Button type="submit" variant="outline-wine mr-2">
                Register
              </Button>
              <Link to="/">
                <Button variant="outline-wine">Return</Button>
              </Link>
            </Form.Group>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default Register;
