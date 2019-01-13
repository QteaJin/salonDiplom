package com.salon.service.google;

import com.salon.repository.bean.adress.AdressBean;

public interface GoogleLocationService {
    AdressBean receiveAddressFromGoogle();
}
