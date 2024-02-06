import React, {useEffect, useState} from 'react';
import {useFetch} from "./hooks/useFetch";
import {initialValues} from "./components/reservations/ReservationFormFields";
import axios from 'axios';
import {getCookie, parseJwt} from "./Cookies";
import {roomsURL, reservationsURL, availableRoomsURL, roomImagesURL} from "./URLs";

const Home = () => {
    axios.defaults.headers.common['Authorization'] = ""
    const [start, setStart] = useState(initialValues.start);
    const [end, setEnd] = useState(initialValues.end);
    const [availableRooms, setAvailableRooms] = useState(null);
    const [dataChanged, setDataChanged] = useState(0);

    useEffect(() => {
        checkAvailability(start, end);
    }, [start, end]);

    const checkAvailability = (start, end) => {
        if (start && end) {
            const rooms = axios.get(availableRoomsURL, {
                params: {
                    start: start,
                    end: end
                },
            })
                .then(response => setAvailableRooms(response.data));
        } else
            setAvailableRooms(null);
    }
    const reserveRoom = (roomId) => {
        const username = parseJwt(getCookie("token")).sub;
        console.log(username);
        axios.post(reservationsURL, {roomId, username, start, end})
            .then(() => checkAvailability(start, end))
            .catch(error => console.log(error));
    }

    return (
        <div className={"d-flex flex-column gap-3 align-items-center"} style={{minHeight: "550px"}}>
            <div className={"d-flex justify-content-center gap-3"}>
                <div>
                    <label>Starting Date</label><br/>
                    <input name="start" type={"date"} value={start} min={new Date().toISOString().split('T')[0]}
                           onChange={(e) => {
                               setStart(e.target.value);
                               setEnd("");
                           }}/>
                </div>
                <div>
                    <label>End</label><br/>
                    <input name="end" type={"date"} min={start} value={end} onChange={(e) => {
                        setEnd(e.target.value);
                    }}/>
                </div>
            </div>
            <br/>
            {availableRooms && availableRooms.length > 0 && <h1>Available Rooms</h1> || start && end &&
                <h1 style={{color: "red"}}>No rooms available in this range</h1>}
            <hr/>
            <div className="w-100 d-flex flex-wrap gap-3 p-3 justify-content-center">
                {availableRooms && availableRooms.map((room) => {
                    return <div className="card-container" key={room.id}>
                        <div className="card-body d-flex flex-column gap-4 justify-content-between align-items-center">
                            <h5 className="card-title">{room.name}</h5>
                            <img src={roomImagesURL + room.name} className="card-image" alt="..."/>
                            <div className="card-text">
                                <h5 className="">{room.roomType}</h5>
                                <button type={"button"} className="btn btn-primary"
                                        onClick={() => reserveRoom(room.id)}>Book {room.price}€
                                </button>
                            </div>
                        </div>
                    </div>
                })
                }
            </div>
            <hr/>
        </div>
    );
};

export default Home;
