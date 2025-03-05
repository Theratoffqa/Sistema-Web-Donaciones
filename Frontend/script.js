document.addEventListener('DOMContentLoaded', function () {
    const mensajeDiv = document.getElementById('mensaje');
    const loginSection = document.getElementById('login');
    const registroSection = document.getElementById('registro');
    const opcionesSection = document.getElementById('opciones');
    const donarSection = document.getElementById('donar');
    const recibirSection = document.getElementById('recibir');
    const listaDonaciones = document.getElementById('listaDonaciones');
    const nombreUsuarioDiv = document.getElementById('nombreUsuario');
    const nombreUsuarioTexto = document.getElementById('nombreUsuarioTexto');
    const btnCerrarSesion = document.getElementById('btnCerrarSesion');
    const detallesDonanteSection = document.getElementById('detallesDonante');
    const infoDonante = document.getElementById('infoDonante');

    let usuarioActual = null;
    let donaciones = [];
    let usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios')) || [];

    function mostrarMensaje(mensaje, tipo = 'success') {
        mensajeDiv.textContent = mensaje;
        mensajeDiv.className = `mensaje ${tipo}`;
        setTimeout(() => mensajeDiv.textContent = '', 3000);
    }

    function mostrarSeccion(seccion) {
        loginSection.style.display = 'none';
        registroSection.style.display = 'none';
        opcionesSection.style.display = 'none';
        donarSection.style.display = 'none';
        recibirSection.style.display = 'none';
        detallesDonanteSection.style.display = 'none';
        seccion.style.display = 'block';
    }

    function limpiarFormulario(formId) {
        document.getElementById(formId).reset();
    }

    function cerrarSesion() {
        usuarioActual = null;
        nombreUsuarioDiv.style.display = 'none';
        mostrarSeccion(loginSection);
        mostrarMensaje('Sesión cerrada correctamente');
    }

    btnCerrarSesion.addEventListener('click', cerrarSesion);

    document.getElementById('formLogin').addEventListener('submit', function (e) {
        e.preventDefault();
        const email = document.getElementById('emailLogin').value;
        const password = document.getElementById('passwordLogin').value;

        const usuario = usuariosRegistrados.find(user => user.email === email && user.password === password);

        if (usuario) {
            usuarioActual = usuario;
            mostrarMensaje('Login exitoso');
            mostrarSeccion(opcionesSection);
            nombreUsuarioTexto.textContent = usuario.nombre;
            nombreUsuarioDiv.style.display = 'block';
        } else {
            mostrarMensaje('Credenciales incorrectas', 'error');
        }
    });

    document.getElementById('formRegistro').addEventListener('submit', function (e) {
        e.preventDefault();
        const nombre = document.getElementById('nombreRegistro').value;
        const email = document.getElementById('emailRegistro').value;
        const telefono = document.getElementById('telefonoRegistro').value;
        const password = document.getElementById('passwordRegistro').value;
        const tipoUsuario = document.getElementById('tipoUsuario').value;

        const usuarioExistente = usuariosRegistrados.find(user => user.email === email);

        if (usuarioExistente) {
            mostrarMensaje('El correo ya está registrado', 'error');
        } else {
            const nuevoUsuario = { id: usuariosRegistrados.length + 1, nombre, email, telefono, password, tipoUsuario };
            usuariosRegistrados.push(nuevoUsuario);
            localStorage.setItem('usuarios', JSON.stringify(usuariosRegistrados));

            mostrarMensaje('Registro exitoso. Por favor, inicia sesión.');
            mostrarSeccion(loginSection);
            limpiarFormulario('formRegistro');
        }
    });

    document.getElementById('btnDonar').addEventListener('click', function () {
        mostrarSeccion(donarSection);
    });

    document.getElementById('btnRecibir').addEventListener('click', function () {
        cargarDonaciones();
        mostrarSeccion(recibirSection);
    });

    document.getElementById('btnRegresarDonar').addEventListener('click', function () {
        mostrarSeccion(opcionesSection);
    });

    document.getElementById('btnRegresarRecibir').addEventListener('click', function () {
        mostrarSeccion(opcionesSection);
    });

    document.getElementById('btnRegresarDetalles').addEventListener('click', function() {
        mostrarSeccion(recibirSection);
    });

    document.getElementById('formDonacion').addEventListener('submit', function (e) {
        e.preventDefault();
        const donacion = {
            descripcion: document.getElementById('descripcion').value,
            cantidad: document.getElementById('cantidad').value,
            fechaVencimiento: document.getElementById('fechaVencimiento').value,
            donanteId: usuarioActual.id
        };

        setTimeout(() => {
            donaciones.push(donacion);
            mostrarMensaje('Donación publicada con éxito');
            mostrarSeccion(opcionesSection);
            limpiarFormulario('formDonacion');
        }, 1000);
    });

    function cargarDonaciones() {
        listaDonaciones.innerHTML = '';
        donaciones.forEach((donacion, index) => {
            const li = document.createElement('li');
            const donante = usuariosRegistrados.find(user => user.id === donacion.donanteId);
            const esDonacionPropia = usuarioActual && donacion.donanteId === usuarioActual.id;

            li.innerHTML = `
                ${donacion.descripcion} - Cantidad: ${donacion.cantidad} - Vence: ${donacion.fechaVencimiento}
                <button class="btn-ver-donante" data-donante-id="${donacion.donanteId}">Ver Donante</button>
                ${esDonacionPropia ? `<button class="btn-eliminar" data-index="${index}">Eliminar Donación</button>` : ''}
            `;
            listaDonaciones.appendChild(li);
        });

        document.querySelectorAll('.btn-ver-donante').forEach(btn => {
            btn.addEventListener('click', function() {
                const donanteId = parseInt(this.getAttribute('data-donante-id'));
                mostrarDetallesDonante(donanteId);
            });
        });

        document.querySelectorAll('.btn-eliminar').forEach(btn => {
            btn.addEventListener('click', function() {
                const index = parseInt(this.getAttribute('data-index'));
                eliminarDonacion(index);
            });
        });
    }

    function mostrarDetallesDonante(donanteId) {
        const donante = usuariosRegistrados.find(user => user.id === donanteId);
        if (donante) {
            infoDonante.innerHTML = `
                <p><strong>Nombre:</strong> ${donante.nombre}</p>
                <p><strong>Email:</strong> ${donante.email}</p>
                <p><strong>Teléfono:</strong> ${donante.telefono}</p>
            `;
            mostrarSeccion(detallesDonanteSection);
        } else {
            mostrarMensaje('Donante no encontrado', 'error');
        }
    }

    function eliminarDonacion(index) {
        if (confirm('¿Estás seguro de que deseas eliminar esta donación?')) {
            donaciones.splice(index, 1);
            mostrarMensaje('Donación eliminada correctamente');
            cargarDonaciones();
        }
    }

    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            mostrarSeccion(document.getElementById(targetId));
        });
    });
});