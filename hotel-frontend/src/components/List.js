import {useState} from "react";
import {Pagination} from "./Pagination";

export const List = ({data, toggleEditModal, pageNav, remove}) => {
    const [searchTerm, setSearchTerm] = useState("");
    const headers = data.length > 0 ? Object.keys(data[0]) : [];

    function isSearchTermPresent(row, searchTerm) {
        for (let key in row) {
            if (row.hasOwnProperty(key)) {
                const value = row[key];
                if (typeof value === 'string' && value.toLowerCase().includes(searchTerm.toString().toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    return (
        <div>

            <div className="d-flex justify-content-end gap-3 p-2">
                <label>Search: <input onChange={(e) => setSearchTerm(e.target.value)}/> </label>
            </div>
            <div className="p-2">
                {data.length > 0 && <Pagination pageNav={pageNav}/>}
            </div>
            <table className="table table-dark table-striped admin-table">
                <tbody>
                <tr>
                    <th>#</th>
                    {headers.map(heading => {
                        return <th key={heading}>{heading}</th>
                    })}
                    <th>Actions</th>
                </tr>
                {data.filter((row) => !searchTerm.length || isSearchTermPresent(row, searchTerm))
                    .map((item, index) => (
                        <tr key={index}>
                            <td>{index}</td>
                            {headers.map(header => (
                                <td key={header}>{item[header]}</td>
                            ))
                            }
                            <td>
                                <div className="actions">
                                    <button className="btn btn-warning" onClick={() => toggleEditModal(item)}>Edit
                                    </button>
                                    <button className="btn btn-danger" onClick={() => remove(item.id)}>Delete
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
