import { Col, Container, Row } from "react-bootstrap";

const TwoComponentPageLayout = ({ main, secondary }) => {
  return (
    <Container fluid className="flex-grow-1">
      <Row className="h-100">
        <Col
          xs={12}
          md={9}
          className="home-pattern p-0 border-lg border-right border-rich-black"
        >
          {main}
        </Col>
        <Col xs={0} md={3} className="d-md-inline bg-charcoal p-0 d-flex">
          {secondary}
        </Col>
      </Row>
    </Container>
  );
};

export default TwoComponentPageLayout;
