import React, {useState, useEffect} from "react";
import axios from "axios";
import {NavigateFunction,useNavigate} from "react-router-dom";
import Logout from "./Logout";
import Navbar from "./Navbar";

const baseUrl="http://localhost:8091";


function Draft(){

        
    return (
        <div>
          <Navbar/>
        </div>
      );
}
export default Draft;