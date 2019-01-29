'use strict';

const URL_DEFAULT = "http://localhost:8080";
const QUICK_ORDER_URL = URL_DEFAULT + "/quickOrder";

var Request = {};
Request.PostQuickOrder = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var quickOrder = JSON.parse(xhr.responseText);
            console.log('responce');
            console.log(quickOrder);
            /*succes(quickOrder);*/
        }
    };

    xhr.open("POST", QUICK_ORDER_URL, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

