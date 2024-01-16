import {useState} from "react";

export const List = ({data, deleteEntry}) => {
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
            <div className="d-flex justify-content-center gap-3">
                <label>Search: <input onChange={(e) => setSearchTerm(e.target.value)}/> </label>
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
                            ))}
                            <td>
                                <div className="actions">
                                    <button className="btn btn-warning">Edit</button>
                                    <button className="btn btn-danger" onClick={() => deleteEntry(item.id)}>Delete
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
