// Function to get a query parameter by name
function getQueryParameterByName(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

// Function to set the input field with the UUID
function setInputFieldWithUUID() {
    const uuid = getQueryParameterByName('UUID');
    if (uuid) {
        const inputField = document.getElementById('uuid');
        if (inputField) {
            inputField.value = uuid;
        } else {
            console.error('Input field with id "uuid" not found');
        }
    } else {
        console.error('UUID not found in URL parameters');
    }
}

// Run the function when the document is ready
document.addEventListener('DOMContentLoaded', setInputFieldWithUUID);
