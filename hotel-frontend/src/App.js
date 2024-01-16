import './css/App.css';
import './css/list-page.css';
import {Rooms} from "./components/rooms/Rooms";
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import {Users} from "./components/users/Users";
import {Reservations} from "./components/reservations/Reservations";
function App() {
    return (
        <div className="App ">
            <Users/>
            <Rooms/>
            <Reservations/>
        </div>
    );
}

export default App;
