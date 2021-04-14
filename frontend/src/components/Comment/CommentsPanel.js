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

  debugger;

  useEffect(() => {
    axiosInstance(authDispatch, history)
      .get(commentsSource + id)
      .then((res) => {
        setComments(res.data);
        setLoading(false);
      })
      .catch((err) => {
        alert(err.message);
      });
  }, []);

  const addComment = (newComment) => {
    setComments([...comments, newComment]);
  };

  const setNewComment = (newComment) => {
    let copy = [...comments];
    for (let i = 0; i < comments.length; i++) {
      if (copy[i].commentId === newComment.commentId) {
        copy[i] = newComment;
        break;
      }
    }
    setComments([...copy]);
  };

  const removeComment = (commentId) => {
    let filtered = comments.filter((comment) => {
      return comment.commentId !== commentId;
    });
    setComments([...filtered]);
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
              id={id}
              setNewComment={setNewComment}
              removeComment={removeComment}
              commentSource={commentsSource}
              key={comment.commentId + "-" + comment.commenter.username}
            ></Comment>
          );
        })}
      </Container>
      <Container fluid className="p-0">
        <CommentForm
          method={addComment}
          text={""}
          action={"create/"}
          id={id}
          commentsSource={commentsSource}
          buttonText={"Comment"}
        ></CommentForm>
      </Container>
    </Container>
  );
};

export default CommentsPanel;
