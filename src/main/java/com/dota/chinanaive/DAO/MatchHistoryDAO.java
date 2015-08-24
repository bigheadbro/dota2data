package com.dota.chinanaive.DAO;

import com.dota.chinanaive.entity.MatchHistory;

public interface MatchHistoryDAO {

  public long insertMatchHistory(MatchHistory mh);

  public void updateMatchHistoryById(MatchHistory mh);
  
  public MatchHistory queryMatchHistoryBySeqnum(int seqnum);
}
