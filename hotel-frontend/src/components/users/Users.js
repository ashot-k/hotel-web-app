import {List} from "../List";
import {useFetch} from "../../hooks/useFetch";
import {initialValues} from "./UserFormFields";
import {useState} from "react";
import {CRUDOperations} from "../CRUDOperations";
import UserForm from "./UserForm";
import {Pagination} from "../Pagination";

export const Users = () => {

    const rootUrl = "http://192.168.1.64:8080/api/users";
    const [url, setUrl] = useState(rootUrl);
    const {data: users, isPending, totalPages, totalElements, pageChange, setDataChanged, setPageSize} = useFetch(url);
    const {create, update, remove} = CRUDOperations(rootUrl);
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
            setUrl(rootUrl + "/search?term=" + searchTerm);
            setDataChanged((prev) => prev + 1);
        } else {
            setUrl(rootUrl);
            setDataChanged((prev) => prev + 1);
        }
    }
    return (
        <div>
            <div className="p-2">
                {totalPages && <Pagination totalPages={totalPages} totalElements={totalElements} pageChange={pageChange} setPageSize={setPageSize}/>}
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
            {users && <List data={users} isSearchTermPresent={isSearchTermPresent}
                               toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                               setDataChanged={setDataChanged} remove={remove}/>}
            {isPending && <div>Loading Users...</div>}
        </div>
    );
}