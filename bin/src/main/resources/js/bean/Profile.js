'use strict';

var ProfileBean = {
    id: Number,
    name: String,
    phone: String,
    email: String,
    login: String,
    password: String,
    worker: Object,
    client: Object,

    get getId() {
        return this.id;
    },
    set setId(id) {
        this.id = id;
    },
    get getName() {
        return this.name;
    },
    set setName(name) {
        this.name = name;
    },
    get getPhone() {
        return this.phone;
    },
    set setPhone(phone) {
        this.phone = phone;
    },
    get getEmail() {
        return this.email;
    },
    set setEmail(email) {
        this.email = email;
    },
    get getLogin() {
        return this.login;
    },
    set setLogin(login) {
        this.login = login;
    },
    get getPassword() {
        return this.password;
    },
    set setPassword(password) {
        this.password = password;
    },
    get getWorker() {
        return this.worker;
    },
    set setWorker(worker) {
        this.worker = worker;
    },
    get getClient() {
        return this.client;
    },
    set setClient(client) {
        this.client = client;
    }
};

