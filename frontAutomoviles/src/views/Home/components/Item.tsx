interface Props {
    item: {
        itemId: string;
        name: string;
        description: string;
        price: number;
        imageUrl: string;
        category: {
            name: string;
            description: string;
        }
    },
    isLogged: boolean;
}

function Item  ({item, isLogged}:Props) {
    const handleAddToCart = () => {
        if(isLogged){
            console.log('Add to cart');
        }
    }

    return (
        <div className="card text-bg-dark mb-3" style={{maxWidth: '750px'}}>
            <div className="row g-0">
                <div className="col-md-4 d-flex justify-content-center align-items-center">
                    <img 
                        src={item.imageUrl} 
                        className="card-img-top" 
                        alt={item.name} 
                    />
                </div>
                <div className="col-md-8">
                    <div className="card-body d-flex flex-column">
                        <div className="d-flex justify-content-between align-items-center mb-3">
                            <h5 className="card-title">{item.name}</h5>
                            <button className="btn btn-primary" onClick={handleAddToCart}>Add to cart</button>
                        </div>
                        <p className="card-text">{item.description}</p>
                        <p className="card-text">Price: {item.price}</p>
                        <p className="card-text">Category: {item.category.name}</p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Item;