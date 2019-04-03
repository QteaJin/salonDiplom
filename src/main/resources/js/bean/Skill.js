'use strict';

var SkillBean = {
    
	skillsId: Number,
	name: String,
	    
    get getId() {
        return this.skillsId;
    },
    set setId(skillsId) {
        this.skillsId = skillsId;
    },
    get getName() {
        return this.name;
    },
    set setName(name) {
        this.name = name;
    }
};

