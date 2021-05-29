import { withStyles } from "@material-ui/core";
import Rating from "@material-ui/lab/Rating";
import { useState } from "react";
import { Button } from "react-bootstrap";
import { BsStar } from "react-icons/bs";
import useAuth from "../../authentication/hook/useAuth";
import axiosInstance from "../../utils/axiosInstance";

const StyledRating = withStyles({
  iconFilled: {
    color: "#BC2C1A",
  },
  iconHover: {
    color: "#BC2C1A",
  },
})(Rating);

const UserGrade = ({ userGrade = 0, id, changeObject, destination }) => {
  const [auth, authDispatch, history] = useAuth();
  const [grade, setGrade] = useState(userGrade);

  const rate = async (value) => {
    if (value === null) return;
    let res = await axiosInstance(authDispatch, history).get(
      "/grade/" + destination + "/" + id + "/" + value
    );
    let resObject = await axiosInstance(authDispatch, history).get(
      "/" + destination + "/detail/" + id
    );
    setGrade(value);
    changeObject(resObject.data);
  };

  const deleteGrade = async () => {
    let res = await axiosInstance(authDispatch, history).delete(
      "/grade/" + destination + "/" + id
    );
    let resObject = await axiosInstance(authDispatch, history).get(
      "/" + destination + "/detail/" + id
    );
    setGrade(0);
    changeObject(resObject.data);
  };

  return (
    <>
      <StyledRating
        name="grade"
        value={grade}
        onChange={(event, newValue) => {
          rate(newValue);
        }}
        emptyIcon={<BsStar />}
      ></StyledRating>
      {grade > 0 && (
        <div>
          <Button variant="rich-black" onClick={deleteGrade}>
            {" "}
            Delete grade
          </Button>
        </div>
      )}
    </>
  );
};

export default UserGrade;
