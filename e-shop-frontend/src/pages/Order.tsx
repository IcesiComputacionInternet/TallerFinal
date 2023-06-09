import CrudApi from "../components/CrudApi";
import NavBar from "../components/NavBar";

const Order = () => {
  return (
    <div>
      <NavBar role="ADMIN" />
      <br />
      <br />
      <br />
      <CrudApi type="ORDER" />;
    </div>
  );
};

export default Order;
