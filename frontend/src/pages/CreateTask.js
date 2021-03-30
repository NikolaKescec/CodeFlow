import { Form } from "formik";
import { Container } from "react-bootstrap";
import useAuth from "../authentication/hook/useAuth";

const CreateTask = () => {
  const [auth, authDispatch, checking] = useAuth();

  return (
    <Container>
      <Form></Form>
    </Container>
  );
};

export default CreateTask;
