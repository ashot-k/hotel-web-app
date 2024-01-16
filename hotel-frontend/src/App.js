import './css/App.css';
import './css/admin-users-page.css'
import {useEffect} from "react";
import {Users} from "./components/Users/Users";
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
function App() {
    return (
        <div className="App ">
            <Users/>
        </div>
    );
}

export default App;
