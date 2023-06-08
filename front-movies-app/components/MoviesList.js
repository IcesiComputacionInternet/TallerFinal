
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';

export default function MoviesList ({movies}) {
    return (
    
      <div style={{display: 'flex'}}>
        {movies.data.map((movie) => (
          <Card movie = {movie}/>
        ))}
      </div>
    
    )
}