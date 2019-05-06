package com.salon.utility;

public class Const {
    public static class GoogleApi {
        public static final String URL_GEOCODING_API = "https://www.googleapis.com/geolocation/v1/geolocate?key=";
        public static final String URL_GOOGLELOCATION = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";
    }

    public static class MyEmail {
        public static final String MY_EMAIL = "saloncloud.info@gmail.com";
    }

    public static class Exception {
        public static class Code {
            public static final String NOT_FOUND = "NOT_FOUND";
            public static final String NOT_EMPTY = "NOT_EMPTY";
        }
    }
    
    public static class SalonShedule {
        public static final int START_WORK = 10-3; //10-00
        public static final int END_WORK = 20-3; // 20-00
    }
    public static class Millis{
    	 public static final long MILLIS_IN_HOUR = 3600000;
    	 public static final long MILLIS_IN_HALF_HOUR = 3600000/2;
    	 public static final long MILLIS_IN_MINUTE = 60000;
    }
}
