import { useState, useEffect } from "react";
import TableItems from "./tableItems";
import FormItems from "./formItems";
import Header from "../utils/header";
import Footer from "../utils/footer";
import itemServices from "../../services/itemServices";

const CrudItems = () => {
  const [items, setItems] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    itemServices.getItems().then((res) => {
      console.log(res.data);
      setItems(res.data);
    });
  }, []);

  const createData = (data: any) => {};

  const updateData = (data: any) => {};

  const deleteData = async (data: any) => {
    console.log(data);
  };
  return (
    <>
      <Header />
      <FormItems
        createData={createData}
        updateData={updateData}
        dataToEdit={dataToEdit}
        setDataToEdit={setDataToEdit}
      />
      <TableItems
        data={items}
        setDataToEdit={dataToEdit}
        deleteData={deleteData}
      />
      <Footer />
    </>
  );
};

export default CrudItems;
