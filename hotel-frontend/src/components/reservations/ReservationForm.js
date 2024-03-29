import {useEffect, useState} from "react";

export const ReservationForm = ({toggleModal, initialValues, submitForm, checkAvailabilityUrl}) => {

    const [username, setUsername] = useState(initialValues.username);
    const [roomId, setRoomId] = useState(initialValues.roomId);
    const [start, setStart] = useState(initialValues.start);
    const [end, setEnd] = useState(initialValues.end);
    const [availableRooms, setAvailableRooms] = useState(null);

    useEffect(() => {
        checkAvailability(start, end)
    }, [start, end]);

    const checkAvailability = async (start, end) => {
        if (start && end) {
            const response = await fetch(checkAvailabilityUrl + "?start=" + start + "&end=" + end);
            const rooms = (await response.json());
            setAvailableRooms(rooms);
        }
    }
    return (
        <form className="form form-modal" action="/" onSubmit={submitForm}>
            <div className="overlay d-flex justify-content-center align-items-center">
                <div className="form-modal-body d-flex flex-column  gap-2 align-items-center">
                    <h1>Reservation Info</h1>
                    <input name="id" value={initialValues.id} hidden onChange={(e) => {
                    }}/>
                    <div>
                        <label>Username*</label><br/>
                        <input name="username" value={username} onChange={(e) => setUsername(e.target.value)}/>
                    </div>
                    <div>
                        <label>Starting Date*</label><br/>
                        <input name="start" type={"date"} value={start} onChange={(e) => {
                            setStart(e.target.value);
                        }}/>
                    </div>
                    <div>
                        <label>End*</label><br/>
                        <input name="end" type={"date"} value={end} onChange={(e) => {
                            setEnd(e.target.value);
                        }}/>
                    </div>
                    <div>
                        <label>Available Rooms*</label><br/>
                        <select name="roomId">
                            {availableRooms &&
                                availableRooms.map((room) => {
                                    return <option value={room.id}>{room.name + " " +  room.roomType + " " +  room.price + "€"}</option>
                                })
                            }
                        </select>
                    </div>
                    <div className="d-flex gap-2 justify-content-center w-100">
                        <button className="btn btn-success" type="submit">Submit</button>
                        <button className="btn btn-danger" type="button" onClick={toggleModal}>Cancel</button>
                    </div>

                </div>
            </div>
        </form>
    );
}

export default ReservationForm;