export const CRUDOperations = (url) => {


    const createRoom = (e, setDataChanged) => {
        e.preventDefault();

        const entry = Object.fromEntries(new FormData(e.target));
        const imagePart = entry.imageUrl;
        const roomName = entry.name;

        const imageData = new FormData();
        imageData.append('image', imagePart);
        imageData.append('roomName', roomName);


        entry.imageUrl = roomName + "/" + entry.imageUrl.name;

        fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(entry)
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                fetch('http://192.168.1.75:8080/api/rooms/image', {
                    method: 'POST',
                    body: imageData
                })
                    .then(response => response.json()).then(() => {
                    setDataChanged((prev) => prev + 1);
                })
                    .catch(error => {
                        console.error(error);
                    });
            }).catch(error => {
            console.error(error);
        }).then(setDataChanged((prev) => prev + 1));

    }

    const updateRoom = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        const imageEntry = entry.imageUrl;
        delete entry.imageUrl;
        const roomEntry = JSON.stringify(entry);

        fetch(url + "/" + entry['id'], {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: roomEntry
        }).then(response => response.json())
            .then(data => {
                const formData = new FormData();
                formData.append('image', imageEntry);
                formData.append('roomId', data.id);
                fetch('http://192.168.1.75:8080/api/rooms/image', {method: 'POST', body: formData})
                    .then(response => response.json())
                    .catch(error => {
                        console.error(error);
                    });

            }).then(() => {
            setDataChanged((prev) => prev + 1);
        })
            .catch(error => {
                console.error(error);
            });
    }

    const create = (e, setDataChanged) => {
        e.preventDefault();

        const entry = Object.fromEntries(new FormData(e.target));
        fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
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
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(entry)
        }).then(() => {
                console.log("entry updated");
                setDataChanged((prev) => prev + 1);
            }
        );
    }

    function remove(id, setDataChanged) {
        fetch(url + "/" + id, {method: 'DELETE'})
            .then((message) => {
                console.log(message.text());
                console.log("entry deleted");
                setDataChanged((prev) => prev + 1);
            }).catch(error => console.log(error));
    }

    return {create, update, remove, createRoom, updateRoom}
}
