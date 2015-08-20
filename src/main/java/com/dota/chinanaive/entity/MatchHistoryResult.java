package com.dota.chinanaive.entity;
public class MatchHistoryResult {
  private Result result;
  
  public Result getResult() {
    return result;
  }
  public void setResult(Result result) {
    this.result = result;
  }
  
  public static class Result {
    private int status;
    private int num_results;
    private int total_results;
    private int results_remaining;
    private Match[] Matches;
  
    public int getStatus() {
      return status;
    }
  
    public void setStatus(int status) {
      this.status = status;
    }
  
    public int getNum_results() {
      return num_results;
    }
  
    public void setNum_results(int num_results) {
      this.num_results = num_results;
    }
  
    public int getTotal_results() {
      return total_results;
    }
  
    public void setTotal_results(int total_results) {
      this.total_results = total_results;
    }
  
    public int getResults_remaining() {
      return results_remaining;
    }
  
    public void setResults_remaining(int results_remaining) {
      this.results_remaining = results_remaining;
    }
  
    public Match[] getMatches() {
      return Matches;
    }
  
    public void setMatches(Match[] matches) {
      Matches = matches;
    }
  
    public static class Match {
      private long match_id;
      private long match_seq_num;
      private long start_time;
      private int lobby_type;
      private long radiant_team_id;
      private long dire_team_id;
      private Player[] players;
  
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
  
      public long getRadiant_team_id() {
        return radiant_team_id;
      }
  
      public void setRadiant_team_id(long radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
      }
  
      public long getDire_team_id() {
        return dire_team_id;
      }
  
      public void setDire_team_id(long dire_team_id) {
        this.dire_team_id = dire_team_id;
      }
  
      public Player[] getPlayers() {
        return players;
      }
  
      public void setPlayers(Player[] players) {
        this.players = players;
      }
  
      public static class Player {
        private long account_id;
  
        private int player_slot;
  
        private int hero_id;
  
        public long getAccount_id() {
          return account_id;
        }
  
        public void setAccount_id(long account_id) {
          this.account_id = account_id;
        }
  
        public int getPlayer_slot() {
          return player_slot;
        }
  
        public void setPlayer_slot(int player_slot) {
          this.player_slot = player_slot;
        }
  
        public int getHero_id() {
          return hero_id;
        }
  
        public void setHero_id(int hero_id) {
          this.hero_id = hero_id;
        }
      }
  
    }
  
  }
  
}
