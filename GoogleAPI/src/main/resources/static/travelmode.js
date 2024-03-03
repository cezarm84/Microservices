function initMap() {
    const directionsRenderer = new google.maps.DirectionsRenderer();
    const directionsService = new google.maps.DirectionsService();
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 14,
        center: { lat: 37.77, lng: -122.447 },
    });

    directionsRenderer.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsRenderer);
    document.getElementById("mode").addEventListener("change", () => {
        calculateAndDisplayRoute(directionsService, directionsRenderer);
    });
}

function calculateAndDisplayRoute(directionsService, directionsRenderer) {
    const selectedMode = document.getElementById("mode").value;

    directionsService
        .route({
            origin: { lat: 37.77, lng: -122.447 },
            destination: { lat: 37.768, lng: -122.511 },
            // Note that Javascript allows us to access the constant
            // using square brackets and a string value as its
            // "property."
            travelMode: google.maps.TravelMode[selectedMode],
        })
        .then((response) => {
            directionsRenderer.setDirections(response);
        })
        .catch((e) => window.alert("Directions request failed due to " + status));
}

window.initMap = initMap;
//https://developers.google.com/maps/documentation/javascript/examples/directions-travel-modes#maps_directions_travel_modes-javascript