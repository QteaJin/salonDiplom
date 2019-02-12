function checklistJson(json) {
    var tbodyTable = document.getElementById("tbodyTable");

    for (var i = 0; i < json.length; i++) {
        var tr = document.createElement("tr");
        var th = document.createElement("th");

        th.setAttribute("scope", i + 1);
        th.appendChild(document.createTextNode(i + 1));

        var tdCatalog = document.createElement("td");
        if (json[i].catalog.length == 0) {
            tdCatalog.appendChild(document.createTextNode("Услуга"));
        } else {
            tdCatalog.appendChild(document.createTextNode(getNameCatalog(JSON.stringify(json[i].catalog))));
        }

        var tdNameClient = document.createElement("td");
        if (json[i].client == null) {
            tdNameClient.appendChild(document.createTextNode("Клиент"));
        } else {
            tdNameClient.appendChild(document.createTextNode(getNameClient(JSON.stringify(json[i].client))));
        }

        var tdPrice = document.createElement("td");
        tdPrice.appendChild(document.createTextNode(json[i].price));

        var tdDescription = document.createElement("td");
        tdDescription.appendChild(document.createTextNode(json[i].description));

        var tdDateApointmant = document.createElement("td");
        tdDateApointmant.appendChild(document.createTextNode(json[i].dateAppointment));

        var tdStatus = document.createElement("td");
        tdStatus.appendChild(document.createTextNode(json[i].status));

        var tdSelect = document.createElement("td");
        tdSelect.appendChild(getInputStatus());

        var tdButton = document.createElement("td");
        tdButton.appendChild(getButton());

        tr.appendChild(th);
        tr.appendChild(tdCatalog);
        tr.appendChild(tdNameClient);
        tr.appendChild(tdPrice);
        tr.appendChild(tdDescription);

        tr.appendChild(tdDateApointmant);
        tr.appendChild(tdStatus);
        tr.appendChild(tdSelect);
        tr.appendChild(tdButton);


        tbodyTable.appendChild(tr);
    }
}


function getInputStatus() {
    var seletc = document.createElement("select");
    seletc.className = "form-control";

    var optionActive = document.createElement("option");
    optionActive.appendChild(document.createTextNode("ACTIVE"));

    var optionAccept = document.createElement("option");
    optionAccept.appendChild(document.createTextNode("ACCEPT"));

    var optionCansel = document.createElement("option");
    optionCansel.appendChild(document.createTextNode("CANCELED"));

    var optionDone = document.createElement("option");
    optionDone.appendChild(document.createTextNode("DONE"));

    seletc.appendChild(optionActive);
    seletc.appendChild(optionAccept);
    seletc.appendChild(optionCansel);
    seletc.appendChild(optionDone);

    return seletc;
}

function getButton() {
    var seletc = document.createElement("a");
    seletc.className = "btn btn-primary";
    seletc.appendChild(document.createTextNode("Потвердить"));
    return seletc;
}
//json[i].client
function getNameClient(jsonClient) {
    var js = JSON.parse(jsonClient);
    var jsProfile = JSON.parse(JSON.stringify(js.profile));
    console.log(jsProfile.name);
    return jsProfile.name;
}

function getNameCatalog(jsonCatalog) {
    var js = JSON.parse(jsonCatalog);
    console.log(js[0].name);
    return js[0].name;
}