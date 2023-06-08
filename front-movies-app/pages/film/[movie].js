import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";

const movie = (props) => {
    const router = useRouter();

    console.log("rou")
    console.log(router.query.movie)

    useEffect(() => {

        if(router.query.movie) {
            console.log(router.query.movie)
            var res = router.query.movie.replace("%", " "); 
            getData(res)
        }
        
    }, [router.query.movie])

    const [movie, setMovie] = useState({
        name: "",
        description: "",
        imageURL: "",
        pgRating: "",
        price: 0,
        categoryName: ""
    });

    async function getData(name) {
        const res = await axios.get("http://localhost:8080/" + name, {

            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                "MediaType": "application/json",
                "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
        });
        const data = await res.json()
        setMovie({name: data.username, pass: data.userpass, picture: data.picture, topsong: data.topsong, desc: data.description, mail: data.mail})
        console.log(movie)
    }

    return(
        <div>
            <h1>Hola</h1>
        </div>
    )
}

export default movie;