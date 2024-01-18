import {useFetch} from "../../hooks/useFetch";
import {CRUDOperations} from "../CRUDOperations";
import {initialValues} from "./RoomFormFields"
import {List} from "../List";
import {useState} from "react";
import RoomForm from "./RoomForm";

export const Rooms = () => {

    const roomsUrl = "http://192.168.1.75:8080/api/rooms";
    const roomTypesUrl = roomsUrl + "/room-types";
    const {data: roomTypes} = useFetch(roomTypesUrl);
    const {data: rooms, isPending, error, pageNav, setDataChanged} = useFetch(roomsUrl);
    const {create, update, remove} = CRUDOperations(roomsUrl);
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
        <div className="main-content">
            <div>
                {addModal && <RoomForm toggleModal={toggleAddModal}
                                       initialValues={initialValues}
                                       roomTypes = {roomTypes}
                                       submitForm={(event) => {
                                           create(event, setDataChanged);
                                           setAddModal(!addModal);
                                       }}/>}
                {editModal && <RoomForm toggleModal={toggleEditModal}
                                        initialValues={editDetails}
                                        roomTypes = {roomTypes}
                                        submitForm={(event) => {
                                            update(event, setDataChanged);
                                            setEditModal(!editModal);
                                        }}/>}
            </div>
            {rooms && <List data={rooms}
                            toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                            setDataChanged={setDataChanged} pageNav={pageNav} remove={remove}/>}
            {isPending && <div>Loading Rooms...</div>}
        </div>
    );
}