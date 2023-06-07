import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios'
import { useEffect, useState } from 'react';

function Carousel() {

  const [movies, setMovies] = useState([])
  const apiKey = 'k_quwg0qhg'

  useEffect( () => {
    axios.get("https://imdb-api.com/en/API/Top250Movies/k_quwg0qhg").then(response => {
      setMovies((response.data))
      //console.log(movies)
    }).catch(err => console.error(err))
  }, [])

  return (
    <div id="carouselExampleIndicators" className="carousel slide">
      <div className="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="0"
          className="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div className="carousel-inner">
        <div className="carousel-item active">
          <img src="/carouselEx1.jpg" className="d-block w-100" alt="1" />
        </div>
        <div className="carousel-item">
          <img src="/dc.jpg" className="d-block w-100" alt="2" />
        </div>
        <div className="carousel-item">
          <img src="/avatar.jpg" className="d-block w-100" alt="3" />
        </div>
      </div>
      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
}

export default Carousel;
