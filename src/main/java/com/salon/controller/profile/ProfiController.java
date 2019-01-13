package com.salon.controller.profile;

import com.salon.repository.bean.profile.ProfileBean;
import com.salon.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prof")
public class ProfiController {

    private ProfileService profileService;

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProfileBean findById(@PathVariable("id") long id) {return profileService.findById(id); }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProfileBean> findAllProfile() { return profileService.findAll(); }

    @RequestMapping(method = RequestMethod.POST)
    public ProfileBean createProfile(@RequestBody ProfileBean profileBean) { return profileService.save(profileBean); }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id) {
        profileService.delete(profileService.findById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ProfileBean updateProfile(@RequestBody ProfileBean profileBean) {
        return profileService.update(profileBean);
    }
}
