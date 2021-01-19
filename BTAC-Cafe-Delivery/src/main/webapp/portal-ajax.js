window.onload = function(){
    document.getElementById("logout").addEventListener('click', logout);
    document.getElementById("order-submit").addEventListener('click', submitOrder);
    document.getElementById("order-clear").addEventListener('click', clearOrder);
    drawMenu();
    getOrders();
}

function logout(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
            alert("Logout successfull");
            window.location.replace("http://localhost:8080/BTAC-Cafe-Delivery/");
        } else if(xhttp.readyState == 4 && xhttp.status != 200){
            alert("Logout not successfull");
        }
    }

    let logoutURL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/api/logout";
    xhttp.open("GET", logoutURL);
    xhttp.send();

}

// function getMenu(){
//     let xhttp = new XMLHttpRequest();

//     xhttp.onreadystatechange = function(){
//         if(xhttp.readyState == 4 && xhttp.status == 200){
//             let menuItems = JSON.parse(xhttp.responseText);
//             drawMenu(menuItems);
//         } else if(xhttp.readyState == 4 && xhttp.status != 200){
//             alert("Could not load menu.");
//         }
//     }

//     let menuURL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/api/items";
//     xhttp.open("GET", menuURL);
//     xhttp.send();
// }

function drawMenu(){
    let menuBody = document.getElementById("food-menu-table-body");
    //Clear current menu items
    // while(menuBody.firstChild){
    //     menuBody.removeChild(menuBody.firstChild);
    // }
    //Create table rows
    for(let i=0; i < menuBody.rows.length; i++){
        let tr = menuBody.rows[i];
        // for(let j=0; j<menuItems[0].length; j++){
        //     let td = tr.insertCell(-1);
        //     td.innerHTML = menuItems[i][j];
        // }
        let td = tr.insertCell(-1);
        let add = document.createElement("INPUT");
        add.type = "button";
        add.className = "btn btn-success btn-sm col-sm-8";
        add.value = "add";
        add.onclick = function(){addToOrder((menuBody.rows[i]))};
        td.appendChild(add);

        td = tr.insertCell(-1);
        let sub = document.createElement("INPUT");
        sub.type = "button";
        sub.className = "btn btn-danger btn-sm col-sm-6";
        sub.value = "remove";
        sub.onclick = function(){ removeFromOrder(menuBody.rows[i])};
        td.appendChild(sub);
    }
}

var items = [];

function addToOrder(item){
    items.push({ itemName: item.cells[0].innerHTML,
        cost: item.cells[1].innerHTML});
    drawOrder();
}

function removeFromOrder(item){
    let parsed = {itemName: item.cells[0].innerHTML,
        cost: item.cells[1].innerHTML};
    const index = items.findIndex(x => x.itemName == parsed.itemName);
    if (index > -1){items.splice(index, 1)}
    drawOrder();
}

function drawOrder(){
    console.log(items);
    let orderBody = document.getElementById("order-table-body");
    //Clear current order
    while(orderBody.firstChild){
        orderBody.removeChild(orderBody.firstChild);
    }
    //Create table rows
    for(let i=0; i<items.length; i++){
        let tr = orderBody.insertRow(-1);
        let td = tr.insertCell(-1);
        td.innerHTML = items[i].itemName;
        td = tr.insertCell(-1);
        td.innerHTML = items[i].cost;
    }
}

function submitOrder(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
            alert("Order submitted!");
            items = [];
            drawOrder();
            getOrders();
        } else if(xhttp.readyState == 4 && xhttp.status != 200){
            alert("Could not submit order.");
        }
    }

    let orderURL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/api/order";
    xhttp.open("POST", orderURL);
    xhttp.setRequestHeader('Content-Type', 'application/json');

    let order = {items: items};
    xhttp.send(JSON.stringify(order));
    console.log(JSON.stringify(order));
}

function clearOrder(){
    items = [];
    drawOrder();
}


function getOrders(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
            let orders = JSON.parse(xhttp.responseText);
            console.log(orders);
            drawOrders(orders);
        } else if(xhttp.readyState == 4 && xhttp.status != 200){
            alert("Could not load orders.");
        }
    }

    let ordersURL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/api/orderbyuser";
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
        let td = tr.insertCell(-1);
        td.innerHTML = orders[i].total;
        td = tr.insertCell(-1);
        for(let j=0; j<orders[i].items.length; j++){
            if(j>0){
                td.innerHTML += ", ";
            }
            td.innerHTML += orders[i].items[j].itemName;
        }
    }
}