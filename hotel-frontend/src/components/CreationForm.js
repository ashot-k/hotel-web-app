/*
import FormInput from "./FormInput";
import {useState} from "react";
import {DateInput} from "./DateInput";
import Form from 'react-bootstrap/Form';
export const CreationForm = ({toggleModal, inputs, initialValues, submitForm}) => {
    const [values, setValues] = useState(initialValues);

    const onChange = (name, value) => {
        setValues({...values, [name]: value});
        console.log(values);
    };

    return (
        <form className="form form-modal" action="/" onSubmit={submitForm}>
            <div className="overlay d-flex justify-content-center align-items-center">
                <div className="form-modal-body align-items-center">
                    <h1>Info</h1>
                    <hr/>
                    <input name="id" value={values['id']} hidden={true}/>
                    {inputs.map((input) => {
                        if (input.type === 'date') {
                                return <div>
                                    <DateInput key={input.id} {...input} initValue={values[input.name]}
                                               onChange={(date) => onChange(input.name, date)}/>
                                </div>
                            } else
                                return <FormInput key={input.id} {...input} value={values[input.name]}
                                                  onChange={(e) => onChange(input.name, e.target.value)}/>;
                        }
                    )}
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

export default CreationForm;
*/
