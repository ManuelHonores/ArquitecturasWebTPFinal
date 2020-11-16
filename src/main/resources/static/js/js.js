"use strict";

// Agregar un viaje por Formulario

let formViaje = document.getElementById("agregarViaje");
if (formViaje != null) {
    formViaje.addEventListener("submit", addViaje);
}

function addViaje() {
    let viaje = {
        name: document.getElementById("nombreViaje").value,
        destinity_city: document.getElementById("ciudadDestino").value,
        continent: document.getElementById("continenteDestino").value,
        day_start_date: document.getElementById("diaInicio").value,
        month_start_date: document.getElementById("mesInicio").value,
        year_start_date: document.getElementById("anioInicio").value,
        day_end_date: document.getElementById("diaFin").value,
        month_end_date: document.getElementById("mesFin").value,
        year_end_date: document.getElementById("anioFin").value,
        description: document.getElementById("descripcion").value,
    }
    fetch('travels/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(viaje)
    })
        .then(response => {
            getViajes();
        })
        .catch(error => console.log(error));
}



// Obtener viajes, tanto los futuros como los realizados.

function getViajes() {
    fetch('travels/')
        .then(response => response.json())
        .then(resp => {
            for (const elem of resp) {
                agregarViajesEnTablas(elem.name, elem.destinity_city, elem.continent, elem.day_start_date, elem.month_start_date, elem.year_start_date, elem.day_end_date, elem.month_end_date, elem.year_end_date, elem.description);
                //agregarPlanesEnTabla(elem.plans);
            }
            getPlanes();
            selectViajes(resp);
        })
        .catch(error => console.log(error));
}

function agregarViajesEnTablas(name, destinity_city, continent, day_start_date, month_start_date, year_start_date, day_end_date, month_end_date, year_end_date, description) {
    let tablaFuturos = document.getElementById("viajesFuturos");
    let bodyFuturos = document.getElementById("tablaViajesFuturos");
    let bodyRealizados = document.getElementById("tablaViajesRealizados");

    let fecha = new Date();
    let fechaTravel = new Date(year_end_date, month_end_date-1, day_end_date);

    if(fecha > fechaTravel) {
        bodyRealizados.innerHTML += "<tr>" + "<td>" + name + "</td>" + "<td>" + destinity_city + "</td>" + "<td>" + continent + "</td>"
            + "<td>" + day_start_date + "</td>" + "<td>" + month_start_date + "</td>" + "<td>" + year_start_date + "</td>"
            + "<td>" + day_end_date + "</td>" + "<td>" + month_end_date + "</td>" + "<td>" + year_end_date + "</td>"
            + "<td>" + description + "</td>" + "</tr>";
    } else {
        bodyFuturos.innerHTML += "<tr>" + "<td>" + name + "</td>" + "<td>" + destinity_city + "</td>" + "<td>" + continent + "</td>"
            + "<td>" + day_start_date + "</td>" + "<td>" + month_start_date + "</td>" + "<td>" + year_start_date + "</td>"
            + "<td>" + day_end_date + "</td>" + "<td>" + month_end_date + "</td>" + "<td>" + year_end_date + "</td>"
            + "<td>" + description + "</td>" + "</tr>";
    }
}

function getPlanes() {
    fetch('plans/')
        .then(response => response.json())
        .then(resp => {
            for (const elem of resp) {
                agregarPlanesEnTabla(elem);
            }
        })
        .catch(error => console.log(error));
}

function agregarPlanesEnTabla(plan) {
    let divPlanes = document.getElementById("mostrarPlanes");

    let str = "<li>" + "Nombre del plan: " + plan.name + "</li>" +
        "<li>" + "Descripcion: " + plan.description + "</li>" +
        "<li>" + "Pais: " + plan.country + "</li>" +
        "<li>" + "Fecha de inicio: " + plan.day_start + "/" + plan.month_start + "/" + plan.year_start + "</li>" +
        "<li>" + "Fecha fin: " + plan.day_end + "/" + plan.month_end + "/" + plan.year_end + "</li>" + "</ul>" + "<br>"

    if(plan.hasOwnProperty("nameHotel")) {
        divPlanes.innerHTML += "Hotel" + "<br>" +
            "<ul>" + "<li>" + "ID de Plan: " + plan.id + "</li>" +  "<li>" + "Nombre: " + plan.nameHotel + "</li>"
            + str + "</ul>"
    } else if(plan.hasOwnProperty("place")) {
        divPlanes.innerHTML += "Excursion" + "<br>" +
            "<ul>" + "<li>" + "ID de Plan: " + plan.id + "</li>" + "<li>" + "Lugar: " + plan.place + "</li>" +
            "<li>" + "Duracion: " + plan.duration + "</li>" + str + "</ul>"
    } else {
        divPlanes.innerHTML += "Vuelo" + "<br>" +
            "<ul>" + "<li>" + "ID de Plan: " + plan.id + "</li>" +  "<li>" + "Compania: " + plan.company + "</li>"
            +"<li>" + "Fecha de partida: " + plan.day_departure_date + "/" + plan.month_departure_date + "/" + plan.year_departure_date + "</li>"
            +"<li>" + "Hora de partida: " + plan.departure_hour + "</li>"
            +"<li>" + "Aeropuerto de salida: " + plan.departure_airport + "</li>"
            +"<li>" + "Fecha de llegada: " + plan.day_return_date + "/" + plan.month_return_date + "/" + plan.year_return_date + "</li>"
            +"<li>" + "Hora de llegada: " + plan.return_hour + "</li>"
            +"<li>" + "Aeropuerto de llegada: " + plan.return_airport + "</li>"
            +"<li>" + "Codigo de reserva: " + plan.reserve_code + "</li>"
            +"<li>" + "Informacion de nave: " + plan.info_airplane + "</li>"
            +"<li>" + "Tiempo entre escalas: " + plan.scale_time + "</li>"
            + str + "</ul>"
    }
}

function addVuelo() {
    let vuelo = {
        company: document.getElementById("...").value,
        departure_date: document.getElementById("...").value,
        departure_hour: document.getElementById("...").value,
        departure_airport: document.getElementById("...").value,
        return_date: document.getElementById("...").value,
        return_hour: document.getElementById("...").value,
        return_airport: document.getElementById("...").value,
        reserve_code: document.getElementById("...").value,
        info_airplane: document.getElementById("...").value,
        scale_time: document.getElementById("...").value,
    }
    fetch('flies/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(vuelo)
    })
        .then(response => {
            getVuelos();
        })
        .catch(error => console.log(error));
}

function selectViajes(data) {
    let selectTravel = document.querySelectorAll(".planViajes");
    for(let j=0; j < selectTravel.length; j++) {
        for (let i = 0; i < data.length; i++) {
            selectTravel[j].innerHTML += "<option value=" + data[i].id + ">"
                + "Nombre: " + data[i].name + " Destino: " + data[i].destinity_city
                + "</option>"
        }
    }
}

getViajes();
