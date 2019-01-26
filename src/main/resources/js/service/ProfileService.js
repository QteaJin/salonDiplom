function test() {
   //  var profile = Object.create(ProfileBean);
    //    //  profile.setName = "As";
    //    //  profile.setPhone = "+38096565646";
    //    //  profile.setEmail = "od081274jil";
    //    //  profile.setLogin = "sdddd";
    //    //  profile.setPassword = 'demo';
    //    //
    //    //  var json = JSON.stringify(profile);
    //    //  console.log(json);
    //    //
    //    // var pfff = Object.create(ProfileBean);
    //    //
    //    //
    //    //
    //    // console.log(pfff.toString())
    var request = Object.create(Request);
    request.GetProfile (1);

    // console.log(JSON.stringify(profile));
    // var request = Object.create(Request);
    // request.PostProfile("http://localhost:8080/prof", JSON.stringify(profile));
}

function succes(profile) {
   var worker = JSON.parse(profile.worker);
}