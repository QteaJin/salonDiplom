package com.salon.service.skills;

import java.util.List;

import com.salon.repository.bean.skills.SkillsBean;
import com.salon.repository.entity.skills.Skills;
import com.salon.service.CRUDService;

public interface SkillsService extends CRUDService<SkillsBean, Skills> {

	List<Skills> toDomainList(List<SkillsBean> skillsBean);

	
}
