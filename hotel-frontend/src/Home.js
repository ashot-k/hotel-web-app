import React, {useEffect, useState} from 'react';
import {useFetch} from "./hooks/useFetch";
import {initialValues} from "./components/reservations/ReservationFormFields";
import axios from 'axios';
import {getCookie, parseJwt} from "./Cookies";
import {roomsURL, reservationsURL, availableRoomsURL, roomImagesURL, roomTypesURL} from "./URLs";

const Home = () => {
    axios.defaults.headers.common['Authorization'] = "";
    const [roomTypes, setRoomTypes] = useState();
    console.log(roomTypes)
    const [selectedRoomType, setSelectedRoomType] = useState("all");
    const [start, setStart] = useState(initialValues.start);
    const [end, setEnd] = useState(initialValues.end);
    const [availableRooms, setAvailableRooms] = useState(null);
    const [dataChanged, setDataChanged] = useState(0);

    useEffect(() => {
        checkAvailability(start, end);
    }, [start, end]);
    useEffect(() => {
        axios.get(roomTypesURL).then((response)=> setRoomTypes(response.data));
    }, []);

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
        axios.post(reservationsURL, {
            roomId,
            username,
            start,
            end
        }, {headers: {"Authorization": 'Bearer ' + getCookie("token")}})
            .then(() => checkAvailability(start, end))
            .catch(error => console.log(error));
    }

    const filterRooms = (rooms) => {
        return rooms.filter((room) => {
            if (!(selectedRoomType === "all")) {
                if (room.roomType === selectedRoomType) {
                    return room;
                }
            } else
                return room;
        })
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
                <div>
                    <label>Room Type</label><br/>
                    <select name="roomType" onChange={(e) => setSelectedRoomType(e.target.value)}>
                        <option key={"all"} value={"all"}>ALL</option>
                        {roomTypes && roomTypes.map((roomType) => {
                                return <option key={roomType} value={roomType}>{roomType}</option>
                            }
                        )}
                    </select>
                </div>
            </div>
            <br/>
            {availableRooms && filterRooms(availableRooms).length > 0 && <h1>Available Rooms</h1>
                || start && end && <h1 style={{color: "red"}}>No rooms available in this range</h1>}
            {availableRooms &&
                <div className="w-100 d-flex flex-wrap gap-3 p-3 justify-content-center">
                    {filterRooms(availableRooms).map((room) => {
                        return <div className="card-container" key={room.id}>
                            <div
                                className="card-body d-flex flex-column gap-4 justify-content-between align-items-center">
                                <h5 className="card-title">{room.name}</h5>
                                <img src={roomImagesURL + room.name} className="card-image"
                                     alt="Image not Available"/>
                                <div className="card-text">
                                    <h5 className="">{room.roomType}</h5>
                                    <button type={"button"} className="btn btn-primary"
                                            onClick={() => reserveRoom(room.id)}>Book {room.price}€
                                    </button>
                                </div>
                            </div>
                        </div>
                    })}
                </div>
            }
        </div>
    );
};

export default Home;
