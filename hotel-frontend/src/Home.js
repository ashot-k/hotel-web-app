import React, {useEffect, useState} from 'react';
import {useFetch} from "./hooks/useFetch";
import {initialValues} from "./components/reservations/ReservationFormFields";

const Home = () => {
    const roomsUrl = "http://192.168.1.75:8080/api/rooms";
    const reservationsUrl = "http://192.168.1.75:8080/api/reservations";

    const availableRoomsUrl = "/available"
   // const {data: rooms, isPending, error,  totalPages, totalElements, pageChange, setDataChanged} = useFetch(roomsUrl);
    const [start, setStart] = useState(initialValues.start);
    const [end, setEnd] = useState(initialValues.end);
    const [availableRooms, setAvailableRooms] = useState(null);

    useEffect(() => {
        checkAvailability(start, end);
    }, [start, end]);

    const checkAvailability = async (start, end) => {
        if (start && end) {
            const response = await fetch(reservationsUrl + availableRoomsUrl + "?start=" + start + "&end=" + end);
            const rooms = (await response.json());
            setAvailableRooms(rooms);
        }
        else
            setAvailableRooms(null);
    }

    return (
        <div className={"d-flex flex-column gap-3 justify-content-center align-items-center"}>
            <div className={"d-flex justify-content-center gap-3"}>
                <div>
                    <label>Starting Date</label><br/>
                    <input name="start" type={"date"} value={start} onChange={(e) => {
                        setStart(e.target.value);
                    }}/>
                </div>
                <div>
                    <label>End</label><br/>
                    <input name="end" type={"date"} value={end} onChange={(e) => {
                        setEnd(e.target.value);
                    }}/>
                </div>
            </div>
            <br/>
            {availableRooms && <h1>Available Rooms</h1>}
            <hr/>
            <div className="w-100 d-flex flex-wrap gap-3 p-3 justify-content-center">
                {availableRooms && availableRooms.map((room) => {
                    return <div className="card-container">
                        <div className="card-body d-flex flex-column gap-4 justify-content-between align-items-center">
                            <h5 className="card-title">{room.name}</h5>
                            <img src={"http://192.168.1.75:8080/api/rooms/"+ room.id +"/image"} className="card-image" alt="..."/>
                            <div className="card-text">
                                <h5 className="">{room.roomType}</h5>
                                <button type={"button"} className="btn btn-primary">Book {room.price}€</button>
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
