import { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import useAuth from "../authentication/hook/useAuth";
import CommentsPanel from "../components/Comment/CommentsPanel";
import SolutionDetails from "../components/Solution/SolutionDetails";
import Spinner from "../components/Spinner";
import TwoComponentPageLayout from "../components/TwoComponentPageLayout";
import axiosInstance from "../utils/axiosInstance";

const Solution = () => {
  let { id } = useParams();
  const [auth, authDispatch, checking] = useAuth();

  if (checking) {
    return <Spinner></Spinner>;
  }
  return (
    <TwoComponentPageLayout
      main={
        <SolutionDetails
          id={id}
          authDispatch={authDispatch}
          loggedInUser={auth.data}
        ></SolutionDetails>
      }
      secondary={
        <CommentsPanel
          loggedInUser={auth.data}
          authDispatch={authDispatch}
          commentsSource={"solution-comments/"}
          id={id}
        ></CommentsPanel>
      }
    ></TwoComponentPageLayout>
  );
};

export default Solution;
