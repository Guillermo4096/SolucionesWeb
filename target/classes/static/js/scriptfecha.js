function obtenerFechaActual() {
    // Obtener la fecha actual
    var fechaActual = new Date();
  
    // Obtener el día del mes
    var dia = fechaActual.getDate();
  
    // Obtener el nombre del mes
    var meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
    var mes = meses[fechaActual.getMonth()];
  
    // Obtener el año
    var anio = fechaActual.getFullYear();
  
    // Crear el mensaje con el formato deseado
    var mensaje = "Hoy es " + dia + " de " + mes + " del " + anio;
  
    // Mostrar el mensaje en el elemento con el id "fechaActual"
    document.getElementById("fechaActual").textContent = mensaje;
  }
  
  window.onload = obtenerFechaActual;