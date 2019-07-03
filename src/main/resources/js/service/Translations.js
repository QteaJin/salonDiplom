    function translateRU (status) {
    switch (status) {
    case "ACTIVE":
	    return "АКТИВНЫЙ";
	case "NOACTIVE":
	    return "ЗАБЛОКИРОВАН";
    case "DONE":
	    return "ВЫПОЛНЕН";
    case "CANCELED":
	    return "ОТМЕНЕН";
    case "NEW":
	    return "НОВЫЙ";
    case "FREE":
	    return "СВОБОДНО";
    case "RESERVED":
	    return "ЗАРЕЗЕРВИРОВАНО";
    case "ALL":
	    return "ВСЕ";

    }
	}	

	    function translateEN (status) {
    switch (status) {
    case "АКТИВНЫЙ":
	    return "ACTIVE";
	case "ЗАБЛОКИРОВАН":
	    return "NOACTIVE";
    case "ВЫПОЛНЕН":
	    return "DONE";
    case "ОТМЕНЕН":
	    return "CANCELED";
    case "НОВЫЙ":
	    return "NEW";
    case "СВОБОДНО":
	    return "FREE";
    case "ЗАРЕЗЕРВИРОВАНО":
	    return "RESERVED";
    case "ВСЕ":
	    return "ALL";

    }
	}