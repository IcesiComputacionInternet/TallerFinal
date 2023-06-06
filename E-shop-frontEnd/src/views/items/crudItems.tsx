import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import TableItems from "./tableItems";
import FormItems from "./formItems";
import Header from "../utils/header";
import Footer from "../utils/footer";
import ItemServices from "../../services/itemServices";
import CategoryServices from "../../services/categoryServices";
import ModalAlert from "../utils/modalAlert";

const CrudItems = () => {
  const [items, setItems] = useState<string[]>([]);
  const [categories, setCategories] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    try {
      ItemServices.getItems().then((res) => {
        console.log(res.data);
        setItems(res.data);
      });
      CategoryServices.getCategories().then((res) => {
        const newCategories = res.data.map((element: any) => element.name);
        setCategories((prevCategories) => [
          ...prevCategories,
          ...newCategories,
        ]);
      });
    } catch (error) {
      ModalAlert.ModalAlertError("Error", "Error al cargar los items");
    }
  }, []);

  const createData = (data: any) => {
    try {
      ItemServices.createItem(data).then((res) => {
        console.log(res);
        setItems((prevItems) => [...prevItems, res.data]);
      });
      ModalAlert.ModalAlertSuccess("Éxito", "Item creado correctamente");
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "Error al crear el item, puede que ya exista otro producto con el mismo nombre"
      );
    }
  };

  const updateData = (data: any) => {
    try {
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
      ModalAlert.ModalAlertSuccess("Exito", "Item actualizado correctamente");
    } catch (error) {
      ModalAlert.ModalAlertError("Error", "No se ha podido actualizar el Item");
    }
  };

  const deleteData = async (data: any) => {
    try {
      await ItemServices.deleteItem(data);
      const filteredItems = items.filter((el: any) => el.name !== data);
      setItems(filteredItems);
      ModalAlert.ModalAlertSuccess(
        "Exito",
        "Se ha borrado el Item correctamente"
      );
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se ha podido borrar el Item, puede que este instanciado en otro objeto"
      );
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
