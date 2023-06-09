import CrudApi from "../components/CrudApi";
import NavBar from "../components/NavBar";

const Category = () => {
  return (
    <div>
      <NavBar role="ADMIN" />
      <br />
      <br />
      <br />
      <CrudApi type="CATEGORIES" />;
    </div>
  );
};

export default Category;
