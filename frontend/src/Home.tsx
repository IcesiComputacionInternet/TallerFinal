import React, { useEffect, useState } from "react";
import axios from "axios";
const baseUrl = "http://localhost:8080";

interface Account {
  accountId: string;
  accountNumber: string;
  balance: number;
}

interface Props {
  setLogin: (isLoggedIn: boolean) => void;
}

const Home = ({ setLogin }: Props) =>{
  const [accounts, setAccounts] = useState<Account[]>([]);

  useEffect(() => {
    fetchAccounts();
  }, []);

  const fetchAccounts = async () => {
    try {
      const token = localStorage.getItem("jwt");
      const headers = {
        Authorization: `Bearer ${token}`
      };

      const response = await axios.get( baseUrl+"/accounts/getUserAccounts", { headers });
      setAccounts(response.data);
    } catch (error) {
      console.error("Error fetching accounts:", error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("jwt");
    setLogin(false);
  };

  return (
    <div className="d-flex flex-column align-items-center justify-content-center">
      <h1 className="text-center mb-4">Bienvenido a la página de inicio</h1>
      <button className="btn btn-primary position-fixed top-0 end-0 m-4" onClick={handleLogout}>
        Logout
      </button>
      <div>
        <h3 className="text-center mb-4">Lista de cuentas</h3>
        {accounts.length === 0 ? (
          <div className="text-center">
            <p style={{ color: "red", fontWeight: "bold" }}>No hay cuentas de banco asociadas a este usuario</p>
          </div>
        ) : (
          accounts.map((account) => (
            <div key={account.accountId}>
              <p>Number: {account.accountNumber}</p>
              <p>Balance: {account.balance}</p>
              <hr />
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default Home;




