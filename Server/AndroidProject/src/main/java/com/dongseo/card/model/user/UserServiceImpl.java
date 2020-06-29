package com.dongseo.card.model.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;

	@Override
	public void insertUser(UserVO vo) {
		userDAO.InsertUser(vo);
		
	}

	@Override
	public UserVO getUser(String id, String password) {
		// TODO Auto-generated method stub
		return userDAO.getUser(id, password);
	}
	@Override
	public Boolean loginCheck(UserVO vo) {
		// TODO Auto-generated method stub
		
		return userDAO.loginCheck(vo);
	}
	@Override
	public Boolean checkUserId(String id) {
		// TODO Auto-generated method stub
		return userDAO.chekcUserId(id);
	}@Override
	public String money(String id) {
		// TODO Auto-generated method stub
		return userDAO.money(id);
	}@Override
	public void updateMoney(UserVO vo) {
		// TODO Auto-generated method stub
		userDAO.updateMoney(vo);
	}
	
}
