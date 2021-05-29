import { withStyles } from "@material-ui/core";
import Rating from "@material-ui/lab/Rating";
import { BsStar } from "react-icons/bs";

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
