package com.salon.repository.dao.profile;

import com.salon.repository.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDAO extends JpaRepository<Profile, Long> {
}
