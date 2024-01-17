import {List} from "../List";
import {useFetch} from "../../hooks/useFetch";
import {initialValues} from "./UserFormFields";
import {useState} from "react";
import {CRUDOperations} from "../CRUDOperations";
import UserForm from "./UserForm";

export const Users = () => {

    const usersUrl = "http://192.168.1.75:8080/api/users";
    const {data: users, isPending, error, pageNav, setDataChanged} = useFetch(usersUrl);
    const {create, update, remove} = CRUDOperations(usersUrl);
    const [addModal, setAddModal] = useState(false);
    const [editModal, setEditModal] = useState(false);
    const [editDetails, setEditDetails] = useState(initialValues);

    const toggleAddModal = () => {
        setAddModal(!addModal);
    }
    const toggleEditModal = (initialValues) => {
        setEditModal(!editModal);
        setEditDetails(initialValues);
    }

    return (
        <div>
            <div className="p-2">
                {addModal && <UserForm toggleModal={toggleAddModal} initialValues={initialValues}
                                       submitForm={(event) => {
                                           create(event, setDataChanged);
                                           setAddModal(!addModal);
                                       }}/>}
                {editModal && <UserForm toggleModal={toggleEditModal}
                                        submitForm={(event) => {
                                            update(event, setDataChanged);
                                            setEditModal(!editModal);
                                        }}
                                        initialValues={editDetails}/>}
            </div>
            {users && <List data={users}
                            toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                            setDataChanged={setDataChanged} pageNav={pageNav} remove={remove}/>}
            {isPending && <div>Loading Users...</div>}
        </div>
    );
}