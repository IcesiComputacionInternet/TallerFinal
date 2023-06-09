interface Props {
    totalPages: number;
    currentPage: number;
    handlePageChange: (pageNumber: number) => void;
}

function Pagination ({totalPages, currentPage, handlePageChange}: Props) {
    const getPageNumbers = () => {
        if (totalPages <= 5) {
            return Array.from({ length: totalPages }, (_, index) => index + 1);
        } else {
            const firstPage = 1;
            const lastPage = totalPages;
            const pagesToShow: (number | string)[] = 
                currentPage === firstPage ? [firstPage, firstPage+1, firstPage+2, '...', lastPage] : 
                (currentPage === lastPage ? [firstPage, '...', lastPage-2, lastPage-1, lastPage] : 
                [firstPage, lastPage]);
            
            if (currentPage === firstPage+1) {
                pagesToShow.splice(1, 0, firstPage+1, firstPage+2, '...');
            }
            if (currentPage === lastPage-1) {
                pagesToShow.splice(-1, 0, '...', lastPage-2, lastPage-1);
            }
            if (currentPage >= firstPage+2 && currentPage <= lastPage-2) {
                pagesToShow.splice(1, 0, '...', currentPage - 1, currentPage, currentPage + 1, '...');
            }
        
            return pagesToShow;
        }
      };


    return(
        <nav aria-label="Pagination">
      <ul className="pagination justify-content-center">
        <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
          <button className="page-link" onClick={() => handlePageChange(currentPage - 1)}>
            Anterior
          </button>
        </li>

        {getPageNumbers().map((pageNumber) => (
          <li
            className={`page-item ${currentPage === pageNumber ? 'active' : ''}`}
            key={pageNumber}
          >
            <button
              className="page-link"
              onClick={() => {
                if (typeof pageNumber === 'number') {
                  handlePageChange(pageNumber);
                }
              }}
              disabled={typeof pageNumber !== 'number'}
            >
              {pageNumber}
            </button>
          </li>
        ))}

        <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
          <button className="page-link" onClick={() => handlePageChange(currentPage + 1)}>
            Siguiente
          </button>
        </li>
      </ul>
    </nav>
    )
}

export default Pagination;