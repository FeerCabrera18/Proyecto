// Variables y constantes del documento
const usernameInput = document.querySelector("#username");
const passwordInput = document.querySelector("#password");
const loginBtn = document.querySelector("#login");

async function login() {
    const link = `http://localhost:8080/usuario/iniciarSesion`;

    if(usernameInput.value.trim() === "" || passwordInput.value.trim()===""){
        alert("Por favor, completa todos los campos.")
        return;
    }

    const response = await fetch(link, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            usuario: usernameInput.value,
            contrasenia: passwordInput.value
        }),
        mode: "cors"
    });

    const status = response.status;

    if (status === 200) {
        window.location.href = "../Home/home.html";
    } else if (status === 401) {
        alert("Credenciales invalidas, compruebe los datos ingresados.");
    } else {
        alert("Ocurrió un error inesperado.");
    }
}
loginBtn.addEventListener("click", login);

window.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        login();
    }
});
