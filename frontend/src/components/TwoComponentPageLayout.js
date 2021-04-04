import { Container } from "@material-ui/core";
import { Col, Row } from "react-bootstrap";

const TwoComponentPageLayout = ({ main, secondary }) => {
  return (
    <Container fluid className="flex-grow-1">
      <Row>
        <Col
          xs={12}
          md={9}
          className="home-pattern p-0 border-lg border-right border-rich-black"
        >
          {main}
        </Col>
        <Col xs={0} md={3} className="d-none d-md-inline p-0 home-pattern">
          {secondary}
        </Col>
      </Row>
    </Container>
  );
};

export default TwoComponentPageLayout;
