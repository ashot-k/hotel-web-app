export const UserList = ({users, handleDelete}) => {

    return (
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
                        <div className="d-flex gap-1 actions">
                            <button className="btn btn-warning">Edit</button>
                            <button className="btn btn-danger" onClick={() => handleDelete(user.id)}>Delete</button>
                        </div>
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    );
}
