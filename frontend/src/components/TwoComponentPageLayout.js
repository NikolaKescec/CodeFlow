import { Col, Container, Row } from "react-bootstrap";

const TwoComponentPageLayout = ({ main, secondary }) => {
  return (
    <Container fluid className="p-0 h-100">
      <Row className="h-100 d-flex">
        <Col
          xs={12}
          md={9}
          className="bg-wine-darker p-0 border-lg border-right border-rich-black flex-grow-1 d-flex column"
        >
          <div
            className="flex-grow-1"
            style={{ height: "500px", overflow: "auto" }}
          >
            {main}
          </div>
        </Col>
        <Col xs={0} md={3} className="d-md-inline bg-charcoal p-0 d-flex">
          {secondary}
        </Col>
      </Row>
    </Container>
  );
};

export default TwoComponentPageLayout;
