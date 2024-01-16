import {UserList} from "./UserList";
import {useFetch} from "../hooks/useFetch";

export const Users = () => {

    const usersUrl = "http://192.168.1.75:8080/api/users";
    const {data: users, isPending, error, handleDelete, pageNav} = useFetch(usersUrl);

    const deleteUser = (id) => {
        handleDelete(id, usersUrl, users);
    }

    return (
        <div>
            {users && <UserList users={users} deleteUser={deleteUser} pageNav={pageNav} url={usersUrl}/>}
            {isPending && <div>Loading Users...</div>}
        </div>
    );
}