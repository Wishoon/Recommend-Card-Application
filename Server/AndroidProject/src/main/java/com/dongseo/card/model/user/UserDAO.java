package com.dongseo.card.model.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession mybatis;
	
	public void InsertUser(UserVO vo) {
		UserMapper mapper = mybatis.getMapper(UserMapper.class);
		mapper.insertUser(vo);
	}
	public UserVO getUser(String id, String password) {
		UserMapper mapper = mybatis.getMapper(UserMapper.class);
		return mapper.getUser(id,password);
	}
	public Boolean loginCheck(UserVO vo) {
		UserMapper mapper = mybatis.getMapper(UserMapper.class);
		String name = mapper.loginCheck(vo);
		return name == null ? false : true;
	}
	public Boolean chekcUserId(String id) {
		UserMapper mapper = mybatis.getMapper(UserMapper.class);
		String name = mapper.checkUserId(id);
		return name == null ? false : true;
	}
	public String money(String id) {
		UserMapper mapper = mybatis.getMapper(UserMapper.class);
		String money = mapper.money(id);
		return money;
	}
	public void updateMoney(UserVO vo) {
		UserMapper mapper = mybatis.getMapper(UserMapper.class);
		mapper.updateMoney(vo);
	}
}
