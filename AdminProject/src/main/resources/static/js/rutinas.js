/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah')
                    .attr('src', e.target.result)
                    .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCard(formulario) {
    var valor = formulario.elements[0].value;
    var url = '/carrito/agregar';
    url = url + '/' + valor;
    // Imprime los valores en la consola
    console.log("Valor:", valor);
    console.log("URL:", url);
    $("#resultsBlock").load(url);
}

function updateModels(idMarca) {
    var modeloSelect = document.getElementById('idModelo');
    modeloSelect.innerHTML = '<option value="">Select Model</option>';
    if (idMarca) {
        fetch('/vehiculos/getModelos?idMarca=' + idMarca)
                .then(response => response.json())
                .then(modelos => {
                    modelos.forEach(modelo => {
                        var option = document.createElement('option');
                        option.value = modelo.idModelo;
                        option.text = modelo.nombre;
                        modeloSelect.add(option);
                    });
                });
    }
}

function updateCliModels(idMarca) {
    var modeloSelect = document.getElementById('idModelo');
    modeloSelect.innerHTML = '<option value="">Select Model</option>';
    if (idMarca) {
        fetch('/clientsvehicles/getModelos?idMarca=' + idMarca)
                .then(response => response.json())
                .then(modelos => {
                    modelos.forEach(modelo => {
                        var option = document.createElement('option');
                        option.value = modelo.idModelo;
                        option.text = modelo.nombre;
                        modeloSelect.add(option);
                    });
                });
    }
}

function updateIva(price) {
        const parsedPrice = parseFloat(price) || 0; 
        const iva = (parsedPrice * 0.13).toFixed(2); 
        const final = (parsedPrice + parseFloat(iva)).toFixed(2); 
        document.getElementById('ivaDisplay').innerText = final;
    }