'use strict';

var CatalogBean = {
		catalogId: Number,
	    name: String,
	    description: String,
	    price: Number,
	    timeLead: Number,
	    skillId: Number,
		
		get getCatalogId() {
	        return this.catalogId;
	    },
	    set setCatalogId(catalogId) {
	        this.catalogId = catalogId;
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
	    get getPrice() {
	        return this.price;
	    },
	    set setPrice(price) {
	        this.price = price;
	    },
	    get getTimeLead() {
	        return this.timeLead;
	    },
	    set setTimeLead(timeLead) {
	        this.timeLead = timeLead;
	    },
	    get getSkillId() {
	        return this.skillId;
	    },
	    set setSkillId(skillId) {
	        this.skillId = skillId;
	    }
	    
	};