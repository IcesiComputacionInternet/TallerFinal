
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';

export default function MoviesList ({movies, buttonAction, orderNumber}) {
    return (
    
      <div style={{display: 'flex'}}>
        {movies.map((movie, key) => (
          <Card  movie = {movie} key={key} buttonAction={buttonAction} orderNumber={orderNumber}/>
        ))}
      </div>
    
    )
}