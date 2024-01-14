import {useState} from "react";
import CreationForm from "./CreationForm"
import {inputs, initialValues} from "./UserFormFields";
export const UserList = ({users, deleteUser, pageNav}) => {
    const [currentPage, setCurrentPage] = useState(0);
    const [modal, setModal] = useState(false);

    console.log(inputs);
    const toggleModal = () => {
         setModal(!modal);
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
                    <button className="btn btn-success" type="button" onClick={toggleModal}>Add new User
                    </button>
                    {modal && <CreationForm toggleModal={toggleModal} inputs={inputs} initialValues={initialValues}/>}
                </div>
            </div>
            <table className="table table-dark table-striped admin-table">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Country</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Actions</th>
                </tr>
                {users.map((user) => (
                    <tr className="user-preview p-1" key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.username}</td>
                        <td>{user.address["country"]}</td>
                        <td>{user.address["email"]}</td>
                        <td>{user.address["phoneNumber"]}</td>
                        <td>
                            <div className="actions">
                                <button className="btn btn-warning">Edit</button>
                                <button className="btn btn-danger" onClick={() => deleteUser(user.id)}>Delete</button>
                            </div>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

        </div>
    );
}
