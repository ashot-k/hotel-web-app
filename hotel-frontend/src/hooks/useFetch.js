import {useEffect, useState} from "react";

export const useFetch = (url) => {
    const [data, setData] = useState([]);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState(null);
    const [page, setPage] = useState(0);
    const [maxPage, setMaxPage] = useState(0);

    useEffect(() => {
        const fetchData = async (url, page) => {
            const controller = new AbortController();
            setIsPending(true);
            try {
                const response = await fetch(url + "?pageNo=" + page + "&pageSize=" + 250, {signal: controller.signal,});
                if(response.headers.get("X-Total-Pages")) {
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
        fetchData(url, page);
    }, [page])

    function pageNav(i) {
        if(i != null) {
            if (i) {
                if (page < maxPage) {
                    setPage(page + 1);
                }
            } else if (!i) {
                if (page > 0) {
                    setPage(page - 1);
                }
            }
        }
        return maxPage;
    }
    return {data, isPending, error,  pageNav}
}