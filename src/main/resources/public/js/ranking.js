function listar_ranking_1(){
    var tabla   = document.createElement("table");
    var tblBody = document.createElement("tbody");
    tabla.style.backgroundColor="white";
    for (var h = 0; h < entidades.length+1; h++) {
      var hilera = document.createElement("tr");
      for (var j = 0; j < 3; j++) {
        var celda = document.createElement("td");
        var textoCelda;
        if (h==0 && j==0){
          textoCelda = document.createTextNode("Rank");
        }else if (h==0 && j==1) {
          textoCelda = document.createTextNode("Entidad");
        }else if (h==0 && j==2){
            textoCelda = document.createTextNode("Tiempo Promedio");
        }else if (h>=1 && j==0){
          textoCelda = document.createTextNode(h);
        }else if (h>=1 && j==1){
          textoCelda = document.createTextNode(entidades[h-1].nombre);
        }else if (h>=1 && j==2){
            textoCelda = document.createTextNode(entidades[h-1].entero);
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
  const entidades = [
    { nombre: "Linea Mitre", entero:  2 },
    { nombre: "Santander Rio", entero: 4},
    { nombre: "Carrefour", entero:  6}
  ];
  const incidentes = [
    { nombre: "Linea Mitre", afectados:  5 },
    { nombre: "Santander Rio", afectados: 7},
    { nombre: "Carrefour", afectados:  9}
  ];

  function listar_ranking_2(){
    var tabla   = document.createElement("table");
    var tblBody = document.createElement("tbody");
    tabla.style.backgroundColor="white";
    for (var h = 0; h < entidades.length+1; h++) {
      var hilera = document.createElement("tr");
      for (var j = 0; j < 3; j++) {
        var celda = document.createElement("td");
        var textoCelda;
        if (h==0 && j==0){
          textoCelda = document.createTextNode("Rank");
        }else if (h==0 && j==1) {
          textoCelda = document.createTextNode("Entidad");
        }else if (h==0 && j==2){
            textoCelda = document.createTextNode("Cantidad de Incidentes");
        }else if (h>=1 && j==0){
          textoCelda = document.createTextNode(h);
        }else if (h>=1 && j==1){
          textoCelda = document.createTextNode(entidades[h-1].nombre);
        }else if (h>=1 && j==2){
            textoCelda = document.createTextNode(entidades[h-1].entero);
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
    function listar_ranking_3(){
        var tabla   = document.createElement("table");
        var tblBody = document.createElement("tbody");
        tabla.style.backgroundColor="white";
        for (var h = 0; h < incidentes.length+1; h++) {
          var hilera = document.createElement("tr");
          for (var j = 0; j < 3; j++) {
            var celda = document.createElement("td");
            var textoCelda;
            if (h==0 && j==0){
              textoCelda = document.createTextNode("Rank");
            }else if (h==0 && j==1) {
              textoCelda = document.createTextNode("Incidentes");
            }else if (h==0 && j==2){
                textoCelda = document.createTextNode("Afectados");
            }else if (h>=1 && j==0){
              textoCelda = document.createTextNode(h);
            }else if (h>=1 && j==1){
              textoCelda = document.createTextNode(incidentes[h-1].nombre);
            }else if (h>=1 && j==2){
                textoCelda = document.createTextNode(incidentes[h-1].afectados);
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