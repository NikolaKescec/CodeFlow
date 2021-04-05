import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { useHistory, useParams } from "react-router";
import { toast } from "react-toastify";
import axiosInstance from "../utils/axiosInstance";

const CommentsPanel = ({ commentsSource, id, authDispatch }) => {
  const [comments, setComments] = useState();
  const [loading, setLoading] = useState(true);
  const history = useHistory();

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

  console.log(comments);

  return <Container>{id}</Container>;
};

export default CommentsPanel;
