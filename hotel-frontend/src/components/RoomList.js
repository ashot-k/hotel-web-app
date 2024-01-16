import {useState} from "react";
import CreationForm from "./CreationForm"
//import {initialValues, inputs} from "./UserFormFields";
export const RoomList = ({rooms, deleteRoom, pageNav, url}) => {
    const [currentPage, setCurrentPage] = useState(0);
    const [modal, setModal] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");
    const toggleModal = () => {
        setModal(!modal);
    }

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
                    <button className="btn btn-success" type="button" onClick={toggleModal}>Add new Room
                    </button>
                   {/* {modal && <CreationForm
                        toggleModal={toggleModal} endpoint={toggleModal} inputs={inputs} initialValues={initialValues}
                        submitForm={submitForm}
                     />}*/}
                </div>
                <h3>Search: </h3>
                <input
                    onChange={(e) => setSearchTerm(e.target.value)}/>
            </div>

            <table className="table table-dark table-striped admin-table">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>Room Type</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Size</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
                {rooms.filter((row) =>
                    !searchTerm.length
                    ||
                    row.roomType.toString().toLowerCase().includes(searchTerm.toString().toLowerCase())
                    ||
                    row.name.toString().toLowerCase().includes(searchTerm.toString().toLowerCase())
                    ||
                    row.description.toString().toLowerCase().includes(searchTerm.toString().toLowerCase())

                )
                    .map((room) => (
                        <tr className="user-preview p-1" key={room.id}>
                            <td>{room.id}</td>
                            <td>{room.roomType}</td>
                            <td>{room.name}</td>
                            <td>{room.description}</td>
                            <td>{room.size}</td>
                            <td>{room.price}</td>
                            <td>
                                <div className="actions">
                                    <button className="btn btn-warning">Edit</button>
                                    <button className="btn btn-danger" onClick={() => deleteRoom(room.id)}>Delete</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}