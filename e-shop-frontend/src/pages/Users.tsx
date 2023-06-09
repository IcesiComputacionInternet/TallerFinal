import CrudApi from "../components/CrudApi";
import NavBar from "../components/NavBar";

const Users = () => {
  return (
    <div>
      <NavBar role="ADMIN" />
      <br />
      <br />
      <br />
      <CrudApi type="USER" />;
    </div>
  );
};

export default Users;
