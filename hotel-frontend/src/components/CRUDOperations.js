export const CRUDOperations = (url) => {

    const create = (e, setDataChanged) => {
        e.preventDefault();
        const entry = Object.fromEntries(new FormData(e.target));
        fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(entry)
        }).then(() => {
                console.log("new entry added");
                setDataChanged((prev) => prev + 1);
            }
        );

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

    return {create, update, remove}
}
