package com.dongseo.card.model.user;


public interface UserMapper {
	void insertUser(UserVO vo);
	UserVO getUser(String id, String password);
	String loginCheck(UserVO vo);
	String checkUserId(String id);
	String money(String id);
	void updateMoney(UserVO vo);
}
