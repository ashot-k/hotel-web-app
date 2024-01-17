import './css/App.css';
import './css/list-page.css';
import {BrowserRouter, Routes,Route, Link, NavLink} from 'react-router-dom'
import {Rooms} from "./components/rooms/Rooms";
import {Users} from "./components/users/Users";
import {Reservations} from "./components/reservations/Reservations";
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import Home from "./Home";
import Login from "./Login";
//import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
function App() {
    return (
        <BrowserRouter>
            <div className="App main-content">
                <header>
                    <nav className="d-flex gap-3 align-items-center">
                        <h1 className="p-1">Hotel</h1>
                        <NavLink to="/">Home</NavLink>
                        <NavLink  to="users">Users</NavLink>
                        <NavLink  to="rooms">Rooms</NavLink>
                        <NavLink  to="reservations">Reservations</NavLink>
                        <NavLink to="login">Login</NavLink>
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
