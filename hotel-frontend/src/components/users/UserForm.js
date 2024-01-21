import {useState} from "react";

export const UserForm = ({toggleModal, initialValues, submitForm}) => {
    const [username, setUsername] = useState(initialValues.username);
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState(initialValues.email);
    const [country, setCountry] = useState(initialValues.country);
    const [postalCode, setPostalCode] = useState(initialValues.postalCode);
    const [phoneNumber, setPhoneNumber] = useState(initialValues.phoneNumber);
    const [street, setStreet] = useState(initialValues.street);
    const [street2, setStreet2] = useState(initialValues.street2);
    return (
        <form className="form form-modal" action="/" onSubmit={submitForm}>
            <div className="overlay d-flex justify-content-center align-items-center">
                <div className="form-modal-body d-flex flex-column  gap-2align-items-center">
                    <h1>User Info</h1>
                    <input name="id" value={initialValues.id} hidden/>
                    <div>
                        <label>Username*</label><br/>
                        <input name="username" value={username} onChange={(e) => setUsername(e.target.value)}/>
                    </div>
                    <div>
                        <label>Password*</label><br/>
                        <input name="password" onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                    <div>
                        <label>Email*</label><br/>
                        <input name="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div>
                        <label>Country*</label><br/>
                        <input name="country" value={country} onChange={(e) => setCountry(e.target.value)}/>
                    </div>
                    <div>
                        <label>Postal Code*</label><br/>
                        <input name="postalCode" value={postalCode} onChange={(e) => setPostalCode(e.target.value)}/>
                    </div>
                    <div>
                        <label>Phone Number*</label><br/>
                        <input name="phoneNumber" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)}/>
                    </div>
                    <div>
                        <label>Street*</label><br/>
                        <input name="street" value={street} onChange={(e) => setStreet(e.target.value)}/>
                    </div>
                    <div>
                        <label>Street 2 (Optional)</label><br/>
                        <input name="street2" value={street2} onChange={(e) => setStreet2(e.target.value)}/>
                    </div>
                    <div>
                        <label>Roles</label><br/>
                        <select name="roles">
                            <option value={"ADMIN"}>Admin</option>
                            <option value={"EMPLOYEE"}>Employee</option>
                            <option value={"CLIENT"}>Client</option>
                        </select>
                    </div>
                    <hr/>
                    <div className="d-flex gap-2 justify-content-center w-100">
                        <button className="btn btn-success" type="submit">Submit</button>
                        <button className="btn btn-danger" type="button" onClick={toggleModal}>Cancel</button>
                    </div>
                </div>
            </div>
        </form>
    );
}

export default UserForm;
