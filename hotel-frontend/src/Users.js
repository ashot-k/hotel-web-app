import {useEffect, useState} from "react";
import {UserList} from "./UserList";

export const Users = () => {
    const [users, setUsers] = useState(null);
    const usersApiEndpoint = "http://192.168.1.75:8080/api/users";
    useEffect(() => {
        fetch(usersApiEndpoint)
            .then(res => {
                return res.json();
            })
            .then(data => {
                setUsers(data);
            })
    }, [])
    const handleDelete = (id) => {
        fetch(usersApiEndpoint + "/" + id, {method: 'DELETE'})
            .then(() => {
                setUsers(users.filter(user => user.id !== id));
            });
    }
    return (
        <div>
            {users && <UserList users={users} handleDelete={handleDelete}/>}
        </div>

    );
}
