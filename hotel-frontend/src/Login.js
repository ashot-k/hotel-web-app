import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {loginURL} from "./URLs";
const Login = ({setToken}) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const [errorMsg, setErrorMsg] = useState("");

    function loginRequest(e) {
        e.preventDefault();
        const credentials = Object.fromEntries(new FormData(e.target));
        console.log("credentials " + credentials.username);
        fetch(loginURL, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(credentials)
        }).then((res) =>
            res.json()).then((data) => {
                console.log(data);
            setToken(data.token);
            document.cookie ="token=" + data.token;
        }).then(() => {
            navigate("/")
        }).catch(error => {
            setErrorMsg("Wrong Credentials");
            console.error(error);
        });
    }

    return (
            <form onSubmit={(e) => loginRequest(e)}>
                <div className="d-flex justify-content-center">
                    <div className=" p-3">
                        <div className="mb-3 form-input">
                            <label className="form-label">Username</label>
                            <input className="form-control" value={username} name="username" required={true}
                                   onChange={(e) => {
                                       setUsername(e.target.value);
                                       setErrorMsg("");
                                   }}/>
                        </div>
                        <div className="mb-3 form-input">
                            <label className="form-label">Password</label>
                            <input className="form-control" type={"password"} value={password} name="password"
                                   required={true}
                                   onChange={(e) => {
                                       setPassword(e.target.value);
                                       setErrorMsg("");
                                   }}/>
                        </div>
                        {errorMsg && <h5 style={{color: "red"}}>Bad Credentials</h5>}
                        <div>
                            <button id="submit" className="btn btn-primary" type="submit">Login
                            </button>
                        </div>
                    </div>
                </div>
            </form>
    );
};

export default Login;