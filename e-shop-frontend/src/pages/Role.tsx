import CrudApi from "../components/CrudApi";
import NavBar from "../components/NavBar";

const Role = () => {
  return (
    <div>
      <NavBar role="ADMIN" />
      <br />
      <br />
      <br />
      <CrudApi type="ROLES" />;
    </div>
  );
};

export default Role;
