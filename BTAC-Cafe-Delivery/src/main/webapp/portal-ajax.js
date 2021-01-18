window.onload = function(){
    document.getElementById("logout").addEventListener('click', logout);
    getMenu();
    getOrders();
}

function logout(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
            alert("Logout successfull")
        } else if(xhttp.readyState == 4 && xhttp.status != 200){
            alert("Logout not successfull");
        }
    }

    let logoutURL = "http://localhost:8080/BTAC-Cafe-Delivery/api/logout";
    xhttp.open("GET", logoutURL);
    xhttp.send();

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

    let menuURL = "http://localhost:8080/BTAC-Cafe-Delivery/api/items";
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

let order = new Array();
order = {
        items:{
        "name": "coffee",
        "cost": 2
        },
        items:{
        "name": "banana",
        "cost": 2
        },
        items:{
        "name": "sandwich",
        "cost": 2
        }  
    
};

function addToOrder(item){
    order[order.length] = item;
}

function removeFromOrder(item){
    const index = order.indexOf(item);
    if (index > -1){order.splice(index, 1)}
}

function drawOrder(order){
    let orderBody = document.getElementById("order-table-body");
    //Clear current order
    while(orderBody.firstChild){
        orderBody.removeChild(orderBody.firstChild);
    }
    //Create table rows
    for(let i=0; i<order.length; i++){
        let tr = orderBody.insertRow(-1);
        for(let j=0; j<order[0].length; j++){
            let td = tr.insertCell(-1);
            td.innerHTML = order[i][j];
        }
    }
}


function getOrders(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
            let orders = JSON.parse(xhttp.responseText);
            drawOrders(orders);
        } else if(xhttp.readyState == 4 && xhttp.status != 200){
            alert("Could not load orders.");
        }
    }

    let ordersURL = "http://localhost:8080/BTAC-Cafe-Delivery/api/orders";
    xhttp.open("GET", ordersURL);
    xhttp.send();
}


function drawOrders(orders){
    let ordersBody = document.getElementById("orders-table-body");
    //Clear current orders
    while(ordersBody.firstChild){
        ordersBody.removeChild(ordersBody.firstChild);
    }
    //Create table rows
    for(let i=0; i<orders.length; i++){
        let tr = ordersBody.insertRow(-1);
        for(let j=0; j<orders[0].length; j++){
            let td = tr.insertCell(-1);
            td.innerHTML = orders[i][j];
        }
    }
}