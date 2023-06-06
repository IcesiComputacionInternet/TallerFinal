import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import TableItems from "./tableItems";
import FormItems from "./formItems";
import Header from "../utils/header";
import Footer from "../utils/footer";
import ItemServices from "../../services/itemServices";
import CategoryServices from "../../services/categoryServices";

const CrudItems = () => {
  const [items, setItems] = useState<string[]>([]);
  const [categories, setCategories] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    ItemServices.getItems().then((res) => {
      console.log(res.data);
      setItems(res.data);
    });
    CategoryServices.getCategories().then((res) => {
      const newCategories = res.data.map((element: any) => element.name);
      setCategories((prevCategories) => [...prevCategories, ...newCategories]);
    });
  }, []);

  const createData = (data: any) => {
    ItemServices.createItem(data).then((res) => {
      console.log(res);
      setItems((prevItems) => [...prevItems, res.data]);
    });
  };

  const updateData = (data: any) => {
    let updatedItem: any = null;
    let newItems = items.map((el: any) => {
      if (el.name === data.name) {
        updatedItem = data;
        return data;
      }
      return el;
    });

    ItemServices.updateItem(updatedItem).then((_res) => {
      setItems(newItems);
    });
  };

  const deleteData = async (data: any) => {
    try {
      await ItemServices.deleteItem(data);
      const filteredItems = items.filter((el: any) => el.name !== data);
      setItems(filteredItems);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <Header />
      <FormItems
        categories={categories}
        createData={createData}
        updateData={updateData}
        dataToEdit={dataToEdit}
        setDataToEdit={setDataToEdit}
      />
      <TableItems
        data={items}
        setDataToEdit={setDataToEdit}
        deleteData={deleteData}
      />
      <Link to="/" style={{ textAlign: "center" }}>
        <Typography>Volver</Typography>
      </Link>
      <Footer />
    </>
  );
};

export default CrudItems;
