import {useFetch} from "../../hooks/useFetch";
import {CRUDOperations} from "../CRUDOperations";
import {initialValues} from "./RoomFormFields"
import {List} from "../List";
import {useState} from "react";
import RoomForm from "./RoomForm";
import {Pagination} from "../Pagination";

export const Rooms = () => {

    const rootUrl = "http://192.168.1.64:8080/api/rooms";
    const [url, setUrl] = useState(rootUrl);
    const roomTypesUrl = rootUrl + "/room-types";
    const {data: roomTypes} = useFetch(roomTypesUrl);
    const {data: rooms, isPending, totalPages, totalElements, pageChange, setDataChanged, setPageSize} = useFetch(url);
    const {createRoom, updateRoom, remove, error} = CRUDOperations(rootUrl);
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