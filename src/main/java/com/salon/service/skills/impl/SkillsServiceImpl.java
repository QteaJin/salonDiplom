package com.salon.service.skills.impl;

import com.salon.repository.bean.skills.SkillsBean;
import com.salon.repository.bean.skills.SkillsBeanSimple;
import com.salon.repository.dao.skills.SkillsDAO;
import com.salon.repository.entity.skills.Skills;
import com.salon.service.skills.SkillsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillsServiceImpl.class);

    @Autowired
    private SkillsDAO skillsDAO;

    public void setSkillsDAO(SkillsDAO skillsDAO) {
        this.skillsDAO = skillsDAO;
    }

    @Override
    public SkillsBean save(SkillsBean bean) {
        LOGGER.debug("Skills save start");

        Skills skills = skillsDAO.save(toDomain(bean));

        LOGGER.debug("Skills save finish");

        return toBean(skills);
    }

    @Override
    public List<SkillsBean> findAll() {
        LOGGER.debug("Skills find All");

        List<Skills> skills = skillsDAO.findAll();

        LOGGER.debug("Skills find All finish");
        return toBean(skills);
    }

    @Override
    public SkillsBean findById(Long id) {
        LOGGER.debug("Skills find By id");

        Optional<Skills> skill = skillsDAO.findById(id);

        LOGGER.debug("Skills find By id");
        return toBean(skill.get());
    }

    @Override
    public SkillsBean update(SkillsBean bean) {
        LOGGER.debug("update Skills start");

        Skills skills = skillsDAO.saveAndFlush(toDomain(bean));

        LOGGER.debug("update Skills finish");
        return toBean(skills);
    }

    @Override
    public void delete(SkillsBean bean) {
        LOGGER.debug("delete Skills start");

        skillsDAO.delete(toDomain(bean));

        LOGGER.debug("delete Skills finish");

    }

    @Override
    public List<SkillsBean> findByExample(SkillsBean bean) {
        LOGGER.debug("find By Example Skills start");

        List<Skills> skills=skillsDAO.findAll(Example.of(toDomain(bean)));

        LOGGER.debug("delete Skills finish");
        return toBean(skills);
    }

    @Override
    public SkillsBean toBean(Skills domain) {
        SkillsBean skillsBean = new SkillsBean();
        skillsBean.setSkillsId(domain.getSkillsId());
        skillsBean.setName(domain.getName());
        skillsBean.setCatalogList(domain.getCatalogList());
        skillsBean.setWorkers(domain.getWorkers());
        return skillsBean;
    }

    @Override
    public Skills toDomain(SkillsBean bean) {
        Skills skills = new Skills();
        skills.setSkillsId(bean.getSkillsId());
        skills.setName(bean.getName());
        skills.setCatalogList(bean.getCatalogList());
        skills.setWorkers(bean.getWorkers());
        return skills;
    }

    private List<SkillsBean> toBean(List<Skills> skills) {
        List<SkillsBean> beans = new ArrayList<>();
        for (Skills skill : skills) {
            beans.add(toBean(skill));
        }
        return beans;
    }
    
   
    public List<Skills> toDomainList(List<SkillsBean> skills) {
        List<Skills> beans = new ArrayList<>();
        for (SkillsBean skill : skills) {
            beans.add(toDomain(skill));
        }
        return beans;
    }

	@Override
	public List<SkillsBeanSimple> getAllSkills() {
		List<SkillsBeanSimple> skillsBeanSimples = new ArrayList<SkillsBeanSimple>();;
		List<SkillsBean> skillsBeans = new ArrayList<SkillsBean>();
		skillsBeans = findAll();
		if (!skillsBeans.isEmpty()) {
		 			
			for (SkillsBean skillsBean : skillsBeans) {
				SkillsBeanSimple beanSimple = new SkillsBeanSimple();
				beanSimple.setSkillsId(skillsBean.getSkillsId());
				beanSimple.setName(skillsBean.getName());
				skillsBeanSimples.add(beanSimple);
			}
		}
		
		return skillsBeanSimples;
	}

	@Override
	public SkillsBeanSimple createEditSkill(SkillsBeanSimple beanSimple) {
		SkillsBean skillsBean = new SkillsBean();
		
		if (beanSimple.getSkillsId() != null) {
			skillsBean = findById(beanSimple.getSkillsId());
			skillsBean.setName(beanSimple.getName());
			update(skillsBean);
			return beanSimple;
		}
				
		skillsBean.setName(beanSimple.getName());
		save(skillsBean);
		
		return beanSimple;
	}

	@Override
	public List<SkillsBean> findAllSkillsAndCatalogs() {
		List<SkillsBean> beans = toBean( skillsDAO.findAllByOrderByName());
		for (int i = 0; i < beans.size(); i++) {
			beans.get(i).setWorkers(null);
		}
		
		return beans;
	}
}
