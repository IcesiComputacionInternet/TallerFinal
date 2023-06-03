import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "../views/login/login";
import Home from "../views/home/home";
import CreateUser from "../views/user/createUser";
import CrudRoles from "../views/roles/crudRoles";

interface Props {
  children: React.ReactComponentElement<any>;
}

const ProtectedRoutes = ({ children }: Props) => {
  if (localStorage.getItem("token") === null) {
    return <Navigate to="/login" />;
  } else {
    return children;
  }
};

const ProtectedRoutesAdmin = ({ children }: Props) => {
  if (
    localStorage.getItem("token") !== null &&
    localStorage.getItem("role") === '"ADMIN"'
  ) {
    return children;
  } else {
    return <Navigate to="/" />;
  }
};

const IsNotLoggedRoutes = ({ children }: Props) => {
  if (localStorage.getItem("token") === null) {
    return children;
  } else {
    return <Navigate to="/" />;
  }
};

const ThemeRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/login"
          element={
            <IsNotLoggedRoutes>
              <Login></Login>
            </IsNotLoggedRoutes>
          }
        />
        <Route
          path="/"
          element={
            <ProtectedRoutes>
              <Home></Home>
            </ProtectedRoutes>
          }
        />
        <Route
          path="/sing_up"
          element={
            <IsNotLoggedRoutes>
              <CreateUser rolAdmin={false} />
            </IsNotLoggedRoutes>
          }
        />
        <Route
          path="/add_user_admin"
          element={
            <ProtectedRoutesAdmin>
              <CreateUser rolAdmin={true} />
            </ProtectedRoutesAdmin>
          }
        />
        <Route
          path="/crud_role"
          element={
            <ProtectedRoutesAdmin>
              <CrudRoles></CrudRoles>
            </ProtectedRoutesAdmin>
          }
        />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
};

export default ThemeRoutes;
