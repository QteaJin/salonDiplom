'use strict';

const URL_DEFAULT = "http://localhost:8080";
const PROFILE_URL = URL_DEFAULT + "/profile";

var Request = {};
Request.PostProfile = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var profile = JSON.parse(xhr.responseText);
            console.log(profile);
            succes(profile);
        }
    };

    xhr.open("POST", PROFILE_URL, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(jsonObject);
};

Request.GetProfile = function (id) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var profile = JSON.parse(xhr.responseText);
            console.log(profile);
        }
    };

    xhr.open("GET", PROFILE_URL +"/"+id, true);
    xhr.send();
};
