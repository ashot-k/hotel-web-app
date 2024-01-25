import {useFetch} from "../../hooks/useFetch";
import {CRUDOperations} from "../CRUDOperations";
import {initialValues} from "./ReservationFormFields";
import {List} from "../List";
import {useState} from "react";
import ReservationForm from "./ReservationForm";
import {Pagination} from "../Pagination";
import {reservationsURL, availableRoomsURL} from "../../URLs";

export const Reservations = () => {

    const [url, setUrl] = useState(reservationsURL);
    const {data: reservations,error, isPending, totalPages, totalElements, pageChange, setDataChanged, setPageSize} = useFetch(url);
    const {create, update, remove} = CRUDOperations(reservationsURL);

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
            setUrl(reservationsURL + "/search?term=" + searchTerm);
            setDataChanged((prev) => prev + 1);
        } else {
            setUrl(reservationsURL);
            setDataChanged((prev) => prev + 1);
        }
    }

    return (
        <div className="main-content">

                 {addModal && <ReservationForm toggleModal={toggleAddModal} checkAvailabilityUrl={availableRoomsURL} initialValues={initialValues}
                                              submitForm={(event) => {
                                                  create(event, setDataChanged);
                                                  setAddModal(!addModal);
                                              }}/>}
                {editModal && <ReservationForm toggleModal={toggleEditModal} checkAvailabilityUrl={availableRoomsURL} initialValues={editDetails}
                                               submitForm={(event) => {
                                                   update(event, setDataChanged);
                                                   setEditModal(!editModal);
                                               }}/>}
            {isPending && <div>Loading Reservations...</div>}
            {reservations && <List data={reservations} isSearchTermPresent={isSearchTermPresent}
                                   toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                                   setDataChanged={setDataChanged} remove={remove}/>}
            {totalPages > 0 &&
                <Pagination totalPages={totalPages} totalElements={totalElements} pageChange={pageChange} setPageSize={setPageSize}/>}
            {error && <h5>{error.message}</h5>}
        </div>
    );
}