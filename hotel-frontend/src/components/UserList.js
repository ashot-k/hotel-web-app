import {useState} from "react";
import CreationForm from "./CreationForm"
import {initialValues, inputs} from "./UserFormFields";

export const UserList = ({users, deleteUser, pageNav, url}) => {
    const [currentPage, setCurrentPage] = useState(0);
    const [modal, setModal] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");
    const toggleModal = () => {
        setModal(!modal);
    }
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
    const headers = users.length > 0 ? Object.keys(users[0]) : [];
    console.log(users)
    const submitForm = (e) => {
        e.preventDefault();
        const user = Object.fromEntries(new FormData(e.target));
        fetch(url, {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user)
        }).then(() => {
                console.log("new user added");
                toggleModal();
            }
        )
    }

    return (
        <div>
            <div className="d-flex justify-content-center gap-3">
                <div className="pagination">
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
                </div>
                <div>
                    <button className="btn btn-success" type="button" onClick={toggleModal}>Add new Entry
                    </button>
                    {modal && <CreationForm
                        toggleModal={toggleModal} endpoint={toggleModal} inputs={inputs} initialValues={initialValues}
                        submitForm={submitForm}
                    />}
                </div>
                <h3>Search: </h3>
                <input
                    onChange={(e) => setSearchTerm(e.target.value)}/>
            </div>

            <table className="table table-dark table-striped admin-table">
                <tbody>
                <tr>
                    {headers.map(heading => {
                        return <th key={heading}>{heading}</th>
                    })
                    }
                </tr>
                {users.filter((row) =>
                    !searchTerm.length
                    ||
                    isSearchTermPresent(row, searchTerm)
                )
                    .map((item, index) => (
                        <tr key={index}>
                            {headers.map(header => (
                                <td key={header}>{item[header]}</td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
