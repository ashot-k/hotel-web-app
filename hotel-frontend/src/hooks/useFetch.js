import {useEffect, useState} from "react";
export const useFetch = (url) => {
    const [data, setData] = useState([]);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState(null);
    const [page, setPage] = useState(0);
    const [maxPage, setMaxPage] = useState(0);
    const [dataChanged, setDataChanged] = useState(0);
    const fetchData = async (url, page) => {
        const controller = new AbortController();
        setIsPending(true);
        try {
            const response = await fetch(url + "?pageNo=" + page + "&pageSize=" + 250, {signal: controller.signal,});
            if (response.headers.get("X-Total-Pages")) {
                setMaxPage(response.headers.get("X-Total-Pages") - 1);
            }
            const data = (await response.json());
            setData(data);
        } catch (e) {
            setError(e);
        } finally {
            setIsPending(false);
        }
        return () => controller.abort();
    }

    useEffect(() => {
        fetchData(url, page);
    }, [page, dataChanged])

    function pageNav(i) {
        if (i != null)
            setPage(i);
        return maxPage;
    }

    return {data, isPending, error, pageNav, setDataChanged}
}