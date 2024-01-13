import {useEffect, useState} from "react";

export const useFetch = (url) => {
    const [data, setData] = useState([]);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(url)
            .then(res => {
                if (!res.ok) {
                    throw Error("Failed fetching data from " + url);
                }
                return res.json();
            })
            .then(data => {
                setError(null)
                setIsPending(false);
                setData(data);
            })
            .catch((error) => {
                setError(error.message)
                console.error(error);
            });
    }, [])

    function handleDelete(id){
        fetch(url + "/" + id, {method: 'DELETE'})
            .then(() => {
                setData(data.filter(data => data.id !== id));
            });
    }

    return {data, isPending, error, handleDelete}
}