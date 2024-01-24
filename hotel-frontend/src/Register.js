import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

const Register = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [token, setToken] = useState("");
    const navigate = useNavigate();
    const [errorMsg, setErrorMsg] = useState("");
    const [email, setEmail] = useState("");
    const [country, setCountry] = useState("");
    const [postalCode, setPostalCode] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [street, setStreet] = useState("");
    const [street2, setStreet2] = useState("");

    function registerRequest(e) {
        e.preventDefault();
        const credentials = Object.fromEntries(new FormData(e.target));
        fetch("http://192.168.1.64:8080/api/auth/register", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(credentials)
        }).then((res) => {
                if (res.ok)
                    res.json() .then((data) => {
                        setToken(data.token);
                        document.cookie = "token=" + data.token;
                        navigate("/")
                    })
                else
                    res.text().then((message) => setErrorMsg(message));
            }).then(() => {

        }).catch(error => {
            console.error(error);
        });
    }

    return (
        <form onSubmit={(e) => registerRequest(e)}>
            <div className="d-flex justify-content-center">
                <div className="w-25 p-3 d-flex flex-column gap-3">
                    <div>
                        <label className="form-label">Username*</label>
                        <input className="form-control" value={username} name="username" required={true}
                               onChange={(e) => {
                                   setUsername(e.target.value);
                                   setErrorMsg("");
                               }}/>
                    </div>
                    <div>
                        <label className="form-label">Password*</label>
                        <input className="form-control" type={"password"} value={password} name="password"
                               required={true}
                               onChange={(e) => {
                                   setPassword(e.target.value);
                                   setErrorMsg("");
                               }}/>
                    </div>
                    <div>
                        <label>Email*</label><br/>
                        <input name="email" className="form-control" required={true} value={email}
                               onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div>
                        <label>Country*</label><br/>
                        <input name="country" className="form-control" required={true} value={country}
                               onChange={(e) => setCountry(e.target.value)}/>
                    </div>
                    <div>
                        <label>Postal Code*</label><br/>
                        <input name="postalCode" className="form-control" required={true} value={postalCode}
                               onChange={(e) => setPostalCode(e.target.value)}/>
                    </div>
                    <div>
                        <label>Phone Number*</label><br/>
                        <input name="phoneNumber" className="form-control" required={true} value={phoneNumber}
                               onChange={(e) => setPhoneNumber(e.target.value)}/>
                    </div>
                    <div>
                        <label>Street*</label><br/>
                        <input name="street" className="form-control" required={true} value={street}
                               onChange={(e) => setStreet(e.target.value)}/>
                    </div>
                    <div>
                        <label>Street 2 (Optional)</label><br/>
                        <input name="street2" className="form-control" value={street2}
                               onChange={(e) => setStreet2(e.target.value)}/>
                    </div>
                    {errorMsg && <h5 style={{color: "red"}}>{errorMsg}</h5>}
                    <div>
                        <button id="submit" className="btn btn-primary" type="submit">Register
                        </button>
                    </div>
                </div>
            </div>
        </form>
    );
};

export default Register;
