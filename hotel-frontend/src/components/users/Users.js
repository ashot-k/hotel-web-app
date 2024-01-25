import {List} from "../List";
import {useFetch} from "../../hooks/useFetch";
import {initialValues} from "./UserFormFields";
import {useState} from "react";
import {CRUDOperations} from "../CRUDOperations";
import UserForm from "./UserForm";
import {Pagination} from "../Pagination";
import {usersURL} from "../../URLs";

export const Users = () => {


    const [url, setUrl] = useState(usersURL);
    const {data: users, isPending, totalPages, totalElements, pageChange, setDataChanged, setPageSize} = useFetch(url);
    const {create, update, remove, error} = CRUDOperations(usersURL);
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

    const isSearchTermPresent = async (searchTerm) => {
        if (searchTerm) {
            setUrl(usersURL + "/search?term=" + searchTerm);
            setDataChanged((prev) => prev + 1);
        } else {
            setUrl(usersURL);
            setDataChanged((prev) => prev + 1);
        }
    }
    return (
        <div>
            <div className="p-2">

                {addModal && <UserForm toggleModal={toggleAddModal} initialValues={initialValues}
                                       error={error}
                                       submitForm={(event) => {
                                           create(event, setDataChanged);
                                           setAddModal(!addModal);
                                       }}
                />}
                {editModal && <UserForm toggleModal={toggleEditModal} initialValues={editDetails}
                                        submitForm={(event) => {
                                            update(event, setDataChanged);
                                            setEditModal(!editModal);
                                        }}
                />}
            </div>
            {isPending && <div>Loading Users...</div>}
            {users && <List data={users} isSearchTermPresent={isSearchTermPresent}
                            toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                            setDataChanged={setDataChanged} remove={remove}/>}
            {totalPages > 0 && <Pagination totalPages={totalPages} totalElements={totalElements} pageChange={pageChange}
                                           setPageSize={setPageSize}/>}
        </div>
    );
}