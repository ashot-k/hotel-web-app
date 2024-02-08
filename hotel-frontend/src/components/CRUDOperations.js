import {getCookie} from "../Cookies";
import axios from 'axios';
import {useState} from "react";

export const CRUDOperations = (url) => {

    const [error, setError] = useState(null);
    axios.defaults.headers.common['Authorization'] = "Bearer " + getCookie("token");
    axios.defaults.headers.common['Accept'] = 'application/json';
    axios.defaults.headers.common['Content-Type'] = 'application/json';
   // axios.interceptors.request.use(function (){ setDataChanged((prev) => prev + 1)})

    const createRoom = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        if (entry.imageUrl.name.length > 0) {
            const reader = new FileReader();
            reader.readAsDataURL(entry.imageUrl);
            reader.onload = () => {
                entry.imageUrl = reader.result;
                axios.post(url, {...entry})
                    .then(() => setDataChanged((prev) => prev + 1))
                    .catch(error => setError(error));
            }
        } else {
            delete entry.imageUrl;
            axios.post(url, {...entry})
                .then(() => setDataChanged((prev) => prev + 1))
                .catch(error => setError(error));
        }
    }

    const updateRoom = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));

        if (entry.imageUrl.name.length > 0) {
            const reader = new FileReader();
            reader.readAsDataURL(entry.imageUrl);
            reader.onload = () => {
                entry.imageUrl = reader.result;
                axios.put(url + "/" + entry['id'], {...entry})
                    .then(() => setDataChanged((prev) => prev + 1))
                    .catch(error => setError(error));
            }
        } else {
            delete entry.imageUrl;
            axios(url + "/" + entry['id'], {...entry}
            ) .then(() => setDataChanged((prev) => prev + 1))
                .catch(error => setError(error));
        }
    }

    const create = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        axios.post(url, {...entry})
            .then(() => setDataChanged((prev) => prev + 1))
            .catch(error => setError(error));
    }

    const update = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        axios.put(url + "/" + entry['id'], {...entry})
            .then(() => setDataChanged((prev) => prev + 1))
            .catch(error => setError(error))
    }
    function remove(id, setDataChanged) {
       axios.delete(url + "/" + id)
            .then(() => {
                setDataChanged((prev) => prev + 1);
            }).catch(error => console.log(error));
    }

    return {create, update, remove, createRoom, updateRoom, error}
}
