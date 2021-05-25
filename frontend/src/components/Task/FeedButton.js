import "../../styles/feed.css";

const FeedButton = ({ name, link, selectFunction, activeElement, middle }) => {
  const isActive = () => {
    if (link === activeElement) {
      return true;
    }
    return false;
  };

  const setClass = () => {
    if (isActive()) {
      return "feed-button-active";
    }
    return "feed-button-inactive";
  };

  const isMiddle = () => {
    if (middle) {
      return "feed-button-middle";
    }
  };

  return (
    <div
      className={"feed-button flex-fill " + setClass() + " " + isMiddle()}
      onClick={() => selectFunction(link)}
    >
      {name.toUpperCase()}
    </div>
  );
};

export default FeedButton;
