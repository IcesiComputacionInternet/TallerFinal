import { useEffect, useState } from "react";
import { getItemsPage } from "../../services/items";
import Item from "./components/Item";
import Pagination from "../../components/Pagination";

interface Props {
  isLogged: boolean;
  setInfoToast: (message: string, title: string) => void;
}

function Home ({isLogged, setInfoToast}: Props) {
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [filter, setFilter] = useState("");
  const [items, setItems] = useState([]);

  useEffect(() => {
    getItemsPage(currentPage, filter).then((result) => {
      setTotalPages(result.totalPages);
      setItems(result.items);
    });
  }, [currentPage, filter]);

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  const handleFilterChange = (event: any) => {
    setFilter(event.target.value);
  };

  return (
    <div className="container">
      <div className="d-flex flex-column align-items-center">
        <input
          type="text"
          className="form-control w-50 my-2"
          placeholder="Search"
          onChange={handleFilterChange}
        />
      </div>
      <div className="d-flex flex-column align-items-center">
        <h1 className="text-center">Productos</h1>
          {items.map((item: any) => (
            <div key={item.id}>
              <Item item={item} isLogged={isLogged} setInfoToast={setInfoToast}/>
            </div>
          ))}
      </div>
      <Pagination 
        totalPages={totalPages} 
        currentPage={currentPage}
        handlePageChange={handlePageChange}
      />
    </div>
  );
};

export default Home;