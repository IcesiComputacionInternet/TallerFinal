import axios from "axios";

const getCurrentUser = async () => {
    try {
        const {data} = await axios.get("http://localhost:8080/users/CurrentUser",
        {
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                "MediaType": "application/json",
                "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
    
        })
  
        let res = {data}
        console.log(res)
        return res
        // setCurrent(res)
        // setIsLoadingCurrent(false)
  
      } catch (err) {
        console.error("Error fetching current user:", err);
      }
}

const getMovies = async () => {
    try {
        const {data} = await axios.get("http://localhost:8080/movies/all",
        {
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                "MediaType": "application/json",
                "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
    
        })
  
        let res = {data}
        console.log(res)
        // setMovies(res)
        // setIsLoadingMovies(false)
  
      } catch (err) {
        console.error("Error fetching movies:", err);
      }
}

const getCategories = async () => {
    try {
        const {data} = await axios.get("http://localhost:8080/categories/all",
        {
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                "MediaType": "application/json",
                "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
    
        })
  
        let res = {data}
        console.log(res)
        // setCategories(res)
        // setIsLoadingCategories(false)
  
      } catch (err) {
        console.error("Error fetching categories:", err);
      }
}

export default {getCurrentUser, getMovies, getCategories}