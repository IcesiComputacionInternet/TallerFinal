import Swal from "sweetalert2";

const ModalAlertError = (title: string, msg: string) => {
  Swal.fire({
    icon: "error",
    title: `<span style="font-family: Arial, sans-serif;">${title}</span>`,
    html: `<div style="font-family: Arial, sans-serif;">${msg}</div>`,
  });
};

const ModalAlertSuccess = (title: string, msg: string) => {
  Swal.fire({
    icon: "success",
    title: `<span style="font-family: Arial, sans-serif;">${title}</span>`,
    html: `<div style="font-family: Arial, sans-serif;">${msg}</div>`,
  });
};

const ModalAlert = {
  ModalAlertError,
  ModalAlertSuccess,
};

export default ModalAlert;
