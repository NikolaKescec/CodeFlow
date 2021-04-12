import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { useHistory, useParams } from "react-router";
import { toast } from "react-toastify";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";
import Spinner from "../Spinner";
import Comment from "./Comment";
import CommentForm from "./CommentForm";

const CommentsPanel = ({ commentsSource, id }) => {
  const [comments, setComments] = useState();
  const [loading, setLoading] = useState(true);
  const [auth, authDispatch, history] = useAuth();

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
      .get(commentsSource + id)
      .then((res) => {
        setComments(res.data);
        setLoading(false);
      })
      .catch((err) => {
        notify(err.message);
      });
  }, []);

  const addComment = (newComment) => {
    setComments([...comments, newComment]);
  };

  console.log(comments);

  if (loading) return <Spinner></Spinner>;

  return (
    <Container
      fluid
      className="d-flex h-100 justify-content-between p-0"
      style={{ flexDirection: "column" }}
    >
      <Container fluid className="scrollable-panel">
        {comments.map((comment) => {
          return (
            <Comment
              comment={comment}
              loggedInUser={auth.data}
              key={comment.commentId + "-" + comment.commenter.username}
            ></Comment>
          );
        })}
      </Container>
      <Container fluid className="p-0">
        <CommentForm
          addComment={addComment}
          id={id}
          commentsSource={commentsSource}
        ></CommentForm>
      </Container>
    </Container>
  );
};

export default CommentsPanel;
