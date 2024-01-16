import {List} from "../List";
import {useFetch} from "../../hooks/useFetch";
import CreationForm from "../CreationForm";
import {initialValues, inputs} from "./UserFormFields";
import {useState} from "react";
import {CRUDOperations} from "../CRUDOperations";

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
        <div >
            <div className="p-2">
                {addModal && <CreationForm toggleModal={toggleAddModal} inputs={inputs} initialValues={initialValues}
                                           submitForm={(event)=> {create(event, setDataChanged); setAddModal(!addModal);}} />}

                {editModal && <CreationForm toggleModal={toggleEditModal} inputs={inputs} initialValues={editDetails}
                                            submitForm={(event) => {update(event, setDataChanged); setEditModal(!editModal);}}/>}
            </div>
            {users && <List data={users}  inputs={inputs}
                            toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                            setDataChanged={setDataChanged} pageNav={pageNav} remove={remove}/>}
            {isPending && <div>Loading Users...</div>}
        </div>
    );
}