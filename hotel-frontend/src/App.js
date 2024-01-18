import './css/App.css';
import './css/list.css';
import './css/form-modal.css'
import {BrowserRouter, NavLink, Route, Routes} from 'react-router-dom'
import {Rooms} from "./components/rooms/Rooms";
import {Users} from "./components/users/Users";
import {Reservations} from "./components/reservations/Reservations";
import Home from "./Home";
import Login from "./Login";
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
//import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
function App() {
    return (
        <BrowserRouter>
            <div className="App main-content">
                <header>
                    <nav className="d-flex flex-wrap p-4 gap-2 ">
                        <div>
                            <h1>Hotel</h1>
                            <br/>
                        </div>
                        <div className="d-flex flex-wrap gap-3 w-100 align-items-center justify-content-between">
                            <div className="d-flex gap-2">
                                <NavLink to="/">Home</NavLink>
                                <NavLink to="users">Users</NavLink>
                                <NavLink to="rooms">Rooms</NavLink>
                                <NavLink to="reservations">Reservations</NavLink>
                            </div>
                            <div className="d-flex gap-2">
                                <NavLink className="btn btn-primary" to="login">Login</NavLink>
                                <NavLink className="btn btn-primary" to="register">Register</NavLink>
                            </div>
                        </div>
                    </nav>
                </header>
                <hr/>
                <main>
                    <Routes>
                    <Route path="/" element={<Home/>}/>
                        <Route path="users" element={<Users/>}/>
                        <Route path="rooms" element={<Rooms/>}/>
                        <Route path="reservations" element={<Reservations/>}/>
                        <Route path="login" element={<Login/>}/>
                    </Routes>
                </main>
            </div>

        </BrowserRouter>
    );
}

export default App;
