package com.salon.repository.dao.skills;

import com.salon.repository.entity.skills.Skills;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsDAO extends JpaRepository<Skills, Long> {

	List<Skills> findAllByOrderByName();
}
