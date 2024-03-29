import {useState} from "react";
import {roomImagesURL} from "../URLs";

export const List = ({data, isSearchTermPresent, toggleAddModal, toggleEditModal, setDataChanged, remove}) => {
    const [searchTerm, setSearchTerm] = useState("");
    const headers = data.length > 0 ? Object.keys(data[0]) : [];

    return (
        <div style={{minHeight: "500px"}}>
            <div className="d-flex flex-row justify-content-end align-items-center gap-3 p-3">
                <button className="btn btn-success" type="button" onClick={() => toggleAddModal()}>Add new Entry
                </button>
                <label>Search:
                    <input onChange={(e) => {
                        if (e.target.value === '')
                            isSearchTermPresent();
                        setSearchTerm(e.target.value);
                    }} onKeyDown={(e) => {
                        if (e.key === 'Enter')
                            isSearchTermPresent(searchTerm);
                    }}/>
                </label>
            </div>
            {data && data.length > 0 &&
                <div className="table-container">
                    <table className="table table-dark table-striped">
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
                                        return <td key={header}><img
                                            src={roomImagesURL + item.name} width={"80px"}
                                            height={"50px"}
                                            alt="Image Not Available"></img><p style={{fontSize: "12px"}}>{item.imageUrl} </p></td>
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
                ||
                <div className="d-flex flex-column justify-content-center align-items-center" style={{height: "90%"}}>
                    <h1>No Entries</h1>
                </div>
            }
        </div>
    );
}
