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
        navigation("/items");
      };
    
    return (
        <div>
          <div
            className="p-3 navbar bg-body-tertiary"
            style={{ backgroundColor: "#e3f2fd" }}
          >
            <div className="col-1">
              <h4>Home</h4>
            </div>
            <div className="col-1">
              <Logout />
            </div>
          </div>
          <div className="m-3">
            <h2>Create Items</h2>
          </div>
    
            <div>
                
                <form onSubmit={handleSubmit}>
                  <div className="form-group row">
                    <label htmlFor="inputName" className="col-sm-2 col-form-label">Name</label>
                    <div className="col-sm-10">
                      <input
                         type="text" 
                         className="form-control" 
                         id="inputName" 
                         placeholder="Name"
                         name="name"
                         value={formData.name}
                         onChange={handleChange}
                         />
                    </div>
                  </div>
                  
                  <div className="form-group row">
                    <label htmlFor="textarea1" className="col-sm-2 col-form-label">Description</label>
                    <div className="col-sm-10">
                      <textarea 
                      className="form-control" 
                      id="textarea1" 
                      rows={3}
                      name="description"
                      value={formData.description}
                      onChange={handleChange}></textarea>
                    </div>
                  </div>

                  <div className="form-group row">
                    <label htmlFor="inputPrice" className="col-sm-2 col-form-label">Price</label>
                    <div className="col-sm-10">
                      <input 
                        type="number" 
                        className="form-control" 
                        id="inputPrice" 
                        placeholder="$"
                        name="price"
                        value={formData.price}
                        onChange={handleChange}/>
                    </div>
                  </div>
                 
                  <div className="form-group row">
                    <label htmlFor="inputImg" className="col-sm-2 col-form-label">Image</label>
                    <div className="col-sm-10">
                      <input 
                        type="text" 
                        className="form-control" 
                        id="inputImg" 
                        placeholder="URL"
                        name="imgURL"
                         value={formData.imgURL}
                         onChange={handleChange}/>
                    </div>
                  </div>


                  <div className="form-group row">
                    <label htmlFor="selectCategory" className="col-sm-2 col-form-label">Category</label>
                    <div className="col-sm-10">
                      <select 
                        className="form-control" 
                        id="selectCategory" 
                        name="category"
                         value={formData.category}
                         onChange={handleChange}>

                        <option value="">Choose...</option>
                        {
                          categories.map((category)=>(
                            <option key={category.name}>{category.name}</option>
                          ))
                        }
                      </select>
                    </div>                    
                  </div>

                  <div className="form-group row">
                    <label htmlFor="inputMinV" className="col-sm-2 col-form-label">Minimun Voltage</label>
                    <div className="col-sm-10">
                      <input 
                        type="number" 
                        className="form-control" 
                        id="inputMinV" 
                        placeholder="0"
                        name="minVoltage"
                        value={formData.minVoltage}
                        onChange={handleChange}/>
                    </div>
                  </div>

                  <div className="form-group row">
                    <label htmlFor="inputMaxV" className="col-sm-2 col-form-label">Maximun Voltage</label>
                    <div className="col-sm-10">
                      <input 
                        type="number" 
                        className="form-control" 
                        id="inputMaxV" 
                        placeholder="0"
                        name="maxVoltage"
                         value={formData.maxVoltage}
                         onChange={handleChange}/>
                    </div>
                  </div>


                  <div className="form-group row">
                    <label htmlFor="inputSOE" className="col-sm-2 col-form-label">Source of energy</label>
                    <div className="col-sm-10">
                      <input 
                        type="text" 
                        className="form-control" 
                        id="inputSOE" 
                        placeholder="Source of energy"
                        name="sourceOfEnergy"
                         value={formData.sourceOfEnergy}
                         onChange={handleChange}/>
                    </div>
                  </div>

                  <div className="form-group row">
                    <label htmlFor="selectLevel" className="col-sm-2 col-form-label">Level of Efficiency</label>
                    <div className="col-sm-10">
                      <select 
                        className="form-control" 
                        id="selectLevel" 
                        name="levelOfEfficiency"
                         value={formData.levelOfEfficiency}
                         onChange={handleChange}>
                        <option value="">Choose...</option>
                        <option>A</option>
                        <option>B</option>
                        <option>C</option>
                        <option>D</option>
                        <option>E</option>
                        <option>F</option>
                        <option>G</option>
                      </select>
                    </div>                    
                  </div>

                  <div className="form-group row">
                    <label htmlFor="inputBrand" className="col-sm-2 col-form-label">Brand</label>
                    <div className="col-sm-10">
                      <input 
                        type="text" 
                        className="form-control" 
                        id="inputBrand" 
                        placeholder="Brand"
                        name="marca"
                         value={formData.marca}
                         onChange={handleChange}/>
                    </div>
                  </div>

                  <div className="form-group row">
                    <label htmlFor="inputModel" className="col-sm-2 col-form-label">Model</label>
                    <div className="col-sm-10">
                      <input 
                        type="text" 
                        className="form-control" 
                        id="inputModel" 
                        placeholder="Model"
                        name="model"
                         value={formData.model}
                         onChange={handleChange}/>
                    </div>
                  </div>

                  <div className="form-group row">
                    <label htmlFor="inputGu" className="col-sm-2 col-form-label">Guarantee</label>
                    <div className="col-sm-10">
                      <input 
                        type="number" 
                        className="form-control" 
                        id="inputGu" 
                        placeholder="Months"
                        name="guarantee"
                         value={formData.guarantee}
                         onChange={handleChange}/>
                    </div>
                  </div>

                 
                  <div className="form-group row">
                    <div className="col-sm-2"></div>
                    <div className="col-sm-10">
                      <div className="form-check">
                        <input 
                        className="form-check-input" 
                        type="checkbox" id="gridCheck1" 
                        name="available"
                        onChange={handleChange} 
                         checked ={formData.available}/>
                        <label className="form-check-label" htmlFor="gridCheck1">
                          Available
                        </label>
                      </div>
                    </div>
                  </div>


                  <div className="form-group row">
                    <div className="col-sm-10">
                      <button type="submit" className="btn btn-primary">Create Item</button>
                    </div>
                  </div>
                  <br />
                  <div className="col-sm-10">
                      <button type="submit" className="btn btn-secondary" onClick={handleClick}>Volver</button>
                  </div>

                </form>

            </div>
        </div>
      );
}
export default CreateItems;