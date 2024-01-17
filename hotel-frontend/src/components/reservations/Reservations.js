import {useFetch} from "../../hooks/useFetch";
import {CRUDOperations} from "../CRUDOperations";
import {initialValues} from "./ReservationFormFields";
import {List} from "../List";
import {useState} from "react";
import ReservationForm from "./ReservationForm";

export const Reservations = () => {

    const reservationsUrl = "http://192.168.1.75:8080/api/reservations";
    const availableRoomsUrl = reservationsUrl + "/available";
    const {data: reservations, isPending, error, pageNav, setDataChanged} = useFetch(reservationsUrl);
    const {create, update, remove} = CRUDOperations(reservationsUrl);
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
            <div className="p-2">
                {addModal && <ReservationForm toggleModal={toggleAddModal} checkAvailabilityUrl={availableRoomsUrl}
                                              initialValues={initialValues}
                                              submitForm={(event)=> {create(event, setDataChanged); setAddModal(!addModal);}}/>}
                {editModal && <ReservationForm toggleModal={toggleEditModal}
                                               initialValues={editDetails} checkAvailabilityUrl={availableRoomsUrl}
                                               submitForm={(event) => {update(event, setDataChanged); setEditModal(!editModal);}}/>}
            </div>
            {reservations && <List data={reservations}
                                   toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                                   setDataChanged={setDataChanged} pageNav={pageNav} remove={remove}/>}
            {isPending && <div>Loading Reservations...</div>}
        </div>
    );
}