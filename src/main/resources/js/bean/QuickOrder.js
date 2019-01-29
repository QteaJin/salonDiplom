'use strict';

var QuickOrderBean = {
	    name: String,
	    phone: String,
	    email: String,
	    description : String,
	    dateCreate: String,
	    
	        
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
	    get getDescription() {
	        return this.description;
	    },
	    set setDescription(description) {
	        this.description = description;
	    },
	    get getDateCreate() {
	        return this.dateCreate;
	    },
	    set setDateCreate(dateCreate) {
	        this.dateCreate = dateCreate;
	    }
	};