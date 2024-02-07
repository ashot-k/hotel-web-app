import './css/App.css';
import './css/list.css';
import './css/form-modal.css'
import {BrowserRouter, NavLink, Route, Routes} from 'react-router-dom'
import {Rooms} from "./components/rooms/Rooms";
import {Users} from "./components/users/Users";
import {Reservations} from "./components/reservations/Reservations";
import Home from "./Home";
import Login from "./Login";
import {deleteCookie, getCookie, setCookie, parseJwt} from "./Cookies";
// noinspection ES6UnusedImports
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import {useEffect, useState} from "react";
import Register from "./Register";
import Footer from "./components/Footer";
//import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
function App() {
    const [token, setToken] = useState(getCookie("token"));

    return (
        <BrowserRouter>
            <div className="App main-content" data-bs-theme="dark">
                <header>
                    <nav className="d-flex flex-wrap p-4 gap-2 justify-content-center">
                        <div className={"d-flex justify-content-between w-100"}>
                            <h1 className={"display-1"}>Hotel</h1>

                        </div>
                        <div className="d-flex flex-wrap gap-3 w-100 align-items-center justify-content-between fs-4">
                            <div className="d-flex gap-2">
                                {(token.length > 0 &&
                                    <div className="d-flex gap-3">
                                        <NavLink to="/">Home</NavLink>
                                        <NavLink to="users">Users</NavLink>
                                        <NavLink to="rooms">Rooms</NavLink>
                                        <NavLink to="reservations">Reservations</NavLink>
                                    </div>)
                                    || <div className="d-flex gap-5">
                                        <NavLink to="/">Home</NavLink>
                                    </div>
                                }
                            </div>
                            {!token.length > 0 &&
                                <div className="d-flex gap-2">
                                    <NavLink className="btn btn-primary" to="login">Login</NavLink>
                                    <NavLink className="btn btn-primary" to="register">Register</NavLink>
                                </div>
                                ||<div>
                                    <h5>Hello {parseJwt(token).sub}</h5>
                                    <button className="btn btn-danger" onClick={(e) => {deleteCookie("token"); setToken("")}}>Logout</button>
                                </div>}
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
                        <Route path="login" element={<Login setToken={setToken}/>}/>
                        <Route path="register" element={<Register setToken={setToken}/>}/>
                    </Routes>
                </main>
                <hr/>
                <Footer/>
            </div>
        </BrowserRouter>
    );
}

export default App;
