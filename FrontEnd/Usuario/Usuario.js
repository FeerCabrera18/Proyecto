const url = 'http://localhost:8080/usuario/listaUsuarios';
const tablaUsuarios = document.getElementById('tablaUsuarios');

const mostrarUsuarios = (usuarios) => {
    let html = '';
    usuarios.forEach(usuario => {
        html += `
            <tr>
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido}</td>
                <td>${usuario.direccion}</td>
                <td>${usuario.fecha_nacimiento}</td>
                <td>${usuario.genero}</td>
                <td>${usuario.estado_civil}</td>
                <td>${usuario.gmail}</td>
                <td>${usuario.usuario}</td>
                <td>${usuario.contrasenia}</td>
                <td>
                    <a href="#" class="btn btn-primary btn-sm btnEditar" data-id="${usuario.id}">Editar</a>
                    <a href="#" class="btn btn-danger btn-sm btnBorrar" data-id="${usuario.id}">Eliminar</a>
                </td>
            </tr>
        `;
    });
    tablaUsuarios.innerHTML = html;

    const btnEditores = document.querySelectorAll('.btnEditar');
    btnEditores.forEach(btn => {
        btn.addEventListener('click', editarUsuario);
    });

    const btnBorradores = document.querySelectorAll('.btnBorrar');
    btnBorradores.forEach(btn => {
        btn.addEventListener('click', confirmarEliminarUsuario);
    });
};

const cargarUsuarios = () => {
    fetch(url)
        .then(response => response.json())
        .then(data => mostrarUsuarios(data))
        .catch(error => console.error('Error al obtener usuarios:', error));
};

document.addEventListener('DOMContentLoaded', () => {
    cargarUsuarios();
});

function confirmarEliminarUsuario(event) {
    event.preventDefault();
    const id = event.target.getAttribute('data-id');
    if (confirm('¿Estás seguro de que quieres eliminar este usuario?')) {
        borrarUsuario(id);
    }
}

function borrarUsuario(id) {
    const urlBorrar = `http://localhost:8080/usuario/eliminar/${id}`;
    fetch(urlBorrar, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                alert('Usuario eliminado correctamente');
                cargarUsuarios(); // Vuelve a cargar la lista de usuarios después de eliminar
            } else {
                throw new Error('Error al eliminar usuario');
            }
        })
        .catch(error => console.error('Error al borrar usuario:', error));
}

function editarUsuario(event) {
    event.preventDefault();
    const id = event.target.getAttribute('data-id');
    window.location.href = `../HTML/EditarUsuario.html?idusuario=${id}`;
}