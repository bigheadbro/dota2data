package com.dota.chinanaive.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match.Player;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MatchHistory {
  private long match_id;
  private long match_seq_num;
  private long start_time;
  private int lobby_type;
  private String players;
  private boolean radiant_win;
  private int game_mode;

  public MatchHistory(Match match) {
    this.match_id = match.getMatch_id();
    this.match_seq_num = match.getMatch_seq_num();
    this.start_time = match.getStart_time();
    this.lobby_type = match.getLobby_type();
    this.radiant_win = match.getRadiant_win();
    this.setGame_mode(match.getGame_mode());
    
    try {
      this.players = buildPlayers(match.getPlayers());
    } catch (JsonGenerationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private String buildPlayers(List<Player> players) throws JsonGenerationException, JsonMappingException, IOException {
    final OutputStream out = new ByteArrayOutputStream();
    final ObjectMapper mapper = new ObjectMapper();

    mapper.writeValue(out, players);

    return out.toString();
    
  }
  
  public long getMatch_id() {
    return match_id;
  }

  public void setMatch_id(long match_id) {
    this.match_id = match_id;
  }

  public long getMatch_seq_num() {
    return match_seq_num;
  }

  public void setMatch_seq_num(long match_seq_num) {
    this.match_seq_num = match_seq_num;
  }

  public long getStart_time() {
    return start_time;
  }

  public void setStart_time(long start_time) {
    this.start_time = start_time;
  }

  public int getLobby_type() {
    return lobby_type;
  }

  public void setLobby_type(int lobby_type) {
    this.lobby_type = lobby_type;
  }

  public String getPlayers() {
    return players;
  }

  public void setPlayers(String players) {
    this.players = players;
  }

  public boolean isRadiant_win() {
    return radiant_win;
  }

  public void setRadiant_win(boolean radiant_win) {
    this.radiant_win = radiant_win;
  }

  public int getGame_mode() {
    return game_mode;
  }

  public void setGame_mode(int game_mode) {
    this.game_mode = game_mode;
  }
}
