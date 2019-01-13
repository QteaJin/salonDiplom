package com.salon.controller.skills;

import com.salon.repository.bean.skills.SkillsBean;
import com.salon.service.skills.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillsController {

    private SkillsService skillsService;

    @Autowired
    public void setSkillsService(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SkillsBean findById(@PathVariable("id") long id) {return skillsService.findById(id); }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<SkillsBean> findAllSkills() { return skillsService.findAll(); }

    @RequestMapping(method = RequestMethod.POST)
    public SkillsBean createSkills(@RequestBody SkillsBean skillsBean) { return skillsService.save(skillsBean); }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id) {
        skillsService.delete(skillsService.findById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public SkillsBean updateSkillsBean(@RequestBody SkillsBean skillsBean) {
        return skillsService.update(skillsBean);
    }

}
