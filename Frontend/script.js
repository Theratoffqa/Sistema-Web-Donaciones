document.addEventListener('DOMContentLoaded', function () {
    const mensajeDiv = document.getElementById('mensaje');

    // Función para mostrar mensajes
    function mostrarMensaje(mensaje, tipo = 'success') {
        mensajeDiv.textContent = mensaje;
        mensajeDiv.className = `mensaje ${tipo}`;
        setTimeout(() => mensajeDiv.textContent = '', 3000);
    }

    // Registrar Donante
    document.getElementById('formDonante').addEventListener('submit', function (e) {
        e.preventDefault();
        const donante = {
            nombre: document.getElementById('nombreDonante').value,
            email: document.getElementById('emailDonante').value,
            telefono: document.getElementById('telefonoDonante').value
        };

        fetch('http://localhost:8080/api/donantes', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(donante)
        })
            .then(response => response.json())
            .then(data => mostrarMensaje('Donante registrado con éxito'))
            .catch(error => mostrarMensaje('Error al registrar donante', 'error'));
    });

    // Registrar Beneficiario
    document.getElementById('formBeneficiario').addEventListener('submit', function (e) {
        e.preventDefault();
        const beneficiario = {
            nombre: document.getElementById('nombreBeneficiario').value,
            email: document.getElementById('emailBeneficiario').value,
            telefono: document.getElementById('telefonoBeneficiario').value
        };

        fetch('http://localhost:8080/api/beneficiarios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(beneficiario)
        })
            .then(response => response.json())
            .then(data => mostrarMensaje('Beneficiario registrado con éxito'))
            .catch(error => mostrarMensaje('Error al registrar beneficiario', 'error'));
    });

    // Publicar Donación
    document.getElementById('formDonacion').addEventListener('submit', function (e) {
        e.preventDefault();
        const donacion = {
            tipoAlimento: document.getElementById('tipoAlimento').value,
            cantidad: document.getElementById('cantidad').value,
            fechaVencimiento: document.getElementById('fechaVencimiento').value
        };

        fetch('http://localhost:8080/api/donaciones', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(donacion)
        })
            .then(response => response.json())
            .then(data => mostrarMensaje('Donación publicada con éxito'))
            .catch(error => mostrarMensaje('Error al publicar donación', 'error'));
    });

    // Solicitar Donación
    document.getElementById('formSolicitud').addEventListener('submit', function (e) {
        e.preventDefault();
        const solicitud = {
            nombreSolicitante: document.getElementById('nombreSolicitante').value,
            tipoAlimentoSolicitado: document.getElementById('tipoAlimentoSolicitado').value,
            cantidadSolicitada: document.getElementById('cantidadSolicitada').value
        };

        fetch('http://localhost:8080/api/solicitudes', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(solicitud)
        })
            .then(response => response.json())
            .then(data => mostrarMensaje('Solicitud de donación enviada con éxito'))
            .catch(error => mostrarMensaje('Error al enviar solicitud', 'error'));
    });
});