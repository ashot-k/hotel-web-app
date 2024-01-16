import {useFetch} from "../hooks/useFetch";
import {useEffect} from "react";

export const CRUDOperations = () => {

    const create =  (e, url) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(entry)
        }).then(() => {
                console.log("new entry added");
            }
        )
    }

    const update= (e, url) =>{
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        console.log(entry)
        fetch(url + "/" + entry['id'], {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(entry)
        }).then(() => {
                console.log("entry updated");
            }
        )
    }
    function remove(url, id) {
        fetch(url + "/" + id, {method: 'DELETE'})
            .then(()=>{
                console.log("entry deleted");
            })
    }
    return {create, update, remove}
}
