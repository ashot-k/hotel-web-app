import {useState} from "react";

export const Pagination = ({pageNav}) => {
    const [currentPage, setCurrentPage] = useState(0);
    return (
        <div className="pagination d-flex flex-row gap-2">
            <button className="btn btn-primary" onClick={() => {
                pageNav(false);
                if (currentPage > 0)
                    setCurrentPage(currentPage - 1);
            }}>Previous
            </button>
            <button className="btn btn-primary current-page-button">{currentPage + 1}</button>
            <button className="btn btn-primary" onClick={() => {
                const max = pageNav(true);
                if (currentPage < max)
                    setCurrentPage(currentPage + 1);
            }
            }>Next
            </button>
            <h3>{pageNav()} Total Pages</h3>
        </div>

    )
}
