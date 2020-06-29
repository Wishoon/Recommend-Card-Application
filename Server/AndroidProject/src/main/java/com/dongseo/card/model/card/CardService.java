package com.dongseo.card.model.card;

import java.util.List;

public interface CardService {
	public void insertCard(CardVO vo);
	public List<CardVO> selectUserCard(String id);
	public List<CardVO> selectCard();
	public List<CardVO> selectStoreCard(String name, String id);
	public List<CardVO> selectStore();
	
}
