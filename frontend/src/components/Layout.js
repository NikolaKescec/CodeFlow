import { Container } from "react-bootstrap";
import Footer from "./Footer";
import TopBar from "./TopBar";

const Layout = ({ children, path }) => {
  return (
    <Container
      fluid
      className="d-flex justify-content-between column pattern p-0 full-height"
    >
      <TopBar path={path} />
      <Container fluid className="flex-grow-1">
        {children}
      </Container>
      <Footer />
    </Container>
  );
};

export default Layout;
