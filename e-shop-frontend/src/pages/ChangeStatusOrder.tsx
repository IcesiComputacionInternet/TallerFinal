import NavBar from "../components/NavBar";
import OrdersTable from "../components/OrdersTable";

const ChangeStatusOrder = () => {
  return (
    <div>
      <NavBar role="SHOP" />
      <br />
      <br />
      <br />
      <OrdersTable
        orders={[{ id: 323232, user: "12dsds", status: "Terminada" }]}
      />
    </div>
  );
};

export default ChangeStatusOrder;
