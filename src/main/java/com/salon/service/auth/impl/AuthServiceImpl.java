package com.salon.service.auth.impl;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.profile.ProfileBean;
import com.salon.repository.bean.salon.SalonBean;
import com.salon.repository.bean.skills.SkillsBean;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.entity.abstractEntity.AbstractUser;
import com.salon.service.auth.AuthService;
import com.salon.service.client.ClientService;
import com.salon.service.crypto.TokenCrypt;
import com.salon.service.exception.ErrorInfoExeption;
import com.salon.service.profile.ProfileService;
import com.salon.service.salon.SalonService;
import com.salon.service.skills.SkillsService;
import com.salon.service.worker.WorkerService;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

	private final static String ERROR_TYPE = "AUTHSERVICE.ERROR";
	
	@Autowired
	private TokenCrypt crypt;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private WorkerService workerService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private SalonService salonService;
	
	@Autowired
	private SkillsService skillsService;

	@Override
	public AuthBean loginUser(String login, String password) {
		LOGGER.debug("login start");

		AuthBean authBean = new AuthBean();

		if (StringUtils.isBlank(login) && StringUtils.isBlank(password)) {
			LOGGER.debug("login and password empty");
			throw new ErrorInfoExeption("login and password empty", "LOGIN_PASSWORD.EMPTY");
		}

		ProfileBean profileBean = new ProfileBean();
		profileBean.setLogin(login);
		profileBean.setPassword(password);
		
		List<ProfileBean> list = profileService.findByExample(profileBean);

		if (list.isEmpty()) {
			LOGGER.debug("Profile not found");
			throw new ErrorInfoExeption("Profile not found", "NOT_FOUND");
		}
		
		ProfileBean bean = list.get(0);
		if(crypt.getUserTokens().get(bean.getId()) == null) {
			
			if (bean.getClient() != null) {
				
				createToken(bean.getClient());
			} else {
				createToken(bean.getWorker());
				
			}
			
		}
		String token = crypt.getUserTokens().get(bean.getId());	
		
		if (bean.getClient() != null) {
			authBean.setUserId(bean.getClient().getId());
			authBean.setEnumRole(bean.getClient().getRole());
		} else {
			authBean.setUserId(bean.getWorker().getId());
			authBean.setEnumRole(bean.getWorker().getRole());
		}
		
		authBean.setUserName(bean.getName());
		authBean.setToken(token);

		LOGGER.debug("login finish");

		return authBean;
	}

	@Override
	public boolean registerWorker(ProfileBean profile) {
		LOGGER.debug("register start");

		validProfile(profile);

		SalonBean salon = salonService.findById(profile.getSalonId());
		
		//List<SkillsBean> skillsBean = skillsService.findAll();
		ProfileBean bean = profileService.save(profile);

		WorkerBean worker = new WorkerBean();
		worker.setProfile(profileService.toDomain(bean));
		worker.setRole(EnumRole.WORKER);
		worker.setStatus(EnumStatus.NOACTIVE);
		//worker.setSkillsList(skillsService.toDomainList(skillsBean));
		if (salon.getSalonId() != null) {
			worker.setSalon(salonService.toDomain(salon));
		}
		
		createToken(workerService.toDomain(worker));



		workerService.save(worker);
		// NEXT SEND EMAIL

		return true;
	}

	@Override
	public boolean registerClient(ProfileBean profile) {
		LOGGER.debug("register start");

		validProfile(profile);

		ProfileBean bean = profileService.save(profile);

		ClientBean client = new ClientBean();
		client.setProfile(profileService.toDomain(bean));
		client.setRole(EnumRole.CLIENT);
		client.setStatus(EnumStatus.NOACTIVE);
		
		createToken(clientService.toDomain(client));

		clientService.save(client);

		// NEXT SEND EMAIL

		return true;
	}

	private void validProfile(ProfileBean profileBean) {
		List<ProfileBean> list = profileService.findByExample(profileBean);

		if (!list.isEmpty()) {
			LOGGER.debug("Profile already exists");
			throw new ErrorInfoExeption("Profile already exists", "PROFILE_EXIST");
		}
	}
	private String createToken(AbstractUser user) {
		
		Long idUserProfile = user.getProfile().getProfileId(); //save token
		String role = user.getRole().name();
		String cryptToken = crypt.cryptToken(idUserProfile + " " + role);
		crypt.getUserTokens().put(idUserProfile, cryptToken);
		
		return cryptToken;
		
	}
}
