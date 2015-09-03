package com.dota.chinanaive.DAO.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.dota.chinanaive.DAO.HeroDAO;
import com.dota.chinanaive.entity.Hero;

@Repository("heroDAO")
public class HeroDAOImpl extends SqlSessionDaoSupport implements HeroDAO {

	@Override
	public int insertHero(Hero hero) {
		this.getSqlSession().insert("insertHero", hero);
    return hero.getId();
	}

	@Override
	public List<Hero> getHeroes() {
		return this.getSqlSession().selectList("queryHeroes");
	}

}
