import {useFetch} from "../../hooks/useFetch";
import {CRUDOperations} from "../CRUDOperations";
import {initialValues} from "./RoomFormFields"
import {List} from "../List";
import {useState} from "react";
import RoomForm from "./RoomForm";
import {Pagination} from "../Pagination";
import {roomsURL, roomTypesURL} from "../../URLs";

export const Rooms = () => {

    const [url, setUrl] = useState(roomsURL);
    const {data: roomTypes} = useFetch(roomTypesURL);
    const {data: rooms, isPending, totalPages, totalElements, pageChange, setDataChanged, setPageSize} = useFetch(url);
    const {createRoom, updateRoom, remove, error} = CRUDOperations(roomsURL);
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
            setUrl(roomsURL + "/search?term=" + searchTerm);
            setDataChanged((prev) => prev + 1);
        } else {
            setUrl(roomsURL);
            setDataChanged((prev) => prev + 1);
        }
    }

    return (
        <div className="main-content">
            {addModal && <RoomForm toggleModal={toggleAddModal} initialValues={initialValues} roomTypes={roomTypes}
                                   submitForm={(event) => {
                                       createRoom(event, setDataChanged);
                                       setAddModal(!addModal);
                                   }}/>}
            {editModal && <RoomForm toggleModal={toggleEditModal} initialValues={editDetails} roomTypes={roomTypes}
                                    submitForm={(event) => {
                                        updateRoom(event, setDataChanged);
                                        setEditModal(!editModal);
                                    }}/>}
            {isPending && <div>Loading Rooms...</div>}
            {rooms &&
                <List data={rooms} isSearchTermPresent={isSearchTermPresent} toggleAddModal={toggleAddModal}
                      toggleEditModal={toggleEditModal} setDataChanged={setDataChanged} remove={remove}/>}
            {totalPages > 0 && <Pagination totalPages={totalPages} totalElements={totalElements} pageChange={pageChange}
                                           setPageSize={setPageSize}/>}
            {error && <h5>{error.message}</h5>}
        </div>
    );
}