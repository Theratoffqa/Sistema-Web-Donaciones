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

    let usuarioActual = null;
    let donaciones = []; // Datos mock para donaciones

    // Cargar usuarios registrados desde localStorage
    let usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios')) || [];

    // Función para mostrar mensajes
    function mostrarMensaje(mensaje, tipo = 'success') {
        mensajeDiv.textContent = mensaje;
        mensajeDiv.className = `mensaje ${tipo}`;
        setTimeout(() => mensajeDiv.textContent = '', 3000);
    }

    // Función para mostrar/ocultar secciones
    function mostrarSeccion(seccion) {
        loginSection.style.display = 'none';
        registroSection.style.display = 'none';
        opcionesSection.style.display = 'none';
        donarSection.style.display = 'none';
        recibirSection.style.display = 'none';
        seccion.style.display = 'block';
    }

    // Función para limpiar formularios
    function limpiarFormulario(formId) {
        document.getElementById(formId).reset();
    }

    // Función para cerrar sesión
    function cerrarSesion() {
        usuarioActual = null;
        nombreUsuarioDiv.style.display = 'none';
        mostrarSeccion(loginSection);
        mostrarMensaje('Sesión cerrada correctamente');
    }

    // Botón Cerrar Sesión
    btnCerrarSesion.addEventListener('click', cerrarSesion);

    // Simular login
    document.getElementById('formLogin').addEventListener('submit', function (e) {
        e.preventDefault();
        const email = document.getElementById('emailLogin').value;
        const password = document.getElementById('passwordLogin').value;

        // Buscar el usuario en la lista de usuarios registrados
        const usuario = usuariosRegistrados.find(user => user.email === email && user.password === password);

        if (usuario) {
            usuarioActual = usuario;
            mostrarMensaje('Login exitoso');
            mostrarSeccion(opcionesSection); // Mostrar opciones después de login

            // Mostrar el nombre del usuario en la parte superior derecha
            nombreUsuarioTexto.textContent = usuario.nombre;
            nombreUsuarioDiv.style.display = 'block';
        } else {
            mostrarMensaje('Credenciales incorrectas', 'error');
        }
    });

    // Simular registro
    document.getElementById('formRegistro').addEventListener('submit', function (e) {
        e.preventDefault();
        const nombre = document.getElementById('nombreRegistro').value;
        const email = document.getElementById('emailRegistro').value;
        const password = document.getElementById('passwordRegistro').value;

        // Verificar si el usuario ya está registrado
        const usuarioExistente = usuariosRegistrados.find(user => user.email === email);

        if (usuarioExistente) {
            mostrarMensaje('El correo ya está registrado', 'error');
        } else {
            // Registrar nuevo usuario
            const nuevoUsuario = { id: usuariosRegistrados.length + 1, nombre, email, password };
            usuariosRegistrados.push(nuevoUsuario);
            localStorage.setItem('usuarios', JSON.stringify(usuariosRegistrados)); // Guardar en localStorage

            mostrarMensaje('Registro exitoso. Por favor, inicia sesión.');
            mostrarSeccion(loginSection); // Redirigir a login después de registro
            limpiarFormulario('formRegistro'); // Limpiar el formulario de registro
        }
    });

    // Botón Donar
    document.getElementById('btnDonar').addEventListener('click', function () {
        mostrarSeccion(donarSection); // Mostrar formulario de donación
    });

    // Botón Recibir
    document.getElementById('btnRecibir').addEventListener('click', function () {
        cargarDonaciones();
        mostrarSeccion(recibirSection); // Mostrar lista de donaciones
    });

    // Botón Regresar (Donar)
    document.getElementById('btnRegresarDonar').addEventListener('click', function () {
        mostrarSeccion(opcionesSection); // Regresar al menú de opciones
    });

    // Botón Regresar (Recibir)
    document.getElementById('btnRegresarRecibir').addEventListener('click', function () {
        mostrarSeccion(opcionesSection); // Regresar al menú de opciones
    });

    // Simular donación
    document.getElementById('formDonacion').addEventListener('submit', function (e) {
        e.preventDefault();
        const donacion = {
            tipoAlimento: document.getElementById('tipoAlimento').value,
            cantidad: document.getElementById('cantidad').value,
            fechaVencimiento: document.getElementById('fechaVencimiento').value,
            donanteId: usuarioActual.id
        };

        // Simular respuesta del servidor
        setTimeout(() => {
            donaciones.push(donacion);
            mostrarMensaje('Donación publicada con éxito');
            mostrarSeccion(opcionesSection); // Volver a las opciones después de donar
            limpiarFormulario('formDonacion'); // Limpiar el formulario de donación
        }, 1000);
    });

    // Cargar donaciones mock
    function cargarDonaciones() {
        listaDonaciones.innerHTML = '';
        donaciones.forEach(donacion => {
            const li = document.createElement('li');
            li.textContent = `${donacion.tipoAlimento} - Cantidad: ${donacion.cantidad} - Vence: ${donacion.fechaVencimiento}`;
            listaDonaciones.appendChild(li);
        });
    }

    // Manejar enlaces de navegación
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            mostrarSeccion(document.getElementById(targetId));
        });
    });
});