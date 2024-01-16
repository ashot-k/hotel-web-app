import {List} from "../List";
import {useFetch} from "../../hooks/useFetch";
import CreationForm from "../CreationForm";
import {initialValues, inputs} from "./UserFormFields";
import {useEffect, useState} from "react";
import {CRUDOperations} from "../CRUDOperations";

export const Users = () => {

    const usersUrl = "http://192.168.1.75:8080/api/users";
    const {data: users, isPending, error, pageNav} = useFetch(usersUrl);
    const {create, update, remove} = CRUDOperations();
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
    const deleteEntry = (id) => {
        remove(usersUrl, id);
    }
    return (
        <div className="main-content">
            <div>
                <button className="btn btn-success" type="button" onClick={toggleAddModal}>Add new Entry</button>
                {addModal && <CreationForm toggleModal={toggleAddModal} inputs={inputs} initialValues={initialValues} submitForm={(event)=> create(event, usersUrl)} />}
                {editModal && <CreationForm toggleModal={toggleEditModal} inputs={inputs} initialValues={editDetails} submitForm={(event) => update(event, usersUrl)}/>}
            </div>
            {users && <List data={users} deleteEntry={deleteEntry} inputs={inputs} toggleEditModal={toggleEditModal} pageNav={pageNav}/>}
            {isPending && <div>Loading Users...</div>}
        </div>
    );
}