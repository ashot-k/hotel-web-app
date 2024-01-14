import React from 'react';
import Form from "react-bootstrap/Form";

function UserCreationForm(toggleModal, create) {
    return (

        <Form className="form-modal form needs-validation">
            <div className="overlay d-flex justify-content-center align-items-center">
                <div className="form-modal-body align-items-center">
                    <div
                        className="form-modal-content gap-2 d-flex flex-column justify-content-center align-items-baseline">
                        <h1>User Info</h1>
                        <hr/>
                        <Form.Group>
                            <Form.Label className="form-label">Username</Form.Label>
                            <Form.Control type="text" name="username"/>
                        </Form.Group>

                        <label className="form-label">Password</label>
                        <input type="password" name="" className="form-control" required/>

                        <label className="form-label">Email</label>
                        <input type="text" name="" className="form-control" required/>
                        <hr/>
                        <h3>Contact Details</h3>
                        <hr/>

                        <label className="form-label">Phone Number</label>
                        <input type="text" name="" className="form-control" required/>

                        <label className="form-label">Country</label>
                        <input type="text" className="form-control"/>

                        <label className="form-label">Postal Code</label>
                        <input type="text" name="" className="form-control" required/>

                        <label className="form-label">Street 1</label>
                        <input type="text" className="form-control" required/>

                        <label className="form-label">Street 2 (Optional) </label>
                        <input type="text" name="" className="form-control"/>

                        <hr/>
                        <div className="d-flex gap-2 justify-content-center w-100">
                            <button className="btn btn-success" type="submit" onClick={create}>Add User</button>
                            <button className="btn btn-danger" type="button" onClick={toggleModal}>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </Form>
    );
}

export default UserCreationForm;
