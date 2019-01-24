package com.salon.service.auth;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.profile.ProfileBean;

public interface AuthService {
    AuthBean loginUser(String login, String password);

    boolean registerWorker(ProfileBean profile);

    boolean registerClient(ProfileBean profile);
}
