'use strict';

var EmailBean = {
    
	from: String,
	to: String,
	subject: String,
	message: String,
	isHtml: String,
   

    
    get getFrom() {
        return this.from;
    },
    set getFrom(from) {
        this.from = from;
    },
    get getTo() {
        return this.to;
    },
    set setTo(to) {
        this.to = to;
    },
    get getSubject() {
        return this.subject;
    },
    set setSubject(subject) {
        this.subject = subject;
    },
    get getMessage() {
        return this.message;
    },
    set setMessage(message) {
        this.message = message;
    },
    get getIsHtml() {
        return this.isHtml;
    },
    set setIsHtml(isHtml) {
        this.isHtml = isHtml;
    }
};

