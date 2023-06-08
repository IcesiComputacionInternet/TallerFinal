import { useState, useEffect } from 'react'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './Login';
import Register from './Register';
import Home from './Home';
import '../styles/App.css'
import Orders from './Orders';

function App() {

  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem("jwt") !== null
  );
  
  const token = localStorage.getItem("jwt")
  console.log(token)
  useEffect(() => {
    localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);

  const logIn = () => setIsLoggedIn(true);

  return (
    <div className="App">
    <Router>
        <Routes>
            <Route path="/" element={<Login setLogin={logIn}/>}/>
            <Route path="/register" element={<Register/>}/>
            <Route path="/orders" element={<Orders/>}/>     
            <Route path="/home" element={<Home/>}/>      
        </Routes>
    </Router>
    </div>
  );
}

export default App
