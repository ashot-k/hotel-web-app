export const inputs = [
    {
        id: 1,
        name: "personId",
        type: "number",
        placeholder: "User Id",
        //   errorMessage: "Name should be more than 3 characters",
        label: "User Id*",
        required: true
    }, {
        id: 2,
        name: "roomId",
        type: "number",
        placeholder: "Room Id",
        //   errorMessage: "Name should be more than 3 characters",
        label: "Room Id*",
        required: true
    }, {
        id: 3,
        name: "start",
        type: "date",
        placeholder: "Start Date",
        //   errorMessage: "Name should be more than 3 characters",
        label: "Start Date*",
        required: true
    },
    {
        id: 4,
        name: "end",
        type: "date",
        placeholder: "End Date",
        //   errorMessage: "Name should be more than 3 characters",
        label: "End Date*",
        required: true
    },
];
export const initialValues = {
    personId: "",
    roomId: "",
    start: "",
    end: ""
};