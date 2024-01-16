import {useFetch} from "../hooks/useFetch";

export const Rooms = () => {

    const roomsUrl = "http://192.168.1.75:8080/api/rooms";
    const {data: rooms, isPending, error, handleDelete, pageNav} = useFetch(roomsUrl);

    const deleteRoom = (id) =>{
        handleDelete(id, roomsUrl, rooms);
    }

    return (
        <div>
            {rooms && <RoomsList rooms={rooms} deleteRoom={deleteRoom} pageNav={pageNav()}/>}
            {isPending && <div>Loading Rooms...</div>}
            <br></br>
        </div>
    );
}