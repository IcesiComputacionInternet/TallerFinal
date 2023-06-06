import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "../views/login/login";
import Home from "../views/home/home";
import CreateUser from "../views/user/createUser";
import CrudRoles from "../views/roles/crudRoles";
import CrudCategories from "../views/categories/crudCategories";
import CrudUsers from "../views/user/crudUser";
import CrudItems from "../views/items/crudItems";
import CrudOrders from "../views/orders/crudOrders";

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

const ProtectedRoutesAdminStore = ({ children }: Props) => {
  if (
    (localStorage.getItem("token") !== null &&
      localStorage.getItem("role") === '"ADMIN"') ||
    localStorage.getItem("role") === '"Store"'
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
        <Route
          path="/crud_categories"
          element={
            <ProtectedRoutesAdmin>
              <CrudCategories />
            </ProtectedRoutesAdmin>
          }
        />
        <Route
          path="/crud_users"
          element={
            <ProtectedRoutesAdmin>
              <CrudUsers />
            </ProtectedRoutesAdmin>
          }
        />
        <Route
          path="/crud_items"
          element={
            <ProtectedRoutesAdminStore>
              <CrudItems />
            </ProtectedRoutesAdminStore>
          }
        />
        <Route
          path="/crud_orders"
          element={
            <ProtectedRoutesAdminStore>
              <CrudOrders />
            </ProtectedRoutesAdminStore>
          }
        />
        <Route path="/view_orders_user" />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
};

export default ThemeRoutes;
