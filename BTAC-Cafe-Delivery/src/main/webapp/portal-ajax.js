window.onload = function(){
}

function getMenu(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
            let menuItems = JSON.parse(xhttp.responseText);
            drawMenu(menuItems);
        } else if(xhttp.readyState == 4 && xhttp.status != 200){
            alert("Could not load menu.");
        }
    }

    let menuURL = "";
    xhttp.open("GET", menuURL);
    xhttp.send();
}

function drawMenu(menuItems){
    let menuBody = document.getElementById("food-menu-table-body");
    //Clear current menu items
    while(menuBody.firstChild){
        menuBody.removeChild(menuBody.firstChild);
    }
    //Create table rows
    for(let i=0; i<menuItems.length; i++){
        let tr = menuBody.insertRow(-1);
        for(let j=0; j<menuItems[0].length; j++){
            let td = tr.insertCell(-1);
            td.innerHTML = menuItems[i][j];
        }
        let td = tr.insertCell(-1);
        let add = document.createElement("INPUT");
        add.type = "button";
        add.className = "btn btn-success btn-sm col-sm-4";
        add.value = "+";
        add.onclick = function(){ addToOrder(menuItem[i])};

        td = tr.insertCell(-1);
        let sub = document.createElement("INPUT");
        sub.type = "button";
        sub.className = "btn btn-success btn-sm col-sm-4";
        sub.value = "-";
        sub.onclick = function(){ removeFromOrder(menuItem[i])};
    }
}

function addToOrder(item){
    
}

function removeFromOrder(item){
    
}