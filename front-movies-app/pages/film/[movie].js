import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";

const movie = (props) => {
    const router = useRouter();

    console.log("rou")
    console.log(router.query.movie)

    useEffect(() => {

        if(router.query.movie) {
            let encodedMovie = encodeURIComponent(router.query.movie)
            getData(encodedMovie)

            console.log(movie)
        }
        
    }, [router.query.movie])

    const [movie, setMovie] = useState({
        name: "",
        description: "",
        price: 0,
        imageURL: "",
        categoryName: "",
        pgRating: ""
        
        
    });

    async function getData(name) {
        const {data} = await axios.get("http://localhost:8080/movies/" + name, {

            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                "MediaType": "application/json",
                "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
        });
        console.log({data})
        console.log(data)
        setMovie({
            name: data.name,
            description: data.description,
            imageURL: data.imageURL,
            pgRating: data.pgRating,
            price: data.price,
            categoryName: data.categoryName
          });
        console.log(movie)
    }

    return(
        <div>
            <h1>Hola</h1>
        </div>
    )
}

export default movie;