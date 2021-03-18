import "../../styles/feed.css";

const FeedButton = ({ text, selectFunction, activeElement, middle }) => {
  const isActive = () => {
    debugger;
    console.log(text);
    if (text === activeElement) {
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
      className={"feed-button flex-fill " + isActive() + " " + isMiddle()}
      onClick={() => selectFunction(text)}
    >
      {text}
    </div>
  );
};

export default FeedButton;
