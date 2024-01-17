import {useEffect, useState} from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Form from 'react-bootstrap/Form';
export const DateInput = (props) => {

    const {label, onChange, errorMessage, id, initValue,...inputProps} = props;
    const [date, setDate] = useState(initValue ? new Date(initValue) : new Date());
    const [value, setValue] = useState(initValue || null);

    const handleDateChange = (selectedDate) => {
        setDate(selectedDate);
        setValue(selectedDate);
        onChange(selectedDate); // Call the onChange prop if provided
    };
    return (
        <Form.Group className="mb-3 form-input">
            <Form.Label className="form-label">{label}</Form.Label>
            <br/>
            <DatePicker
                value={value}
                name={inputProps['name']}
                dateFormat="yyyy-MM-dd"
                selected={date}
                onChange={handleDateChange}
            />
        </Form.Group>
    );
};