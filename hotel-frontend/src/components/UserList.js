import {useState} from "react";
import CreationForm from "./CreationForm"
import {inputs, initialValues} from "./UserFormFields";
export const UserList = ({users, deleteUser, pageNav}) => {
    const [currentPage, setCurrentPage] = useState(0);
    const [modal, setModal] = useState(false);


    const toggleModal = () => {
        setModal(!modal);
    }


    const submitForm = (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        console.log(formData)
        const formDataArray = Object.fromEntries(formData);
      /*  const payload = [];
        payload.push(formDataArray.)*/
        console.log(formDataArray);
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
                    {modal && <CreationForm
                        toggleModal={toggleModal} endpoint={toggleModal} inputs={inputs} initialValues={initialValues}
                        submitForm={submitForm}
                    />}
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
