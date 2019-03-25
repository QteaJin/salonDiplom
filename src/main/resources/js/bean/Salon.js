'use strict';

var SalonBean = {
		id: Number,
	    name: String,
	    description: String,
	    country: String,
		city: String,
		street: String,
		
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
	    get getDescription() {
	        return this.description;
	    },
	    set setDescription(description) {
	        this.description = description;
	    },
	    get getCountry() {
	        return this.country;
	    },
	    set setCountry(country) {
	        this.country = country;
	    },
	    get getCity() {
	        return this.city;
	    },
	    set setCity(city) {
	        this.city = city;
	    },
	    get getStreet() {
	        return this.street;
	    },
	    set setStreet(street) {
	        this.street = street;
	    }
	    
	};