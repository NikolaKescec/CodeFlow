import { Button, Col, Container, Row, Form } from "react-bootstrap";
import { useFormik } from "formik";
import { TextField } from "@material-ui/core";
import { Link, Redirect, useHistory } from "react-router-dom";
import { AuthContext } from "../authentication/context/AuthProvider";
import { useContext, useEffect } from "react";

import login from "../authentication/actions/login";

import "../styles/login.css";

const validate = (values) => {
  const errors = {};

  if (!values.username) {
    errors.username = "Required";
  }

  if (!values.password) {
    errors.password = "Required";
  }
  // else if (values.password.length < 8) {
  //   errors.password = "Password must be longer than 8 characters!";
  // }

  return errors;
};

const Login = () => {
  const { auth, dispatch } = useContext(AuthContext);
  const history = useHistory();

  debugger;

  console.log(auth);
  console.log(dispatch);

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validate,
    onSubmit: (values) => {
      login(values)(dispatch);
    },
  });

  useEffect(() => {
    if (auth.data) history.push("/home");
  }, [auth]);

  return (
    <Container className="d-flex p-2 bg-baby-powder column justify-content-around border rounded">
      <Container>
        <Row className="bg-baby-powder">
          <Col xs={{ span: 12, order: "last" }} md={{ order: "first" }}>
            <Container fluid className="justify-content-center p-1">
              <h2>
                Welcome to <b className="text-red-violet">CodeFlow</b>
              </h2>
              <p>
                Solve problems, make problems and make your
                <span className="text-red-violet"> code flow</span>!
              </p>
            </Container>
            <Container fluid className="bg-login"></Container>
          </Col>
          <Col
            xs={{ span: 12, order: "first" }}
            md={{ order: "last" }}
            className="text-center  d-flex column justify-content-center p-1 border-charcoal rounded form"
          >
            {auth.error && (
              <span className=" text-rich-dark text-center">
                {auth.error.message}
              </span>
            )}
            <Form onSubmit={formik.handleSubmit} className=" p-2  ">
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
              <Button type="submit" variant="outline-wine">
                Login
              </Button>
            </Form>
            <Container className="p-0 my-0 ">
              <hr className="bg-wine"></hr>
            </Container>
            <Container>
              <span>Dont have an account? </span>
              <Link to="/register">
                <Button variant="outline-wine" className="d-inline">
                  Register!
                </Button>
              </Link>
            </Container>
          </Col>
        </Row>
      </Container>
      <Container fluid className="text-red-violet">
        <hr className="bg-red-violet"></hr>
        Quench your problem solving thirst!
      </Container>
      <Container className="justify-content-center bg-baby-powder rounded">
        <div>
          <h1 className="pt-2">
            Not sure what <b className="text-red-violet">CodeFlow</b> is?
          </h1>
          <hr className="bg-red-violet"></hr>
          <p>
            Imagine CodeFlow as a platform where you can brush up your coding
            skills by solving problems that others users issue. <b>You</b>{" "}
            included!
          </p>
          <hr className="bg-red-violet"></hr>
          <p className="pb-3">
            Don't worry about running code on your computer, the page will do
            this for you!
          </p>
        </div>
      </Container>
    </Container>
  );
};

export default Login;
