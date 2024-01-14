import React, {useState} from 'react';
import Form from 'react-bootstrap/Form';
const FormInput = (props) => {
    const {label, onChange, errorMessage, id,  ...inputProps} = props;
    const [focused, setFocused] = useState(false);
    const handleFocus = (e) =>{
        setFocused(true);
    }

    return (
        <Form.Group className="mb-3 form-input">
            <Form.Label className="form-label">{label}</Form.Label>
            <Form.Control {...inputProps} onChange={onChange} onBlur={handleFocus} focused={focused.toString()}/>
            <span>{errorMessage}</span>
        </Form.Group>
    );
};

export default FormInput;
