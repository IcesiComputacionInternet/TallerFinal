import React from "react";
import { useState, useEffect } from "react";

const item = {
  name: "",
  description: "",
  price: "",
  categoryId: "",
  imageUrl: "",
};

interface IProps {
  createData: any;
  updateData: any;
  dataToEdit: any;
  setDataToEdit: any;
}

const FormItems: React.FC<IProps> = ({
  createData,
  updateData,
  dataToEdit,
  setDataToEdit,
}) => {
  const [dataItem, setDataItem] = useState(item);

  useEffect(() => {
    dataToEdit ? setDataItem(dataToEdit) : setDataItem(item);
  }, [dataToEdit]);
};

export default FormItems;
