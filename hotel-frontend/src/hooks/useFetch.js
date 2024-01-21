import {useEffect, useState} from "react";
import {getCookie} from "../Cookies";

export const useFetch = (url) => {
    const [data, setData] = useState([]);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState(null);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [totalElements, setTotalElements] = useState(0);
    const [dataChanged, setDataChanged] = useState(0);
    const [pageSize, setPageSize] = useState(25);



    const fetchData = async (url, page) => {
        const controller = new AbortController();
        setIsPending(true);
        try {
            console.log(getCookie("token"))
            let response;
            if (url.toString().includes("?"))
                response = await fetch(url + "&pageNo=" + page + "&pageSize=" + pageSize, {headers:  {Authorization: 'Bearer ' + getCookie("token")}, signal: controller.signal});
            else
                response = await fetch(url + "?pageNo=" + page + "&pageSize=" + pageSize, {headers:  {Authorization: 'Bearer ' + getCookie("token")}, signal: controller.signal});
            const data = (await response.json());
            if (data['content']) {
                setTotalPages(data['totalPages']);
                setTotalElements(data['totalElements']);
                setData(data['content']);
            } else {
                setData(data);
            }
        } catch (e) {
            setError(e);
        } finally {
            setIsPending(false);
        }
        return () => controller.abort();
    }

    useEffect(() => {
        fetchData(url, page);
    }, [page, dataChanged, pageSize])

    function pageChange(i) {
        if (i != null)
            setPage(i);
        return totalPages - 1;
    }

    return {data, isPending, error, totalPages, totalElements, pageChange, setDataChanged, setPageSize}
}