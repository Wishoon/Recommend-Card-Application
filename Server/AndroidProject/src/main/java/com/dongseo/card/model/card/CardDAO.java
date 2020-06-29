package com.dongseo.card.model.card;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongseo.card.model.user.UserMapper;
import com.dongseo.card.model.user.UserVO;

@Repository
public class CardDAO {
	@Autowired
	private SqlSession mybatis;
	
	public void insertCard(CardVO vo) {
		CardMapper mapper = mybatis.getMapper(CardMapper.class);
		mapper.insertCard(vo);
	}
	public List<CardVO> selectUserCard(String id) {
		CardMapper mapper = mybatis.getMapper(CardMapper.class);
		return mapper.selectUserCard(id);
	}
	public List<CardVO> selectCard() {
		CardMapper mapper = mybatis.getMapper(CardMapper.class);
		return mapper.selectCard();
	}
	public List<CardVO> selectStoreCard(String name, String id){
		CardMapper mapper = mybatis.getMapper(CardMapper.class);
		return mapper.selectStoreCard(name, id);
	}
	public List<CardVO> selectStore(){
		CardMapper mapper = mybatis.getMapper(CardMapper.class);
		return mapper.selectStore();
	}
}
