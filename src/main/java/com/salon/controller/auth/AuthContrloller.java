package com.salon.controller.auth;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.profile.ProfileBean;
import com.salon.repository.bean.worker.WorkerCreateUpdateBean;
import com.salon.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthContrloller {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthBean loginUser(@RequestParam(name = "login") String login,
                              @RequestParam(name = "password") String password) {

        return authService.loginUser(login, password);
    }

    @RequestMapping(value = "/registr/worker", method = RequestMethod.POST)
    public boolean registerWorker (@RequestBody WorkerCreateUpdateBean createUpdateBean) {
        return authService.registerWorker(createUpdateBean);
    }

    @RequestMapping(value = "/registr/client", method = RequestMethod.POST)
    public boolean registerClient (@RequestBody ProfileBean profile) {
        return authService.registerClient(profile);
    }
}
