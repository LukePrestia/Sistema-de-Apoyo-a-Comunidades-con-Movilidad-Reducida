function Esconder(){
    var div = document.getElementById("programa");
    if (div.style.display === "none") {
      div.style.display = "block";
    } else {
      div.style.display = "none";
    }
    var div2 = document.getElementById("agregar");
    if (div2.style.visibility === "hidden") {
      div2.style.visibility = "visible";
    } else {
      div2.style.visibility = "hidden";
    }
    var div3 =document.getElementById("text");
    if (div3.innerHTML ==="Volver al Programa") {
        div3.innerHTML = "Agregar Cliente";
      } else {
        div3.innerHTML = "Volver al Programa";
      }
}

function Listar_Prestadora(){    
  console.log(prestadoras)
  document.getElementById("entidadyorganismo").style.display = "none";
  var tabla   = document.createElement("table");
  var tblBody = document.createElement("tbody");
  tabla.style.backgroundColor="white";

  for (var h = 0; h < prestadoras.length+1; h++) {
    var hilera = document.createElement("tr");
    for (var j = 0; j < 2; j++) {
      var celda = document.createElement("td");
      var textoCelda;
      if (h==0 && j==0){
        textoCelda = document.createTextNode("Prestadora");
      }else if (h==0 && j==1) {
        textoCelda = document.createTextNode("Entidades");
      }else if (h>=1 && j==0){
        textoCelda = document.createTextNode(prestadoras[h-1].prestadora);
      }else if (h>=1 && j==1){
        textoCelda = document.createTextNode(prestadoras[h-1].entidades);
      }
      celda.appendChild(textoCelda);
      hilera.appendChild(celda);
    }
    tblBody.appendChild(hilera);
    tabla.appendChild(tblBody);
    tabla.setAttribute("border", "2");
    document.getElementById("ma").innerHTML=tabla.outerHTML;
    
  }
}

function arrayRemove(arr, value) {
    return arr.filter(function (geeks) {
      return geeks != value;
    });
}
function eliminar(){
  let tipoborrar = document.getElementById("tipoBorrar")

  let candidato = document.getElementById("nombreBorrar")
  if(tipoborrar.value =="Prestadora de servicios"){
    console.log(prestadoras.splice(prestadoras.findIndex((prestadora)=>prestadora.prestadora==candidato)))
  }
  if(tipoborrar.value =="Organismo de Control"){
    console.log(organismos.splice(organismos.findIndex((organismo)=>organismo.organismo==candidato)))
  }
}

function Listar_Organismo(){
  console.log(organismos)
  var tabla   = document.createElement("table");
  var tblBody = document.createElement("tbody");
  tabla.style.backgroundColor="white";
  for (var h = 0; h < organismos.length+1; h++) {
    var hilera = document.createElement("tr");
    for (var j = 0; j < 2; j++) {
      var celda = document.createElement("td");
      var textoCelda;
      if (h==0 && j==0){
        textoCelda = document.createTextNode("Organismo");
      }else if (h==0 && j==1) {
        textoCelda = document.createTextNode("Prestadoras");
      }else if (h>=1 && j==0){
        textoCelda = document.createTextNode(organismos[h-1].organismo);
      }else if (h>=1 && j==1){
        textoCelda = document.createTextNode(organismos[h-1].prestadoras);
      }
      celda.appendChild(textoCelda);
      hilera.appendChild(celda);
    }
    tblBody.appendChild(hilera);
    tabla.appendChild(tblBody);
    tabla.setAttribute("border", "2");
    document.getElementById("ma").innerHTML=tabla.outerHTML;
  }
}

function Agregar_Cliente(nombreIngresado, apellidoIngresado){
  if(tipo.value =="Prestadora de servicios"){
    let nuevaPrestadora = {
      prestadora:nombreIngresado,
      entidades:apellidoIngresado
    };
    prestadoras.push(nuevaPrestadora)
  }
  if(tipo.value =="Organismo de Control"){
    let nuevoOrganismo = {
      organismo:nombreIngresado,
      prestadoras:apellidoIngresado
    };
    organismos.push(nuevoOrganismo)
  }

}
var tipo = document.getElementById("tipo")
tipo.addEventListener("change",function() {
  if(tipo.value =="Prestadora de servicios"){
    document.getElementById("segundoCampo").innerHTML="Entidades:"
  }
  if(tipo.value =="Organismo de Control"){
    document.getElementById("segundoCampo").innerHTML="Prestadoras:"
  }
});
function Agregar(){
    Agregar_Cliente(document.getElementById("nombre").value, document.getElementById("apellido").value);
}

var organismos = [
    {
        organismo: "Banco Central",
        prestadoras: "Santader,Macro",
    },
    {
      organismo: "CNRT",
      prestadoras: "Trenes Argentinos,Macro",
    },
    {
      organismo: "Banco Central",
      prestadoras: "Santader,Macro",
    },
    {
      organismo: "Banco Central",
      prestadoras: "Santader,Macro",
    },
];
var prestadoras = [
  {
    prestadora: "Trenes Argentinos",
      entidades: "Línea Mitre,Línea Roca",
  },
  {
    prestadora: "Santander Río",
    entidades: "Santander Río Argentina,Santander Río Colombia",
  },

];
