package com.dongseo.card.model.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	CardDAO cardDAO;
	@Override
	public void insertCard(CardVO vo) {
		cardDAO.insertCard(vo);
	}
	@Override
	public List<CardVO> selectCard() {
		// TODO Auto-generated method stub
		return cardDAO.selectCard();
	}@Override
	public List<CardVO> selectUserCard(String id) {
		// TODO Auto-generated method stub
		return cardDAO.selectUserCard(id);
	}
	@Override
	public List<CardVO> selectStore() {
		// TODO Auto-generated method stub
		return cardDAO.selectStore();
	}
	@Override
	public List<CardVO> selectStoreCard(String name, String id) {
		// TODO Auto-generated method stub
		return cardDAO.selectStoreCard(name, id);
	}
}
