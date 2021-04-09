import { nanoid } from "nanoid";
import { BsStar, BsStarFill, BsStarHalf } from "react-icons/bs";

const Grade = ({ grade }) => {
  const stars = [];

  debugger;

  for (let counter = 0; counter < grade; counter++) {
    stars.push(<BsStarFill key={nanoid(10)}></BsStarFill>);
  }

  if (!Number.isInteger(grade)) {
    grade = Math.round(grade + 1);
    stars.push(<BsStarHalf key={nanoid(10)}></BsStarHalf>);
  }

  for (let remaining = grade; remaining < 5; remaining++) {
    stars.push(<BsStar key={nanoid(10)}></BsStar>);
  }

  return stars;
};

export default Grade;
