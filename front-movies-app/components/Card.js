import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import { useRouter } from "next/router";

function Card({movie}) {
  const router = useRouter();
  return (
    <ul key={movie.name} style={{display: 'flex'}} onClick={() => {router.push('/film/' + movie.name)}}>
      <div class="card" style={{ width: 300 }}>
        <img src={movie.imageURL} class="card-img-top" alt="..." />
        <div class="card-body">
          <h5 class="card-title">{movie.name}</h5>
          <p class="card-text">{movie.description}
          </p>
          <button class="btn btn-primary">
            Go somewhere
          </button>
        </div>
      </div>
    </ul>
  );

  //
}

export default Card;
