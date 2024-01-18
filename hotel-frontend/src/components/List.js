import {useState} from "react";
import {Pagination} from "./Pagination";

export const List = ({data, isSearchTermPresent, toggleAddModal, toggleEditModal, setDataChanged, pageNav, remove}) => {
    const [searchTerm, setSearchTerm] = useState("");
    const headers = data.length > 0 ? Object.keys(data[0]) : [];

    return (
        <div>
            <div className="d-flex flex-row justify-content-end gap-3 p-2">
                <label>Search:
                    <input onChange={(e) => {
                        if (e.target.value === '')
                            isSearchTermPresent();
                        setSearchTerm(e.target.value);
                    }} onKeyDown={(e) => {
                        if (e.key === 'Enter')
                            isSearchTermPresent(searchTerm);
                    }}/></label>
            </div>
            <div className="p-3 d-flex justify-content-between">
                {data.length > 0 && pageNav && <Pagination pageNav={pageNav}/>}
                <button className="btn btn-success" type="button" onClick={() => toggleAddModal()}>Add new Entry
                </button>
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
                {data.map((item, index) => (
                    <tr key={index}>
                        <td>{index + 1}</td>
                        {headers.map(header => {
                            if (header === 'imageUrl')
                                return <td key={header}><img src={item[header]} width={"55px"} height={"55px"}
                                                             alt=""></img></td>
                            else
                                return <td key={header}>{item[header]}</td>
                        })}
                        <td>
                            <div className="actions">
                                <button className="btn btn-warning" onClick={() => toggleEditModal(item)}>
                                    Edit
                                </button>
                                <button className="btn btn-danger"
                                        onClick={() => remove(item.id, setDataChanged)}>
                                    Delete
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
