import "./Loader.css";

interface Props {
  align: string;
}
const Loader = ({ align }: Props) => {
  return (
    <div className={`lds-spinner ${align}`}>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  );
};

export default Loader;
