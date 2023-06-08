export const helpHttp = () => {
  const baseUrl = "http://localhost:8080";
  const customFetch = (endpoint: string, options: any) => {
    const defaultHeaders = {
      accept: "application/json",
      "Access-Control-Allow-Origin": baseUrl,
      "Content-Type": "application/json",
    };

    //Este objeto nos ayuda si no hay respuesta del servidor para abortar la peticion
    const controller = new AbortController();
    //Agregamos el objeto controller a las opciones de la peticion
    options.signal = controller.signal;

    //Si tiene metodo el metodo, sino GET
    options.method = options.method || "GET";

    //Mezclo cabeceras
    options.headers = options.headers
      ? { ...options.headers, ...defaultHeaders }
      : defaultHeaders;

    //Cambiamos el objeto pasado como js a JSON para ingresarlo a la db
    options.body = options.body ? JSON.stringify(options.body) : false;

    //Si no hay body y es falso, elimina dicha propiedad.
    if (!options.body) delete options.body;

    //Si despues de tres segundos no hay respuesta aborta
    setTimeout(() => controller.abort(), 3000);

    return fetch(endpoint, options)
      .then((res) =>
        res.ok
          ? res.json()
          : Promise.reject({
              err: true,
              status: res.status || "00",
              statusText: res.statusText || "Ocurrio un error",
            })
      )
      .catch((err) => err);
  };

  //Cada metodo para usar
  const get = (url: string, options: any) => customFetch(url, options);

  const post = (url: string, options: any) => {
    options.method = "POST";
    return customFetch(url, options);
  };

  const put = (url: string, options: any) => {
    options.method = "PUT";
    return customFetch(url, options);
  };

  const del = (url: string, options: any) => {
    options.method = "DELETE";
    return customFetch(url, options);
  };

  return {
    get,
    post,
    put,
    del,
  };
};

export default helpHttp;
