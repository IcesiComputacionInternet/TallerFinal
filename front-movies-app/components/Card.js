import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'

function Card({movie}) {
  return (
    <div class="card" style={{ width: 300 }}>
      <img src="/dc.jpg" class="card-img-top" alt="..." />
      <div class="card-body">
        <h5 class="card-title">{movie.name}</h5>
        <p class="card-text">{movie.description}
        </p>
        <a href="#" class="btn btn-primary">
          Go somewhere
        </a>
      </div>
    </div>
  );
}

export default Card;
