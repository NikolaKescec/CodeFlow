import { Box, withStyles } from "@material-ui/core";
import { nanoid } from "nanoid";
import { BsStar, BsStarFill, BsStarHalf } from "react-icons/bs";
import Rating from "@material-ui/lab/Rating";

const StyledRating = withStyles({
  iconFilled: {
    color: "#f7f7ff",
  },
  iconHover: {
    color: "#ff3d47",
  },
})(Rating);

const Grade = ({ grade }) => {
  return (
    <StyledRating
      name="readOnlyGrade"
      value={grade}
      emptyIcon={<BsStar />}
      readOnly
    ></StyledRating>
  );
};

export default Grade;
