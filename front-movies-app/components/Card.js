import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import axios from 'axios'
import { useRouter } from "next/router";
import Swal from 'sweetalert2'

function Card({movie, buttonAction, orderNumber}) {
  const router = useRouter();
  const baseUrl = 'http://localhost:8080'

  const handleDeleteMovie = async () => {
    let order = {
      orderNumber : orderNumber,
      movieName: movie.name
    }
    console.log(order)
    try {
      const {data} = axios.post(`${baseUrl}/orders/deleteMovie`, order, {
        headers : {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          "MediaType": "application/json",
          "Authorization": "Bearer " + localStorage.getItem('jwt')
        }
      })
      
      Swal.fire({
        title: 'Pelicula elimnada',
        text: 'Se ha eliminado satisfactoriamente la pelicula de la orden',
        icon: 'success',
        confirmButtonText: 'Ok'
      })

      router.push('/Home')
    } catch (err) {
      Swal.fire({
        title: 'Ha habido un error',
        text: 'No se ha podido eliminar la pelicula de la orden',
        icon: 'error',
        confirmButtonText: 'Ok'
      })
    }
  }

  const handleDeleteDbMovie = async () => {
    Swal.fire({
      title:'En progreso',
      text: 'Todavía no eliminamos peliculas de la DB',
      icon: 'info',
      confirmButtonText: "Ok"
    })
  }

  // return (
  //   <ul key={movie.name} style={{display: 'flex'}}>
  //     <div className="card" style={{ width: 300 }}>
  //       <img src={movie.imageURL} className="card-img-top" alt="..." />
  //       <div className="card-body">
  //         <h5 className="card-title">{movie.name}</h5>
  //         <p className="card-text">{movie.description}
  //         </p>
  //         {buttonAction == 'Home' ? (
  //         <button className="btn btn-primary" onClick={() => {router.push('/film/' + movie.name)}}>
  //           Check details
  //         </button>
  //         ): buttonAction == 'Order' ?
  //         (
  //         <button className="btn btn-primary" onClick={handleDeleteMovie}>
  //           Remove movie
  //         </button>
  //         ) : (
  //           <button className="btn btn-primary" onClick={handleDeleteMovie}>
  //           Delete movie
  //         </button>
  //         )}
  //       </div>
  //     </div>
  //   </ul>
  // );
  return (
    <div key={movie.name} className="card ml-2 mr-2" style={{ width: '300px', display: 'flex', flexDirection: 'column', justifyContent: 'space-between', marginLeft: '10px', marginRight: '10px' }}>
      <img src={movie.imageURL} className="card-img-top" alt="..." />
      <div className="card-body">
        <h5 className="card-title">{movie.name}</h5>
        <p className="card-text">{movie.description}</p>
      </div>
      <div className="">
        {buttonAction == 'Home' ? (
          <button className="btn btn-primary mb-2" onClick={() => { router.push('/film/' + movie.name) }}>
            Check details
          </button>
        ) : buttonAction == 'Order' ?
          (
            <button className="btn btn-primary mb-2" onClick={handleDeleteMovie}>
              Remove movie
            </button>
          ) : (
            <button className="btn btn-primary mb-2" onClick={handleDeleteDbMovie}>
              Delete movie
            </button>
          )}
      </div>
    </div>
  );
}

export default Card;
