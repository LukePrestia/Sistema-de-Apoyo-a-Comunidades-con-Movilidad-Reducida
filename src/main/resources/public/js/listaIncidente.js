function listarTodo() {
    const incidentesContainer = document.getElementById("incidentes");
    const incidentes = Array.from(incidentesContainer.querySelectorAll(".incidente"));
    const incidentTableBody = document.getElementById("incidentTableBody");
    incidentTableBody.innerHTML = "";

    incidentes.forEach(incidente => {
        const entidad = incidente.getAttribute("data-entidad");
        const establecimiento = incidente.getAttribute("data-establecimiento");
        const servicio = incidente.getAttribute("data-servicio");
        const estado = incidente.getAttribute("data-estado");

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${entidad}</td>
            <td>${establecimiento}</td>
            <td>${servicio}</td>
            <td>${estado}</td>
            <td>Acciones</td>
        `;
        incidentTableBody.appendChild(row);
    });
}

function listarAbierto() {
    const incidentesContainer = document.getElementById("incidentes");
    const incidentes = Array.from(incidentesContainer.querySelectorAll(".incidente"));
    const incidentTableBody = document.getElementById("incidentTableBody");
    incidentTableBody.innerHTML = "";

    incidentes.forEach(incidente => {
        const estado = incidente.getAttribute("data-estado");

        if (estado === "INCIDENTE_ABIERTO") {
            const entidad = incidente.getAttribute("data-entidad");
            const establecimiento = incidente.getAttribute("data-establecimiento");
            const servicio = incidente.getAttribute("data-servicio");

            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${entidad}</td>
                <td>${establecimiento}</td>
                <td>${servicio}</td>
                <td>${estado}</td>
                <td>Acciones</td>
            `;
            incidentTableBody.appendChild(row);
        }
    });
}

function listarCerrado() {
    const incidentesContainer = document.getElementById("incidentes");
    const incidentes = Array.from(incidentesContainer.querySelectorAll(".incidente"));
    const incidentTableBody = document.getElementById("incidentTableBody");
    incidentTableBody.innerHTML = "";

    incidentes.forEach(incidente => {
        const estado = incidente.getAttribute("data-estado");

        if (estado === "INCIDENTE_CERRADO") {
            const entidad = incidente.getAttribute("data-entidad");
            const establecimiento = incidente.getAttribute("data-establecimiento");
            const servicio = incidente.getAttribute("data-servicio");

            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${entidad}</td>
                <td>${establecimiento}</td>
                <td>${servicio}</td>
                <td>${estado}</td>
                <td>Acciones</td>
            `;
            incidentTableBody.appendChild(row);
        }
    });
}
