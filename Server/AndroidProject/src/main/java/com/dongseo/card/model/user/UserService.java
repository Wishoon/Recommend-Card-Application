package com.dongseo.card.model.user;

public interface UserService {
	public void insertUser(UserVO vo);
	public UserVO getUser(String id, String password);
	public Boolean loginCheck(UserVO vo);
	public Boolean checkUserId(String id);
	public String money(String id);
	public void updateMoney(UserVO vo);
}
