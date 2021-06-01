function loadCameras()
{
    fetch("http://localhost:8080/cameras/all")
        .then(response => response.json())
        .then(data => loadCamElements(data))
}

function loadCamElements(cameras)
{
    populateColumns(cameras);
    populateMap(cameras);
}

function getColumn(camera)
{
    if(!camera || !camera.number)
    {
        console.error("Camera has no number!")
        console.error(camera);
        return;
    }

    if(camera.number % 5 === 0 && camera.number % 3 === 0)
        return "column15";

    if(camera.number % 3 === 0)
        return "column3";

    if(camera.number % 5 === 0)
        return "column5";

    return "columnOther";
}

function populateColumns(cameras)
{
    cameras.forEach(camera => {
        let columnId = getColumn(camera);

        let table = document.getElementById(columnId);
        let row = table.insertRow();

        let numberCell = row.insertCell(0);
        let nameCell = row.insertCell(1);
        let latCell = row.insertCell(2);
        let longCell = row.insertCell(3);

        numberCell.innerHTML = camera.number;
        nameCell.innerHTML = camera.name;
        latCell.innerHTML = camera.latitude;
        longCell.innerHTML = camera.longitude;
    });
}

function populateMap(cameras)
{
    var mymap = L.map('mapid').setView([52.0914, 5.1115], 13);

    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1,
        accessToken: 'pk.eyJ1IjoicmFjaGlkZGQiLCJhIjoiY2twZWlsOXJmMDhlNTJ3cmk2N3duYmoxZCJ9.hFoIoBCZZC1JurN6lPiedw'
    }).addTo(mymap);

    cameras.forEach(c => {
        let marker = L.marker([c.latitude, c.longitude]).addTo(mymap);
        let markerText = "<b>" + c.number + "</b> <br>" + c.name;
        marker.bindPopup(markerText);
    });

}

document.onload = loadCameras();