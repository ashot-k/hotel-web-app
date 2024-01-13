import './App.css';
import './css/admin-users-page.css'
import {useEffect} from "react";
import {Users} from "./components/Users";
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
function App() {
    return (
        <div className="App main-content">
            <Users/>
        </div>
    );
}

export default App;
