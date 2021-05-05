import { Link } from "react-router-dom";

const LinkToUser = ({ name }) => {
  return (
    <strong>
      <Link className="text-link" to={"/programmer/" + name}>
        {name}
      </Link>
    </strong>
  );
};

export default LinkToUser;
