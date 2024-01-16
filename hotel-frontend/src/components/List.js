import {useState} from "react";
import CreationForm from "./CreationForm"
import {initialValues, inputs} from "./UserFormFields";

export const List = ({data, deleteEntry}) => {
    const [searchTerm, setSearchTerm] = useState("");

    function isSearchTermPresent(row, searchTerm) {
        for (let key in row) {
            if (row.hasOwnProperty(key)) {
                const value = row[key];
                if (typeof value === 'string' && value.toLowerCase().includes(searchTerm.toString().toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
    const headers = data.length > 0 ? Object.keys(data[0]) : [];

    console.log(data)


    return (
        <div>
            <div className="d-flex justify-content-center gap-3">
                <label>Search: <input onChange={(e) => setSearchTerm(e.target.value)}/> </label>
            </div>

            <table className="table table-dark table-striped admin-table">
                <tbody>
                <tr>
                    {headers.map(heading => {return <th key={heading}>{heading}</th>})}
                </tr>
                {data.filter((row) =>
                    !searchTerm.length
                    ||
                    isSearchTermPresent(row, searchTerm)
                )
                    .map((item, index) => (
                        <tr key={index}>
                            {headers.map(header => (
                                <td key={header}>{item[header]}</td>
                            ))}

                            <td></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
