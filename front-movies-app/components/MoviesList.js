
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';

export default function MoviesList ({movies}) {
    return (<div>
      {movies.data.map((movie) => (
        <h1>{movie.name}</h1>
      ))}
      </div>
    )
}