import {useState} from "react";

export const RoomForm = ({toggleModal, initialValues, submitForm, roomTypes}) => {

    const [roomType, setRoomType] = useState(initialValues.roomType);
    const [name, setName] = useState(initialValues.name);
    const [description, setDescription] = useState(initialValues.description);
    const [size, setSize] = useState(initialValues.size);
    const [price, setPrice] = useState(initialValues.price);
    const [imageUrl, setImageUrl] = useState(initialValues.imageUrl);

    return (
        <form className="form form-modal" action="/" onSubmit={submitForm}>
            <div className="overlay d-flex justify-content-center align-items-center">
                <div className="form-modal-body d-flex flex-column  gap-2align-items-center">
                    <h1>User Info</h1>
                    <input name="id" value={initialValues.id} hidden/>
                    <div>
                        <label>Room Type*</label><br/>
                        <select name="roomType">
                            {roomTypes.map((roomType) => {
                                    if (initialValues.roomType && roomType === initialValues.roomType)
                                        return <option value={roomType} selected>{roomType}</option>
                                    return <option value={roomType}>{roomType}</option>
                                }
                            )}
                        </select>
                    </div>
                    <div>
                        <label>Name*</label><br/>
                        <input name="name" value={name} onChange={(e) => setName(e.target.value)}/>
                    </div>
                    <div>
                        <label>Description*</label><br/>
                        <input name="description" value={description}
                               onChange={(e) => setDescription(e.target.value)}/>
                    </div>
                    <div>
                        <label>Size*</label><br/>
                        <input name="size" type={"number"} value={size} onChange={(e) => setSize(e.target.value)}/>
                    </div>
                    <div>
                        <label>Price*</label><br/>
                        <input name="price" type={"number"} value={price}
                               onChange={(e) => setPrice(e.target.value)}/>
                    </div>
                    <div>
                        <label>Add Image</label><br/>
                        <input name="imageUrl" type={"file"} onChange={(e) => {setImageUrl(e.target.files[0].name);}}/>
                    </div>
                    <div className="d-flex gap-2 justify-content-center w-100">
                        <button className="btn btn-success" type="submit">Submit</button>
                        <button className="btn btn-danger" type="button" onClick={toggleModal}>Cancel</button>
                    </div>
                </div>
            </div>
        </form>
    );
}

export default RoomForm;