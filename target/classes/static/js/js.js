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
                selectContinents(elem.continent)
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

let formVuelo = document.querySelector(".agregarVuelo");
if(formVuelo!=null){
    formVuelo.addEventListener('submit', async function(){
        let idViaje = document.querySelector(".planViajes").value;
        let fechaInicio = document.getElementById("fechaVueloInicio").value
        let fechaIni = new Date(fechaInicio)
        let fechaFin = document.getElementById("fechaVueloFin").value
        let fechaF = new Date(fechaFin)
        let fechaInicioPlan = document.getElementById("fechaInicioPlan").value
        let fechaIniPlan = new Date(fechaInicioPlan)
        let fechaFinPlan = document.getElementById("fechaFinPlan").value
        let fechaFPlan = new Date(fechaFinPlan)
        let finished;
        if(fechaFPlan < new Date()) {
            finished = true;
        } else {
            finished = false;
        }
        let vuelo = {
            "name" : document.getElementById('nombrePlan').value,
            "description" : document.getElementById('descripcion').value,
            "finished": finished,
            "country" : document.getElementById('pais').value,
            "day_start" : fechaIniPlan.getDate()+1,
            "month_start" : fechaIniPlan.getMonth()+1,
            "year_start" : fechaIniPlan.getFullYear(),
            "day_end" : fechaFPlan.getDate()+1,
            "month_end" : fechaFPlan.getMonth()+1,
            "year_end" : fechaFPlan.getFullYear(),
            "company" : document.getElementById('compania').value,
            "day_departure_date" : fechaIni.getDate()+1,
            "month_departure_date" : fechaIni.getMonth()+1,
            "year_departure_date" : fechaIni.getFullYear(),
            "departure_hour" : document.getElementById('horaVueloInicio').value,
            "departure_airport" : document.getElementById('aeropuertoSalida').value,
            "day_return_date" : fechaF.getDate()+1,
            "month_return_date" : fechaF.getMonth()+1,
            "year_return_date" : fechaF.getFullYear(),
            "return_hour" : document.getElementById('horaVueloFin').value,
            "return_airport" : document.getElementById('aeropuertoLlegada').value,
            "reserve_code" : document.getElementById('codigoReserva').value,
            "info_airplane" : document.getElementById('infoAeropuerto').value,
            "scale_time" : document.getElementById('escalaTiempo').value
        }
        console.log(vuelo)
        await fetch('flies/' + idViaje, {
            method : 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(vuelo)
        }).then(response => {
            getViajes();
        }).catch(error => console.log(error));
    })
}

let formHotel = document.querySelector(".agregarReserva");
if(formHotel!=null){
    formHotel.addEventListener('submit', async function() {
        let idViaje = document.querySelector(".planViajes").value;
        let fechaInicioPlan = document.getElementById("fechaInicioPlan").value
        let fechaIniPlan = new Date(fechaInicioPlan)
        let fechaFinPlan = document.getElementById("fechaFinPlan").value
        let fechaFPlan = new Date(fechaFinPlan)
        let finished;
        if(fechaFPlan < new Date()) {
            finished = true;
        } else {
            finished = false;
        }
        let hotel = {
            "name" : document.getElementById('nombrePlan').value,
            "description" : document.getElementById('descripcionPlan').value,
            "finished": finished,
            "country" : document.getElementById('pais').value,
            "day_start" : fechaIniPlan.getDate()+1,
            "month_start" : fechaIniPlan.getMonth()+1,
            "year_start" : fechaIniPlan.getFullYear(),
            "day_end" : fechaFPlan.getDate()+1,
            "month_end" : fechaFPlan.getMonth()+1,
            "year_end" : fechaFPlan.getFullYear(),
            "name_hotel" : document.getElementById('hotel').value
        }
        await fetch('hotels/' + idViaje, {
            method : 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(hotel)
        }).then(response => {
            getViajes();
        }).catch(error => console.log(error));
    })
}


let formExc = document.querySelector(".agregarPlan");
if(formExc!=null){
    formExc.addEventListener('submit', async function() {
        //e.preventDefault();
        let idViaje = document.querySelector(".planViajes").value;
        let fechaInicioPlan = document.getElementById("fechaInicioPlan").value
        let fechaIniPlan = new Date(fechaInicioPlan)
        let fechaFinPlan = document.getElementById("fechaFinPlan").value
        let fechaFPlan = new Date(fechaFinPlan)
        let finished;
        if(fechaFPlan < new Date()) {
            finished = true;
        } else {
            finished = false;
        }
        console.log(idViaje);
        console.log(fechaIniPlan);
        console.log(fechaFinPlan);
        let excursion = {
            "name": document.getElementById('nombrePlan').value,
            "description": document.getElementById('descripcion').value,
            "finished": finished,
            "country": document.getElementById('pais').value,
            "day_start": fechaIniPlan.getDate() + 1,
            "month_start": fechaIniPlan.getMonth() + 1,
            "year_start": fechaIniPlan.getFullYear(),
            "day_end": fechaFPlan.getDate() + 1,
            "month_end": fechaFPlan.getMonth() + 1,
            "year_end": fechaFPlan.getFullYear(),
            "place": document.getElementById('lugar').value,
            "duration": document.getElementById('duracion').value
        }
        await fetch('excursions/' + idViaje, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(excursion)
        }).then(response => {
            getViajes();
        }).catch(error => console.log(error));
    })
}


//Reportes

let selectUserReports = document.getElementById('selectUserReports')


function selectContinents(continent) {
    let select = document.getElementById("continents");
    select.innerHTML = "";

    let array = [];
    if(!array.includes(continent)) {
        array.push(continent)
    }

    for(let i=0; i<array.length; i++) {
        select.innerHTML += "<option value=" + array[i] + ">" + array[i] + "</option>"
    }

}

let formUserReports = document.getElementById("btnReport");
if(formUserReports != null) {
    formUserReports.addEventListener("click", getUserReports);
}

let conti = document.getElementById("continents");
/*conti.addEventListener("change", async function(){
    if(selectUserReports.value == "zonaGeografica/") {
        console.log("entroEnesteSelect")
        await fetch('users/' + conti.value + "/" + 28)
            .then(response => response.json())
            .then(resp => {
                console.log(resp);
                //getViajes();
            })
            .catch(error => console.log(error));
    }
})*/



async function getUserReports() {
    if(selectUserReports.value == "zonaGeografica/") {
        await fetch('users/' + conti.value + "/" + 56, )
            .then(response => response.json())
            .then(resp => {
                console.log(resp);
                let divReports = document.getElementById("showReports");
                divReports.innerHTML = "";
                for (const elem of resp) {
                    showReports(elem)
                }
                //getViajes();
            })
            .catch(error => console.log(error));
    } else if (selectUserReports.value == "users/reportDays/"){
        let fechaI = document.getElementById('fechaInicioReporte').value
        let fechaF = document.getElementById('fechaFinReporte').value
        let dateI = new Date(fechaI)
        let dateF = new Date(fechaF)
        let diaI = dateI.getDate() + 1
        let mesI = dateI.getMonth() + 1
        let anoI = dateI.getFullYear()
        let diaF = dateF.getDate() + 1
        let mesF = dateF.getMonth() + 1
        let anoF = dateF.getFullYear()
        console.log(diaI + "/" + mesI + "/" + anoI);
        console.log(diaF + "/" + mesF + "/" + anoF);
        await fetch(selectUserReports.value + 56 + "/" + diaI + "/" + mesI + "/" + anoI + "/" + diaF + "/" + mesF + "/" + anoF, )
                    .then(response => response.json())
                    .then(resp => {
                        console.log(resp);
                        let divReports = document.getElementById("showReports");
                        divReports.innerHTML = "";
                        for (const elem of resp) {
                            showReports(elem)
                        }
                        //getViajes();
                    })
                    .catch(error => console.log(error));
    }else {
        await fetch(selectUserReports.value + 56, )
            .then(response => response.json())
            .then(resp => {
                console.log(resp);
                let divReports = document.getElementById("showReports");
                divReports.innerHTML = "";
                for (const elem of resp) {
                    showReports(elem)
                }
                //getViajes();
            })
            .catch(error => console.log(error));
    }
}


selectUserReports.addEventListener("change", function(){
    if(selectUserReports.value == "users/reportDays/"){
        document.getElementById('continents').disabled =  true
        document.getElementById('fechaInicioReporte').disabled =  false
        document.getElementById('fechaFinReporte').disabled =  false
    }else if(selectUserReports.value == "zonaGeografica/"){
        document.getElementById('continents').disabled =  false
        document.getElementById('fechaInicioReporte').disabled =  true
        document.getElementById('fechaFinReporte').disabled =  true
    }else{
        document.getElementById('fechaInicioReporte').disabled =  true
        document.getElementById('fechaFinReporte').disabled =  true
        document.getElementById('continents').disabled =  true
    }
})


function showReports(plan) {
    let divReports = document.getElementById("showReports");

    let str = "<li>" + "Nombre del plan: " + plan.name + "</li>" +
        "<li>" + "Descripcion: " + plan.description + "</li>" +
        "<li>" + "Pais: " + plan.country + "</li>" +
        "<li>" + "Fecha de inicio: " + plan.day_start + "/" + plan.month_start + "/" + plan.year_start + "</li>" +
        "<li>" + "Fecha fin: " + plan.day_end + "/" + plan.month_end + "/" + plan.year_end + "</li>" + "</ul>" + "<br>"

    if(plan.hasOwnProperty("nameHotel")) {
        divReports.innerHTML += "Hotel" + "<br>" +
            "<ul>" + "<li>" + "ID de Plan: " + plan.id + "</li>" +  "<li>" + "Nombre: " + plan.nameHotel + "</li>"
            + str + "</ul>"
    } else if(plan.hasOwnProperty("place")) {
        divReports.innerHTML += "Excursion" + "<br>" +
            "<ul>" + "<li>" + "ID de Plan: " + plan.id + "</li>" + "<li>" + "Lugar: " + plan.place + "</li>" +
            "<li>" + "Duracion: " + plan.duration + "</li>" + str + "</ul>"
    } else {
        divReports.innerHTML += "Vuelo" + "<br>" +
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


//Reportes compania

//Login

let token = null;

let formLogin = document.getElementById("formLogin");
if(formLogin != null) {
    formLogin.addEventListener("submit", login)
}

async function login() {
    let mail = document.getElementById("mailLogin").value;
    let pass = document.getElementById("passwordLogin").value;

    let data = {
        "mail" : mail,
        "password" : pass
    }

    await fetch('login/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(response => {
            token = response.text();
            console.log(token);
            //getViajes();
        })
        .catch(error => console.log(error));

    //Ocultar el login luego de que termine de loguearse
}

let formRegister = document.getElementById("formRegister");
if(formRegister != null) {
    formRegister.addEventListener("submit", register)
}

async function register(e) {
    e.preventDefault();
    let name = document.getElementById("nameRegister").value;
    let mail = document.getElementById("mailRegister").value;
    let pass = document.getElementById("passwordRegister").value;

    let data = {
        "name" : name,
        "mail" : mail,
        "password" : pass
    }

    await fetch('login/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(response => {
            token = response.text();
            console.log(token);
            //getViajes();
        })
        .catch(error => console.log(error));

    //Ocultar el login luego de que termine de loguearse
}
