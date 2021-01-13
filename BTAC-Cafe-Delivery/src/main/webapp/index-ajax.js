window.onload = function(){
    document.getElementById("switch-register").addEventListener('click', showRegisterView);
    document.getElementById("switch-login").addEventListener('click', showLoginView);
    document.getElementById("login-submit").addEventListener('click', submitLogin);
    document.getElementById("register-submit").addEventListener('click', submitRegister);
}

function showRegisterView(){
    let loginMenu = document.getElementById("login-menu");
    let registerMenu = document.getElementById("register-menu");

    loginMenu.style.display = "none";
    registerMenu.style.display = "block";
}

function showLoginView(){
    let loginMenu = document.getElementById("login-menu");
    let registerMenu = document.getElementById("register-menu");

    loginMenu.style.display = "block";
    registerMenu.style.display = "none";
}

function submitLogin(){

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && status == 200){
            alert("Login successful!");
        } else if(xhttp.readyState == 4 && status != 200){
            alert("Could not login.");
        }

    }
    let loginURL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/api/login";
    xhttp.open("POST", loginURL);
    xhttp.setRequestHeader('Content-Type', 'application/json');

    let user={
        username: document.getElementById("login-username").value,
        password: document.getElementById("login-password").value
    }

    xhttp.send(JSON.stringify(user));
}

function submitRegister(){

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && status == 200){
            alert("Registered successfully!");
            showLoginView();
        } else if(xhttp.readyState == 4 && status != 200){
            alert("Could not register.");
        }
    }
    let registerURL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/api/user";
    xhttp.open("POST", registerURL);
    xhttp.setRequestHeader('Content-Type', 'application/json');

    let user={
        username: document.getElementById("register-username").value,
        password: document.getElementById("register-password").value,
        firstname: document.getElementById("register-firstname").value,
        lastname: document.getElementById("register-lastname").value
    }

    xhttp.send(JSON.stringify(user));
}