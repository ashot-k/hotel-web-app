import FormInput from "./FormInput";
import {useState} from "react";

export const CreationForm = ({toggleModal, inputs, initialValues, submitForm}) => {
    const [values, setValues] = useState(initialValues);

    const onChange = (e) => {
        setValues({...values, [e.target.name]: e.target.value});
    }

    return (
        <form className="form form-modal" action="/" onSubmit={submitForm}>
            <div className="overlay d-flex justify-content-center align-items-center">
                <div className="form-modal-body align-items-center">
                    <h1>Info</h1>
                    <hr/>
                    {inputs.map((input) => (
                        <FormInput key={input.id} {...input} value={values[input.name]} onChange={onChange}/>
                    ))}
                    <hr/>
                    <div className="d-flex gap-2 justify-content-center w-100">
                        <button className="btn btn-success" type="submit">Add</button>
                        <button className="btn btn-danger" type="button" onClick={toggleModal}>Cancel</button>
                    </div>
                </div>
            </div>
        </form>
    );
}

export default CreationForm;
