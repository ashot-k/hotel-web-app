import './App.css';
import './css/pagebody.css'
import {useEffect} from "react";

function App() {
    useEffect(() => {
        fetch("http://localhost:8080/api/users")
            .then(res => {
                return res.json();
            })
            .then(data => {
                console.log(data);
            })
    }, [])

    return (
        <div className="App">
            <div className="main-content">amongus</div>
        </div>
    );
}

export default App;
