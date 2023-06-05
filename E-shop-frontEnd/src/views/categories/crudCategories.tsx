import { useState, useEffect } from "react";
import CategoryServices from "../../services/categoryServices";
import FormCategory from "./formCategories";
import TableCategories from "./tableCategories";
import Footer from "../utils/footer";
import Header from "../utils/header";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";

const CrudCategory = () => {
  const [categories, setCategories] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    CategoryServices.getCategories().then((res) => {
      setCategories(res.data);
    });
  }, []);

  const createData = (data: any) => {
    CategoryServices.create(data).then((res) => {
      setCategories((prevCategories) => [...prevCategories, res.data]);
    });
  };

  const updateData = (data: any) => {
    let updatedCategory: any = null;
    let newCategories = categories.map((el: any) => {
      if (el.name === data.name) {
        updatedCategory = data;
        return data;
      }
      return el;
    });

    CategoryServices.update(updatedCategory).then((_res) => {
      setCategories(newCategories);
    });
  };

  const deleteData = async (data: any) => {
    try {
      await CategoryServices.deleteCategory(data);
      const filteredCategories = categories.filter(
        (el: any) => el.name !== data
      );
      setCategories(filteredCategories);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <Header />
      <div className="container-add-role">
        <FormCategory
          createData={createData}
          updateData={updateData}
          dataToEdit={dataToEdit}
          setDataToEdit={setDataToEdit}
        />
        <TableCategories
          data={categories}
          setDataToEdit={setDataToEdit}
          deleteData={deleteData}
        />
      </div>
      <Link to="/" style={{ textAlign: "center" }}>
        <Typography>Volver</Typography>
      </Link>
      <Footer />
    </>
  );
};

export default CrudCategory;
