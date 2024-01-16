import './css/App.css';
import './css/list-page.css';
import {Rooms} from "./components/rooms/Rooms";
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import {Users} from "./components/users/Users";
import {Reservations} from "./components/reservations/Reservations";
function App() {
    return (
        <div className="App main-content">
            <hr/>
            <h1>Users </h1>
            <hr/>
            <Users/>
            <hr/>
            <h1>Rooms</h1>
            <hr/>
            <Rooms/>
            <hr/>
            <h1>Reservations</h1>
            <hr/>
            <Reservations/>
            <hr/>
        </div>
    );
}

export default App;
