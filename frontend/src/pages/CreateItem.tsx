// @ts-ignore
import React, { useState } from "react";
import '../style/form.css'
import { NavigateFunction, useNavigate } from "react-router-dom";

const CreateItem = () => {
    const [cellname, setCellName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [brand, setBrand] = useState("");
    const [storage, setStorage] = useState("");
    const [ram, setRam] = useState("");
    const [os, setOs] = useState("");
    const [frontCamera, setFrontCamera] = useState("");
    const [mainCamera, setMainCamera] = useState("");
    const [screenSize, setScreenSize] = useState("");
    const [stock, setStock] = useState("");
    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: any) => {
        event.preventDefault();
    };

    const cancelNewItem = () => {
        navigation("/");
    }

    return (
        <form className="new-item">
            <div className="row g-lg-0">
                <h3>New Item</h3>
                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="text"
                               className="input-field"
                               placeholder="Name"
                               value={cellname}
                               onChange={(event) => setCellName(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Name</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="text"
                               className="input-field"
                               placeholder="Brand"
                               value={brand}
                               onChange={(event) => setBrand(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Brand</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>


                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="number"
                               min="1"
                               className="input-field"
                               placeholder="Price"
                               value={price}
                               onChange={(event) => setPrice(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Price</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="number"
                               min="64"
                               className="input-field"
                               placeholder="Storage"
                               value={storage}
                               onChange={(event) => setStorage(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Storage</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="text"
                               min="64"
                               className="input-field"
                               placeholder="Operating System"
                               value={os}
                               onChange={(event) => setOs(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Operating System</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="number"
                               min="2"
                               className="input-field"
                               placeholder="RAM"
                               value={ram}
                               onChange={(event) => setRam(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">RAM</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="number"
                               min="1"
                               className="input-field"
                               placeholder="Front Camera"
                               value={frontCamera}
                               onChange={(event) => setFrontCamera(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Front Camera</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>


                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="number"
                               min="1"
                               className="input-field"
                               placeholder="Main Camera"
                               value={mainCamera}
                               onChange={(event) => setMainCamera(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Main Camera</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="number"
                               min="1"
                               className="input-field"
                               placeholder="Screen Size"
                               value={screenSize}
                               onChange={(event) => setScreenSize(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Screen Size</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <input required
                               type="number"
                               min="1"
                               className="input-field"
                               placeholder="Stock"
                               value={stock}
                               onChange={(event) => setStock(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Stock</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-6">
                    <div className="input-container">
                        <textarea required
                               className="input-field"
                                  placeholder="Description"
                                  value={description}
                                  onChange={(event) => setDescription(event.target.value)}
                        />
                        <label htmlFor="input-field" className="input-label">Description</label>
                        <span className="input-highlight"></span>
                    </div>
                </div>

                <div className="col-md-5">
                    <select id="inputState" className="form-select" style={{marginTop:"40px"}}>
                        <option selected>Category</option>
                        <option>...</option>
                    </select>
                </div>
                </div>
            <div className="button-cont-item">
                <button className="btn btn-primary">Done</button>
                <button className="btn btn-outline-danger" onClick={cancelNewItem}>Cancel</button>
        </div>
        </form>
    );
};
export default CreateItem;