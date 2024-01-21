import {getCookie} from "../Cookies";

export const CRUDOperations = (url) => {


    const createRoom = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));

        if (entry.imageUrl.name.length > 0) {
            const reader = new FileReader();
            reader.readAsDataURL(entry.imageUrl);
            reader.onload = () => {
                entry.imageUrl = reader.result;
                fetch(url, {
                    method: 'POST',
                    headers: {
                        Accept: 'application/json',
                        'Content-Type': 'application/json',
                        Authorization: "Bearer " + getCookie("token"),
                    },
                    body: JSON.stringify(entry)
                }).then(response => response.json())
                    .then(data => {
                        console.log(data);
                    })
                    .then(() => {
                        setDataChanged((prev) => prev + 1);
                    }).catch(error => {
                    console.error(error);
                });
            }
        } else {
            console.log("no image")
            delete entry.imageUrl;
            fetch(url, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                    Authorization: "Bearer " + getCookie("token"),
                },

                body: JSON.stringify(entry)
            }).then(response => response.json())
                .then(data => {
                    console.log(data);
                })
                .then(() => {
                    setDataChanged((prev) => prev + 1);
                }).catch(error => {
                console.error(error);
            });
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
                fetch(url + "/" + entry['id'], {
                    method: 'PUT',
                    headers: {
                        Accept: 'application/json',
                        'Content-Type': 'application/json',
                        Authorization: "Bearer " + getCookie("token"),
                    },
                    body: JSON.stringify(entry)
                }).then(response => response.json())
                    .then(data => {
                        console.log(data);
                    })
                    .then(() => {
                        setDataChanged((prev) => prev + 1);
                    }).catch(error => {
                    console.error(error);
                });
            }
        } else {
            console.log("no image")
            delete entry.imageUrl;
            fetch(url + "/" + entry['id'], {
                method: 'PUT',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                    Authorization: "Bearer " + getCookie("token"),
                },

                body: JSON.stringify(entry)
            }).then(response => response.json())
                .then(data => {
                    console.log(data);
                })
                .then(() => {
                    setDataChanged((prev) => prev + 1);
                }).catch(error => {
                console.error(error);
            });
        }
    }

    const create = (e, setDataChanged) => {
        e.preventDefault();


        const entry = Object.fromEntries(new FormData(e.target));
        console.log(JSON.stringify(entry))
        console.log(" auth token " + getCookie("token"))
        fetch(url, {
            method: 'POST',
            headers: {
                Accept: 'application/json', 'Content-Type': 'application/json',
                Authorization: "Bearer " + getCookie("token"),
            },
            body: JSON.stringify(entry)
        }).then(response => response.json())
            .then(data => {
                setDataChanged((prev) => prev + 1);
            }).catch(error => {
            console.error(error);
        });
    }

    const update = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        console.log(entry)
        fetch(url + "/" + entry['id'], {
            method: 'PUT',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                Authorization: "Bearer " + getCookie("token"),
            },

            body: JSON.stringify(entry)
        }).then(() => {
                console.log("entry updated");
                setDataChanged((prev) => prev + 1);
            }
        );
    }

    function remove(id, setDataChanged) {
        fetch(url + "/" + id, {
            method: 'DELETE',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                Authorization: "Bearer " + getCookie("token"),
            },
        })
            .then((message) => {
                console.log(message.text());
                console.log("entry deleted");
                setDataChanged((prev) => prev + 1);
            }).catch(error => console.log(error));
    }

    return {create, update, remove, createRoom, updateRoom}
}
