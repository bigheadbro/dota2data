package com.dota.chinanaive.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dota.chinanaive.entity.HeroRecord;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match.Player;

public class DataConvertUtils {
  public static boolean isRadiant(int slot) {
    if(slot < 5) {
      return true;
    }
    return false;
  }
  
  public  static Map<Integer, HeroRecord> getHeroFreqFromMH(long aid, List<Match> matches) {
    Map<Integer, HeroRecord> hrMap = new HashMap<Integer, HeroRecord>();
    for(int i = 0; i < matches.size(); i++) {
      Match match = matches.get(i);
      List<Player> players = match.getPlayers();
      for(int j = 0; j < players.size(); j++) {
        Player player = players.get(j);
        if(player.getAccount_id() == aid){
          HeroRecord hr = hrMap.get(player.getHero_id());
          if(hr == null) {
            hr = new HeroRecord();
            if(match.getRadiant_win() && isRadiant(player.getPlayer_slot())) {
              hr.setHeroid(player.getHero_id());
              hr.setWinCount(1);
              hr.setUseCount(1);
            } else if(!match.getRadiant_win() && !isRadiant(player.getPlayer_slot())) {
              hr.setHeroid(player.getHero_id());
              hr.setWinCount(1);
              hr.setUseCount(1);
            } else {
              hr.setHeroid(player.getHero_id());
              hr.setWinCount(0);
              hr.setUseCount(1);
            }
            hrMap.put(player.getHero_id(), hr);
          } else {
            if(match.getRadiant_win()  && isRadiant(player.getPlayer_slot())) {
              hr.setWinCount(hr.getWinCount() + 1);
              hr.setUseCount(hr.getUseCount() + 1);
            } else if(!match.getRadiant_win() && !isRadiant(player.getPlayer_slot())) {
              hr.setWinCount(hr.getWinCount() + 1);
              hr.setUseCount(hr.getUseCount() + 1);
            } else {
              hr.setUseCount(hr.getUseCount() + 1);
            }
          }
          break;
        }
      }
    }

    return hrMap;
  }

}
