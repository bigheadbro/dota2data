package com.dota.chinanaive.DAO.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.dota.chinanaive.DAO.MatchHistoryDAO;
import com.dota.chinanaive.entity.MatchHistory;

@Repository("mhDAO")
public class MatchHistoryDAOImpl extends SqlSessionDaoSupport implements MatchHistoryDAO {

  @Override
  public long insertMatchHistory(MatchHistory mh) {
    this.getSqlSession().insert("insertMatchHistory", mh);
    return mh.getMatch_id();
  }

	@Override
	public void updateMatchHistoryById(MatchHistory mh) {
		this.getSqlSession().update("updateMatchHistoryById", mh);
	}

	@Override
	public MatchHistory queryMatchHistoryBySeqnum(int seqnum) {
		return this.getSqlSession().selectOne("queryMatchHistoryBySeqnum",seqnum);
	}
	
	@Override
  public List<MatchHistory> queryMatchHistoryByLobbyType(int type) {
    return this.getSqlSession().selectList("queryMatchHistoryByLobbyType",type);
  }

}
