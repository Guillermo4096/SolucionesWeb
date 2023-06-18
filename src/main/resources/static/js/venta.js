(function(){
    var listaBotonesEditar = document.querySelectorAll(".editarVenta");
    listaBotonesEditar.forEach(item =>{
        item.addEventListener("click", e =>{
            document.getElementById('id').value = item.dataset.id;
            document.getElementById('cboProducto').value = item.dataset.producto;
            document.getElementById('txtCan').value = item.dataset.can;
            document.getElementById('txtClientedn').value = item.dataset.clientedn;
            document.getElementById('txtClienteid').value = item.dataset.clienteid;
            document.getElementById('txtUsuario').value = item.dataset.usuario;
            document.getElementById('txtCod').value = item.dataset.cod;
            new bootstrap.Modal(document.getElementById('modalEditar')).show();
        });
    });

})();

