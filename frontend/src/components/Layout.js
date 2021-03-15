import { Container } from "react-bootstrap";
import Footer from "./Footer";
import TopBar from "./TopBar";

const Layout = ({ children, path }) => {
  return (
    <Container
      fluid
      className="d-flex justify-content-between column full-height pattern p-0"
    >
      <TopBar path={path} />
      {children}
      <Footer />
    </Container>
  );
};

export default Layout;
