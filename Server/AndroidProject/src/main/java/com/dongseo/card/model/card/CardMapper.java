package com.dongseo.card.model.card;

import java.util.List;

public interface CardMapper {
	void insertCard(CardVO vo);
	List<CardVO> selectUserCard(String id);
	List<CardVO> selectCard();
	List<CardVO> selectStoreCard(String name, String id);
	List<CardVO> selectStore();
}
