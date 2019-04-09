package com.salon.service.auth;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.profile.ProfileBean;
import com.salon.repository.bean.worker.WorkerCreateUpdateBean;

public interface AuthService {
    AuthBean loginUser(String login, String password);

    boolean registerWorker(WorkerCreateUpdateBean createWorkerBean);

    boolean registerClient(ProfileBean profile);
}
