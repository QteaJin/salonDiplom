function selectDay() {
    var inputStateDay = document.getElementById("inputStateDay");
    clearOption();
    var inputStateMountsIndex = document.getElementById("inputStateMounts").selectedIndex;
    var inputStateMountsOption = document.getElementById("inputStateMounts").options;
    var count = inputStateMountsOption[inputStateMountsIndex].value;
    var year = new Date();
    var day = 32- new Date(year.getFullYear(), count, 32).getDay();
    for (var i = 1; i <= day; i++) {
        inputStateDay.options[inputStateDay.options.length] = new Option(i, i);
    }
}

function clearOption() {
    var inputStateDay = document.getElementById("inputStateDay");
    for (var i =0; i<inputStateDay.options.length; i++){
        inputStateDay.remove(i);
    }
}

function selectedMounts() {
    var mounts = [
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь"
    ];
    var inputStateMounts = document.getElementById("inputStateMounts");
    for (var i = 0; i < mounts.length; i++) {
        inputStateMounts.options[inputStateMounts.options.length] = new Option(mounts[i], i + 1);
    }
}

function load() {
    selectedMounts();
    selectDay();
}



function geList() {
    var request = Object.create(Request);
    request.GetCheckListALL();
}