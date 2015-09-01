package com.dota.chinanaive.entity;

public class Hero {
	private int id;
	private String name;
	private String name_ch;
	private String nickname_en;
	private String nickname_ch;
	
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
  public String getName_ch() {
    return name_ch;
  }
  public void setName_ch(String name_ch) {
    this.name_ch = name_ch;
  }
  public String getNickname_en() {
    return nickname_en;
  }
  public void setNickname_en(String nickname_en) {
    this.nickname_en = nickname_en;
  }
  public String getNickname_ch() {
    return nickname_ch;
  }
  public void setNickname_ch(String nickname_ch) {
    this.nickname_ch = nickname_ch;
  }
}
