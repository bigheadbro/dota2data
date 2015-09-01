package com.dota.chinanaive.DAO;

import java.util.List;

import com.dota.chinanaive.entity.MatchHistory;

public interface MatchHistoryDAO {

  public long insertMatchHistory(MatchHistory mh);

  public void updateMatchHistoryById(MatchHistory mh);
  
  public MatchHistory queryMatchHistoryBySeqnum(int seqnum);
  
  public List<MatchHistory> queryMatchHistoryByLobbyType(int type);
}
