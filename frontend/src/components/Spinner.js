import { Container } from "react-bootstrap";
import "../styles/spinner.css";

const Spinner = () => {
  return (
    <Container
      fluid
      className="d-flex flex-column h-100 justify-content-center"
    >
      <div className="loader my-auto">Loading...</div>
    </Container>
  );
};

export default Spinner;
