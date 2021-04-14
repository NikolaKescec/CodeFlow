import { TextField } from "@material-ui/core";
import { Form, Formik } from "formik";
import { Button, Container } from "react-bootstrap";
import * as Yup from "yup";
import axiosInstance from "../../utils/axiosInstance";
import { AuthContext } from "../../authentication/context/AuthProvider";
import { useContext } from "react";
import { useHistory } from "react-router";
import useAuth from "../../authentication/hook/useAuth";

const validation = Yup.object().shape({
  authorId: Yup.number().required("Author id is required!"),
  commentBaseId: Yup.string().required("Task id is required!"),
  commentText: Yup.string().required(
    "Comment has to have at least some content!"
  ),
});

const CommentForm = ({
  id,
  text,
  commentsSource,
  action,
  method,
  buttonText,
}) => {
  const [auth, authDispatch, history] = useAuth();

  debugger;
  return (
    <Container fluid className="p-0 border-top border-rich-black">
      <Formik
        initialValues={{
          authorId: auth.data.id,
          commentBaseId: id,
          commentText: text,
        }}
        validationSchema={validation}
        onSubmit={async (values) => {
          try {
            let res = await axiosInstance(authDispatch, history).post(
              commentsSource + action + id,
              values
            );
            method(res.data);
            values.commentText = "";
          } catch (e) {
            alert(e.message);
          }
        }}
      >
        {({ values, touched, errors, handleChange, handleBlur }) => (
          <Form>
            <TextField
              fullWidth
              multiline={true}
              rows={2}
              label={"Comment:"}
              id="commentText"
              name="commentText"
              type="text"
              variant="filled"
              value={values.commentText}
              onChange={handleChange}
              onBlur={handleBlur}
              error={touched.commentText && Boolean(errors.commentText)}
              helperText={touched.commentText && errors.commentText}
            ></TextField>
            <Button type="submit" variant="rich-black" block>
              {buttonText}
            </Button>
          </Form>
        )}
      </Formik>
    </Container>
  );
};

export default CommentForm;
