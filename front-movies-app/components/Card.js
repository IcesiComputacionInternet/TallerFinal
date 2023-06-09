import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import { useRouter } from "next/router";

function Card({movie}) {
  const router = useRouter();
  return (
    <ul key={movie.name} style={{display: 'flex'}} onClick={() => {router.push('/film/' + movie.name)}}>
      <div className="card" style={{ width: 300 }}>
        <img src={movie.imageURL} className="card-img-top" alt="..." />
        <div className="card-body">
          <h5 className="card-title">{movie.name}</h5>
          <p className="card-text">{movie.description}
          </p>
          <button className="btn btn-primary">
            Go somewhere
          </button>
        </div>
      </div>
    </ul>
  );

  //
}

export default Card;
