package com.App.weatherTracker.model.serviceImpl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.weatherTracker.model.bo.User;
import com.App.weatherTracker.model.request.UserRequest;
import com.App.weatherTracker.model.response.UserResponse;
import com.App.weatherTracker.model.service.UserService;
import com.App.weatherTracker.repository.UserRespository;

@Service
public class UserServiceImpl  implements UserService {

	@Autowired
	UserRespository userRepo;
	
	@Transactional
	@Override
	public UserResponse createUser(UserRequest req) throws Exception {
    	UserResponse response = new UserResponse();
		User user = userRepo.findByUserNameAndIsActive(req.getUsername(), (byte) 1);
        if(user==null) {
        	User newUser = new User();
        	newUser.setUserName(req.getUsername());
        	newUser.setPassword(req.getPassword());
        	newUser.setIsActive((byte) 1);
            Date date = new Date();  
        	newUser.setModifiedOn(date);
        	newUser.setCreatedOn(date);
        	newUser.setModifiedBy(-1l);
        	newUser.setCreatedBy(-1l);
        	newUser = userRepo.save(newUser);
        	
        	response.setUserId(newUser.getUserId());
        	response.setUserName(newUser.getUserName());
        	response.setPassword(newUser.getPassword());
        	response.setIsActive((byte) 1);
        } else {
            throw new Exception("Already User Exists!");  
        }
    	return response;
	}

	@Override
	public UserResponse validateUser(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		User user = userRepo.findByUserNameAndIsActive(userName, (byte) 1);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				UserResponse res = new UserResponse();
				res.setUserId(user.getUserId());
				return res;
			} else {
	            throw new Exception("InValid Password");  
			}
		} else {
            throw new Exception("InValid UserName");  
		}
	}

}
