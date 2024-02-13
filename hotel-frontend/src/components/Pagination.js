import {useEffect, useState} from "react";

export const Pagination = ({totalPages, totalElements, pageChange, setPageSize}) => {

    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        setCurrentPage(0);
        pageChange(0);
    }, [totalElements]);
    const pageNavButtons = () => {
        const buttons = [];

        for (let i = currentPage; i < totalPages; i++) {
            if (!(currentPage + 3 < i && i < totalPages)) {
                buttons.push(<button key={i}
                                     className={i === currentPage ? "btn btn-success" : "btn btn-primary"}
                                     onClick={() => {
                                         pageChange(i);
                                         setCurrentPage(i);
                                     }}>{i + 1}</button>);
            } else if (totalPages - 1 === i)
                buttons.push(<button key={i} className="btn btn-primary">...</button>);
        }
        return buttons;
    }
    return (
        <div className="d-flex flex-column align-items-center p-2 gap-1">
            <div className="d-flex gap-2">
                <h5>Entries per Page</h5>
                <select onChange={(e) => {
                    pageChange(0);
                    setCurrentPage(0);
                    setPageSize(e.target.value)
                }} defaultValue={25}>
                    <option value={25}>25</option>
                    <option value={50}>50</option>
                    <option value={100}>100</option>
                    <option value={250}>250</option>
                </select>
            </div>
            <br/>
            <div className="d-flex flex-column justify-content-center align-items-center">
                <h5>{totalPages} Pages, {totalElements} Entries</h5>
                <div className="d-flex flex-row flex-wrap gap-1">
                    {(currentPage !== 0) && <button className="btn btn-primary" onClick={() => {
                        if (currentPage > 0) {
                            pageChange(0);
                            setCurrentPage(0);
                        }
                    }}>&#60;&#60;</button>}
                    <button className="btn btn-primary" onClick={() => {
                        if (currentPage > 0) {
                            pageChange(currentPage - 1);
                            setCurrentPage(currentPage - 1);
                        }
                    }}>&#60;</button>
                    {pageNavButtons()}
                    {(currentPage + 1 !== totalPages) && <button className="btn btn-primary" onClick={() => {
                        const max = pageChange();
                        pageChange(max);
                        if (currentPage < max) {
                            pageChange(currentPage + 1);
                            setCurrentPage(currentPage + 1);
                        }
                    }}>></button>}
                    <button className="btn btn-primary" onClick={() => {
                        pageChange(totalPages - 1);
                        setCurrentPage(totalPages - 1);
                    }}>>>
                    </button>
                </div>
            </div>
        </div>
    )
}
