import CrudApi from "../components/CrudApi";
import NavBar from "../components/NavBar";

const Items = () => {
  return (
    <div>
      <NavBar role="ADMIN" />
      <br />
      <br />
      <br />
      <CrudApi type="PRODUCT" />;
    </div>
  );
};

export default Items;
