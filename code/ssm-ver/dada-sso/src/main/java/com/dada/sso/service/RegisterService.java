package com.dada.sso.service;

import com.dada.pojo.OrganizationCategory;
import com.dada.pojo.UserInfo;

public interface RegisterService {
	
	public String registerUser(UserInfo userInfo);
	
	public String registerOrganization(OrganizationCategory category);
	
}
