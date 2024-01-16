import {List} from "./List";
import {useFetch} from "../hooks/useFetch";
import CreationForm from "./CreationForm";
import {initialValues, inputs} from "./UserFormFields";
import {useState} from "react";
import {Pagination} from "./Pagination";

export const Users = () => {

    const usersUrl = "http://192.168.1.75:8080/api/users";
    const {data: users, isPending, error, handleDelete, pageNav} = useFetch(usersUrl);
    const deleteEntry = (id) => {
        handleDelete(id, usersUrl, users);
    }
    const [modal, setModal] = useState(false);

    const toggleModal = () => {
        setModal(!modal);
    }
    const submitForm = (e) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        fetch(usersUrl, {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(entry)
        }).then(() => {
                console.log("new entry added");
            }
        )
    }
    return (
        <div>
            <Pagination pageNav={pageNav}/>
            <div>
                <button className="btn btn-success" type="button" onClick={toggleModal}>Add new Entry</button>
                {modal && <CreationForm toggleModal={toggleModal} inputs={inputs} initialValues={initialValues} submitForm={submitForm} />}
            </div>
            {users && <List data={users}/>}
            {isPending && <div>Loading Users...</div>}
        </div>
    );
}