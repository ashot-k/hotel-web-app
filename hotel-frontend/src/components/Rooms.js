import {useFetch} from "../hooks/useFetch";

export const Rooms = () => {

    const roomsUrl = "http://192.168.1.75:8080/api/rooms";
    const {data: rooms, isPending, error, handleDelete} = useFetch(roomsUrl);

    const deleteRoom = (id) =>{
        handleDelete(id, roomsUrl, rooms);
    }

    return (
        <div>
            {rooms && <RoomsList rooms={rooms} handleDelete={deleteRoom}/>}
            {isPending && <div>Loading Rooms...</div>}
            <br></br>
        </div>
    );
}