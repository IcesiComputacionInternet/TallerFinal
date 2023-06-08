import { useState } from 'react'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './Login';
import Register from './Register';
import '../styles/App.css'
import Orders from './Orders';

function App() {
  return (
    <div className="App">
    <Router>
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
            <Route path="/orders" element={<Orders/>}/>            
        </Routes>
    </Router>
    </div>
  )
}

export default App
