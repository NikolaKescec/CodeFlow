import { Container } from "react-bootstrap";
import Footer from "./Footer";
import TopBar from "./TopBar";

const Layout = ({ children }) => {
  return (
    <Container
      fluid
      className="d-flex justify-content-between column full-height pattern p-0"
    >
      <TopBar />
      {children}
      <Footer />
    </Container>
  );
};

export default Layout;
