import {useEffect, useState} from "react";

export const Pagination = ({pageNav}) => {
    const [currentPage, setCurrentPage] = useState(0);
    const [maxPage, setMaxPage] = useState(pageNav);
    const pageButtons = () => {
        const buttons = [];
        for (let i = currentPage; i <= maxPage; i++) {
            if (!(currentPage + 4 < i && i < maxPage)) {
                buttons.push(

                    <button key={i}
                            className={i === currentPage ? "btn btn-primary current-page-button" : "btn btn-primary"}
                            onClick={() => {
                                pageNav(i);
                                setCurrentPage(i);
                            }}>{i + 1}</button>);
            } else if (maxPage - 1 === i) {
                buttons.push(<button key={i} className="btn btn-primary">...</button>);
            }
        }
        return buttons;
    }
    return (
        <div className="pagination">
            <h5>{pageNav() + 1} Total Pages</h5>
            <div className=" d-flex flex-row gap-2 ">
                <button className="btn btn-primary" onClick={() => {
                    if (currentPage > 0) {
                        setMaxPage(pageNav(0));
                        setCurrentPage(0);
                    }
                }}> &#60;&#60;</button>
                <button className="btn btn-primary" onClick={() => {
                    if (currentPage > 0) {
                        setMaxPage(pageNav(currentPage - 1));
                        setCurrentPage(currentPage - 1);
                    }
                }}> &#60;</button>
                {pageButtons()}
                <button className="btn btn-primary" onClick={() => {
                    const max = pageNav();
                    setMaxPage(max);
                    if (currentPage < max) {
                        setMaxPage(pageNav(currentPage + 1));
                        setCurrentPage(currentPage + 1);
                    }
                }}>></button>
                <button className="btn btn-primary" onClick={() => {
                    setMaxPage(pageNav(maxPage));
                    setCurrentPage(maxPage);
                }}>>></button>
            </div>
        </div>
    )
}
