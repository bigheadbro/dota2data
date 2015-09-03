package com.dota.chinanaive.DAO;

import java.util.List;

import com.dota.chinanaive.entity.Hero;

public interface HeroDAO {
	public int insertHero(Hero hero);
	
	public List<Hero> getHeroes();
}
