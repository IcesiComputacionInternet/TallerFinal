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

          <div className="container">
             
                  <div className="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                      
                      <div className="row">
                          <div className="text-center">
                              <h1>Receipt</h1>
                          </div>
                          
                          <table className="table table-hover">
                              <thead>
                                  <tr>
                                      <th>Product</th>
                                      <th>#</th>
                                      <th className="text-center">Price</th>
                                      <th className="text-center">Total</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr>
                                      <td className="col-md-9"><em>Baked Rodopa Sheep Feta</em></td>
                                      <td className="col-md-1" style={{textAlign: "center"}}> 2 </td>
                                      <td className="col-md-1 text-center">$13</td>
                                      <td className="col-md-1 text-center">$26</td>
                                  </tr>
                                  <tr>
                                      <td className="col-md-9"><em>Lebanese Cabbage Salad</em></td>
                                      <td className="col-md-1" style={{textAlign: "center"}}> 1 </td>
                                      <td className="col-md-1 text-center">$8</td>
                                      <td className="col-md-1 text-center">$8</td>
                                  </tr>
                                  <tr>
                                      <td className="col-md-9"><em>Baked Tart with Thyme and Garlic</em></td>
                                      <td className="col-md-1" style={{textAlign: "center"}}> 3 </td>
                                      <td className="col-md-1 text-center">$16</td>
                                      <td className="col-md-1 text-center">$48</td>
                                  </tr>
                                 
                                  <tr>
                                      <td>   </td>
                                      <td>   </td>
                                      <td className="text-right"><h4><strong>Total: </strong></h4></td>
                                      <td className="text-center text-danger"><h4><strong>$31.53</strong></h4></td>
                                  </tr>
                              </tbody>
                          </table>
                      
                  </div>
              </div>
              </div>
        </div>
      );
}
export default Draft;