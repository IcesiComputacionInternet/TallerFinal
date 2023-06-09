
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';

export default function MoviesList ({movies}) {
    return (
    
      <div style={{display: 'flex'}}>
        {movies.map((movie, key) => (
          <Card movie = {movie} key={key}/>
        ))}
      </div>
    
    )
}