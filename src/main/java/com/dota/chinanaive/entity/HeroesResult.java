package com.dota.chinanaive.entity;

import java.util.List;

public class HeroesResult {
private Result result;
  
  public Result getResult() {
    return result;
  }
  public void setResult(Result result) {
    this.result = result;
  }
  

  public static class Result {
  	private List<Hero> heroes;
  	private int status;
  	private int count;
  	
  	public static class Hero {
  		private int id;
  		private String name;
  		private String localized_name;
  		
  		public int getId() {
  			return id;
  		}
  		public void setId(int id) {
  			this.id = id;
  		}
  		public String getName() {
  			return name;
  		}
  		public void setName(String name) {
  			this.name = name;
  		}
  		public String getLocalized_name() {
  			return localized_name;
  		}
  		public void setLocalized_name(String localized_name) {
  			this.localized_name = localized_name;
  		}
  	}

		public List<Hero> getHeroes() {
			return heroes;
		}

		public void setHeroes(List<Hero> heroes) {
			this.heroes = heroes;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
  }
}
