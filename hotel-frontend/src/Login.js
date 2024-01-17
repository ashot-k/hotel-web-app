import React from 'react';

const Login = () => {
    function loginRequest() {

    }

    return (
        <div className="d-flex justify-content-center">
            <div className="w-25 p-3">
                <div className="mb-3 form-input">
                    <label className="form-label">Username</label>
                    <input className="form-control" name="username"/>
                </div>
                <div className="mb-3 form-input">
                    <label className="form-label">Password</label>
                    <input className="form-control" name="password"/>
                </div>
                <div>
                    <button id="submit" className="btn btn-primary" type="button" onClick={() => loginRequest()}>Login
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Login;