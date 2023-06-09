import React, {useState, useEffect} from "react";
import axios from "axios";
import {NavigateFunction,useNavigate} from "react-router-dom";
import Logout from "./Logout";

const baseUrl="http://localhost:8091";


function CreateItems(){

    const navigation : NavigateFunction = useNavigate();

    const [categories,setCategories]=useState([])

    const [formData, setFormData] = useState({
        name: '',
        description: '',
        price: 0,
        imgURL:'',
        category:'',
        minVoltage:0,
        maxVoltage:0,
        sourceOfEnergy:'',
        levelOfEfficiency:'',
        marca:'',
        model:'',
        guarantee:0,
        available:true
      });

      useEffect(()=>{
        const fetchItems = async () => {
          var token=localStorage.getItem("jwt");
          const response = await axios.get(
              baseUrl + "/categories",
              { headers: { 
                  "Access-Control-Allow-Origin":baseUrl,
                  "MediaType":"application/json",
                  Authorization: `Bearer ${token}` 
              } }
              );
          const responseData = response.data;
          setCategories(responseData);
      };
  
      fetchItems();
      }, []);
    
      const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        const convertedValue = type === "checkbox" ? checked : type === "number" ? Number(value) : value;

        setFormData({
          ...formData,
          [name]: convertedValue
        });
      };
    
      const handleSubmit = async (event: any) => {
        event.preventDefault();
        try {
          var token=localStorage.getItem("jwt");
          const response = await axios.post(
            baseUrl+"/items",
            {
              name: formData.name,
              description: formData.description,
              price: formData.price,
              imageUrl:formData.imgURL,
              category:formData.category,
              minVoltage:formData.minVoltage,
              maxVoltage:formData.maxVoltage,
              sourceOfEnergy:formData.sourceOfEnergy,
              levelOfEfficiency:formData.levelOfEfficiency,
              marca:formData.marca,
              model:formData.model,
              guarantee:formData.guarantee,
              available:formData.available
            },
            {
              headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "MediaType":"application/json",
                Authorization: `Bearer ${token}` 
              },
            }
          );
  
          alert("Item created")
        } catch (error) {
          
        }
        setFormData({
          name: '',
          description: '',
          price: 0,
          imgURL:'',
          category:'',
          minVoltage:0,
          maxVoltage:0,
          sourceOfEnergy:'',
          levelOfEfficiency:'',
          marca:'',
          model:'',
          guarantee:0,
          available:true
        });
      };

      const handleClick = async (event: any) => {
        event.preventDefault();
        navigation("/users/home-shop");
      };

      return (
        <div>
          <div className="p-3 navbar bg-body-tertiary" style={{ backgroundColor: "#e3f2fd" }}>
            <div className="col-1">
              <h4>Home</h4>
            </div>
            <div className="col-1">
              <Logout />
            </div>
          </div>
          <div className="m-3 text-center">
            <h2>Create Items</h2>
          </div>
      
          <div className="container d-flex justify-content-center">
            <div className="scroll-container" style={{ height: "500px", overflowY: "scroll", width: "50%" }}>
              <form onSubmit={handleSubmit}>
                <div className="row">
                  <div className="col-md-12">
                    <ul className="list-group">
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputName">Name</label>
                        <input
                          type="text"
                          className="form-control"
                          id="inputName"
                          placeholder="Name"
                          name="name"
                          value={formData.name}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="textarea1">Description</label>
                        <textarea
                          className="form-control"
                          id="textarea1"
                          rows={3}
                          name="description"
                          value={formData.description}
                          onChange={handleChange}
                        ></textarea>
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputPrice">Price</label>
                        <input
                          type="number"
                          className="form-control"
                          id="inputPrice"
                          placeholder="$"
                          name="price"
                          value={formData.price}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputImg">Image</label>
                        <input
                          type="text"
                          className="form-control"
                          id="inputImg"
                          placeholder="URL"
                          name="imgURL"
                          value={formData.imgURL}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="selectCategory">Category</label>
                        <select
                          className="form-control"
                          id="selectCategory"
                          name="category"
                          value={formData.category}
                          onChange={handleChange}
                        >
                          <option value="">Choose...</option>
                          {categories.map((category) => (
                            <option key={category.name}>{category.name}</option>
                          ))}
                        </select>
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputMinV">Minimum Voltage</label>
                        <input
                          type="number"
                          className="form-control"
                          id="inputMinV"
                          placeholder="0"
                          name="minVoltage"
                          value={formData.minVoltage}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputMaxV">Maximum Voltage</label>
                        <input
                          type="number"
                          className="form-control"
                          id="inputMaxV"
                          placeholder="0"
                          name="maxVoltage"
                          value={formData.maxVoltage}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputSOE">Source of Energy</label>
                        <input
                          type="text"
                          className="form-control"
                          id="inputSOE"
                          placeholder="Source of energy"
                          name="sourceOfEnergy"
                          value={formData.sourceOfEnergy}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="selectLevel">Level of Efficiency</label>
                        <select
                          className="form-control"
                          id="selectLevel"
                          name="levelOfEfficiency"
                          value={formData.levelOfEfficiency}
                          onChange={handleChange}
                        >
                          <option value="">Choose...</option>
                          <option>A</option>
                          <option>B</option>
                          <option>C</option>
                          <option>D</option>
                          <option>E</option>
                          <option>F</option>
                          <option>G</option>
                        </select>
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputBrand">Brand</label>
                        <input
                          type="text"
                          className="form-control"
                          id="inputBrand"
                          placeholder="Brand"
                          name="marca"
                          value={formData.marca}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputModel">Model</label>
                        <input
                          type="text"
                          className="form-control"
                          id="inputModel"
                          placeholder="Model"
                          name="model"
                          value={formData.model}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <label htmlFor="inputGu">Guarantee</label>
                        <input
                          type="number"
                          className="form-control"
                          id="inputGu"
                          placeholder="Months"
                          name="guarantee"
                          value={formData.guarantee}
                          onChange={handleChange}
                        />
                      </li>
                      <li className="list-group-item mb-3">
                        <div className="form-check">
                          <input
                            className="form-check-input"
                            type="checkbox"
                            id="gridCheck1"
                            name="available"
                            onChange={handleChange}
                            checked={formData.available}
                          />
                          <label className="form-check-label" htmlFor="gridCheck1">
                            Available
                          </label>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="row justify-content-center d-flex">
                  <div className="col-md-6 justify-content-between d-flex">
                    <button type="submit" className="btn btn-primary btn-block">
                      Create Item
                    </button>
                    <button type="button" className="btn btn-secondary btn-block" onClick={handleClick}>
                      Volver
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      );
}
export default CreateItems;