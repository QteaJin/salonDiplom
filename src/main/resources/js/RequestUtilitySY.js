'use strict';

const URL_DEFAULT = "http://localhost:8080";
const URL_CHECKLIST = URL_DEFAULT + "/checklist";


var Request = {};
Request.GetCheckListWorkerByStatusAndDayAndMountsAndYear =
    function (status, day, mounts, year) {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var quickOrder = JSON.parse(xhr.responseText);
                checklistJson(quickOrder);
            }
        };


        xhr.open("GET", URL_CHECKLIST + "/quick", true);
        xhr.send();
    };

Request.GetCheckListALL = function () {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var checkListJson = JSON.parse(xhr.responseText);
            checklistJson(checkListJson);
        }
    };

    xhr.open("GET", URL_CHECKLIST + "/all", true);
    xhr.send();
};



