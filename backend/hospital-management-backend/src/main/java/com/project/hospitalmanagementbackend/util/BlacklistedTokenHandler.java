package com.project.hospitalmanagementbackend.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class BlacklistedTokenHandler {

	Map<String,Boolean> tokenList=new HashMap<String, Boolean>();
	
	public void unsetBlacklistToken(String token)
	{
		
			tokenList.put(token, false);
	}
	
	public void setBlacklistToken(String token)
	{
		
			tokenList.put(token,true);

		
	}
	
	public boolean isTokenBlacklisted(String token)
	{
		if(tokenList.containsKey(token))
		{
			return tokenList.get(token);
		}
		return false;
	}
}
