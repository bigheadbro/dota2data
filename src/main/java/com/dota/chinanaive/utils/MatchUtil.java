package com.dota.chinanaive.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.dota.chinanaive.entity.MatchHistory;
import com.dota.chinanaive.entity.MatchHistoryResult;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MatchUtil implements Runnable {
  private long begin_num;
  private long end_num;
  
  public MatchUtil(long begin_num, long end_num) {
    super();
    this.begin_num = begin_num;
    this.end_num = end_num;
  }
 
  @Override
  public void run() {
    long id = begin_num + 1;
    
    while (true && id <= end_num) {
      System.out.println("get data:" + id);
      String strUrl = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum"
          + "/v1/?key=76707E31AEC98FF8CAECE7E45677CD5D&start_at_match_seq_num="
          + id + "&matches_requested=1";

      try {
        URL url = new URL(strUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setConnectTimeout(0);
        httpConn.setReadTimeout(0);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("GET");

        int respCode = httpConn.getResponseCode();
        if (respCode == 200) {
          StringBuilder content = new StringBuilder();
          
          try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
              content.append(line + "\n");
            }
            bufferedReader.close();

          } catch (IOException e) {
            System.out.println("error occured when convert stream to json");
          } 
          
          ObjectMapper mapper = new ObjectMapper();
          MatchHistoryResult result = mapper.readValue(content.toString(),
              MatchHistoryResult.class);
          if (result != null) {
            List<Match> matches = result.getResult().getMatches();
            System.out.println(id);
            MatchHistory mh = new MatchHistory(matches.get(0));
            try {
              Connection connection = java.sql.DriverManager
                  .getConnection(
                      "jdbc:mysql://127.0.0.1/dota2data?useUnicode=true&amp;characterEncoding=utf-8",
                      "root", "123123");

              PreparedStatement stmt = 
                  connection.prepareStatement("INSERT INTO t_matchhistory(match_id,match_seq_num,start_time,game_mode"
                      + ",lobby_type,radiant_win,players,heroes) VALUES (?, ?, ?,?,?,?,?,?)");

              stmt.setLong(1, mh.getMatch_id());
              stmt.setLong(2, mh.getMatch_seq_num());
              stmt.setLong(3, mh.getStart_time());
              stmt.setInt(4, mh.getGame_mode());
              stmt.setInt(5, mh.getLobby_type());
              stmt.setBoolean(6, mh.isRadiant_win());
              stmt.setString(7, mh.getPlayers());

              String heroes = "";
              ObjectMapper heroMap = new ObjectMapper();
              List<Player> players = heroMap.readValue(
                  mh.getPlayers(),
                  heroMap.getTypeFactory().constructCollectionType(
                          List.class, Player.class));
              for(int j = 0;j<players.size();j++) {
              	if(j == 4) {
              		heroes = heroes + players.get(j).getHero_id() + "|";
              	} else if(j != players.size() - 1) {
                  heroes = heroes + players.get(j).getHero_id() + ",";
                } else {
                  heroes = heroes + players.get(j).getHero_id();
                }
              }
              if(heroes.split(",").length == 2) {
              	heroes = heroes.replace(',', '|');
              }
              stmt.setString(8, heroes);
              
              stmt.execute();
              connection.close();
            } catch (SQLException e) {
              e.printStackTrace();
            }
            id = mh.getMatch_seq_num() + 1;
          }
          
          System.out.println("finish to read");
        } else {
          System.out.println("response error");
        }
        System.out.println("finish");

      } catch (SocketTimeoutException e) {
        System.out.println("getMatchHistory timeout");
      } catch (MalformedURLException e) {
        System.out.println("getMatchHistory exception");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public long getBegin_num() {
    return begin_num;
  }

  public void setBegin_num(long begin_num) {
    this.begin_num = begin_num;
  }

  public long getEnd_num() {
    return end_num;
  }

  public void setEnd_num(long end_num) {
    this.end_num = end_num;
  }

}
