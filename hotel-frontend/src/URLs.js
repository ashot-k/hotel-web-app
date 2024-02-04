const localHostUrl = "http://192.168.1.64:8080";

const rootURL = localHostUrl + "/api"
const loginURL = rootURL + "/auth/login"
const registerURL = rootURL + "/auth/register"
const roomsURL = rootURL + "/rooms"
const roomTypesURL = roomsURL + "/room-types"
const roomImagesURL = roomsURL + "/image/"
const usersURL = rootURL + "/users"
const reservationsURL = rootURL + "/reservations"
const availableRoomsURL = reservationsURL + "/available"

export {rootURL, roomsURL, roomTypesURL, roomImagesURL, usersURL, reservationsURL, availableRoomsURL, loginURL, registerURL};