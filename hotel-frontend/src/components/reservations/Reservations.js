import {useFetch} from "../../hooks/useFetch";
import {CRUDOperations} from "../CRUDOperations";
import {initialValues, inputs} from "../reservations/ReservationFormFields";
import CreationForm from "../CreationForm";
import {List} from "../List";
import {useState} from "react";

export const Reservations = () => {

    const reservationsUrl = "http://192.168.1.75:8080/api/reservations";
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
                {addModal && <CreationForm toggleModal={toggleAddModal} inputs={inputs} initialValues={initialValues}
                                           submitForm={(event)=> {create(event, setDataChanged); setAddModal(!addModal);}} />}
                {editModal && <CreationForm toggleModal={toggleEditModal} inputs={inputs} initialValues={editDetails}
                                            submitForm={(event) => {update(event, setDataChanged); setEditModal(!editModal);}}/>}
            </div>
            {reservations && <List data={reservations}  inputs={inputs}
                            toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                            setDataChanged={setDataChanged} pageNav={pageNav} remove={remove}/>}
            {isPending && <div>Loading Reservations...</div>}
        </div>
    );
}