import {useFetch} from "../../hooks/useFetch";
import {CRUDOperations} from "../CRUDOperations";
import {initialValues} from "./ReservationFormFields";
import {List} from "../List";
import {useState} from "react";
import ReservationForm from "./ReservationForm";
import {Pagination} from "../Pagination";

export const Reservations = () => {

    const rootUrl = "http://192.168.1.64:8080/api/reservations";
    const [url, setUrl] = useState(rootUrl);
    const availableRoomsUrl = rootUrl + "/available";
    const {data: reservations, isPending, totalPages, totalElements, pageChange, setDataChanged, setPageSize} = useFetch(url);
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
        <div className="main-content">
            <div className="p-2">
                {totalPages &&
                    <Pagination totalPages={totalPages} totalElements={totalElements} pageChange={pageChange} setPageSize={setPageSize}/>}
                {addModal && <ReservationForm toggleModal={toggleAddModal} checkAvailabilityUrl={availableRoomsUrl}
                                              initialValues={initialValues}
                                              submitForm={(event) => {
                                                  create(event, setDataChanged);
                                                  setAddModal(!addModal);
                                              }}/>}
                {editModal && <ReservationForm toggleModal={toggleEditModal}
                                               initialValues={editDetails} checkAvailabilityUrl={availableRoomsUrl}
                                               submitForm={(event) => {
                                                   update(event, setDataChanged);
                                                   setEditModal(!editModal);
                                               }}/>}
            </div>
            {reservations && <List data={reservations} isSearchTermPresent={isSearchTermPresent}
                                   toggleAddModal={toggleAddModal} toggleEditModal={toggleEditModal}
                                   setDataChanged={setDataChanged} remove={remove}/>}
            {isPending && <div>Loading Reservations...</div>}
        </div>
    );
}