package com.dota.chinanaive.entity;

import java.util.List;


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
    private List<Match> Matches;
    private int num_results;
    private int total_results;
    private int results_remaining;
  
    public int getStatus() {
      return status;
    }
  
    public void setStatus(int status) {
      this.status = status;
    }
  
    public List<Match> getMatches() {
      return Matches;
    }
  
    public void setMatches(List<Match> matches) {
      Matches = matches;
    }
  
    public static class Match {
      private long match_id;
      private long match_seq_num;
      private int radiant_team_id;
      private String radiant_name;
      private String radiant_logo;
      private int radiant_team_complete;
      private int radiant_guild_id;
      private String radiant_guild_name;
      private String radiant_guild_logo;
      private int dire_team_id;
      private String dire_name;
      private String dire_logo;
      private int dire_team_complete;
      private int dire_guild_id;
      private String dire_guild_name;
      private String dire_guild_logo;
      private long start_time;
      private int lobby_type;
      private int tower_status_radiant;
      private int tower_status_dire;
      private int barracks_status_radiant;
      private int barracks_status_dire;
      private int cluster;
      private int first_blood_time;
      private int human_players;
      private int leagueid;
      private int positive_votes;
      private int negative_votes;
      private int game_mode;
      private int engine;
      private boolean radiant_win;
      private int duration;
      private long radiant_captain;
      private long dire_captain;
      private List<BanPick> picks_bans;
      private List<Player> players;
  
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
  
      public List<Player> getPlayers() {
        return players;
      }
  
      public void setPlayers(List<Player> players) {
        this.players = players;
      }
  
      public static class BanPick {
        private boolean is_pick;
        private int hero_id;
        private int team;
        private int order;
        public boolean isIs_pick() {
          return is_pick;
        }
        public void setIs_pick(boolean is_pick) {
          this.is_pick = is_pick;
        }
        public int getHero_id() {
          return hero_id;
        }
        public void setHero_id(int hero_id) {
          this.hero_id = hero_id;
        }
        public int getTeam() {
          return team;
        }
        public void setTeam(int team) {
          this.team = team;
        }
        public int getOrder() {
          return order;
        }
        public void setOrder(int order) {
          this.order = order;
        }
      }
      
      public static class Player {
        private long account_id;
        private int player_slot;
        private int hero_id;
        private int item_0;
        private int item_1;
        private int item_2;
        private int item_3;
        private int item_4;
        private int item_5;
        private int kills;
        private int deaths;
        private int assists;
        private int leaver_status;
        private int gold;
        private int last_hits;
        private int denies;
        private int gold_per_min;
        private int xp_per_min;
        private int gold_spent;
        private int hero_damage;
        private int tower_damage;
        private int hero_healing;
        private int level;
        private List<AbilityUpgrade> ability_upgrades;
        private List<AdditionalUnits> additional_units;
        
        public static class AbilityUpgrade {
          private int ability;
          private int time;
          private int level;
          
          public int getAbility() {
            return ability;
          }
          public void setAbility(int ability) {
            this.ability = ability;
          }
          public int getTime() {
            return time;
          }
          public void setTime(int time) {
            this.time = time;
          }
          public int getLevel() {
            return level;
          }
          public void setLevel(int level) {
            this.level = level;
          }
        }
  
        public static class AdditionalUnits {
          private String unitname;
          private int item_0;
          private int item_1;
          private int item_2;
          private int item_3;
          private int item_4;
          private int item_5;
          
          public String getUnitname() {
            return unitname;
          }
          public void setUnitname(String unitname) {
            this.unitname = unitname;
          }
          public int getItem_0() {
            return item_0;
          }
          public void setItem_0(int item_0) {
            this.item_0 = item_0;
          }
          public int getItem_1() {
            return item_1;
          }
          public void setItem_1(int item_1) {
            this.item_1 = item_1;
          }
          public int getItem_2() {
            return item_2;
          }
          public void setItem_2(int item_2) {
            this.item_2 = item_2;
          }
          public int getItem_3() {
            return item_3;
          }
          public void setItem_3(int item_3) {
            this.item_3 = item_3;
          }
          public int getItem_4() {
            return item_4;
          }
          public void setItem_4(int item_4) {
            this.item_4 = item_4;
          }
          public int getItem_5() {
            return item_5;
          }
          public void setItem_5(int item_5) {
            this.item_5 = item_5;
          }
        }
        
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

        public int getItem_0() {
          return item_0;
        }

        public void setItem_0(int item_0) {
          this.item_0 = item_0;
        }

        public int getItem_1() {
          return item_1;
        }

        public void setItem_1(int item_1) {
          this.item_1 = item_1;
        }

        public int getItem_2() {
          return item_2;
        }

        public void setItem_2(int item_2) {
          this.item_2 = item_2;
        }

        public int getItem_3() {
          return item_3;
        }

        public void setItem_3(int item_3) {
          this.item_3 = item_3;
        }

        public int getItem_4() {
          return item_4;
        }

        public void setItem_4(int item_4) {
          this.item_4 = item_4;
        }

        public int getItem_5() {
          return item_5;
        }

        public void setItem_5(int item_5) {
          this.item_5 = item_5;
        }

        public int getKills() {
          return kills;
        }

        public void setKills(int kills) {
          this.kills = kills;
        }

        public int getDeaths() {
          return deaths;
        }

        public void setDeaths(int deaths) {
          this.deaths = deaths;
        }

        public int getAssists() {
          return assists;
        }

        public void setAssists(int assists) {
          this.assists = assists;
        }

        public int getLeaver_status() {
          return leaver_status;
        }

        public void setLeaver_status(int leaver_status) {
          this.leaver_status = leaver_status;
        }

        public int getGold() {
          return gold;
        }

        public void setGold(int gold) {
          this.gold = gold;
        }

        public int getLast_hits() {
          return last_hits;
        }

        public void setLast_hits(int last_hits) {
          this.last_hits = last_hits;
        }

        public int getDenies() {
          return denies;
        }

        public void setDenies(int denies) {
          this.denies = denies;
        }

        public int getGold_per_min() {
          return gold_per_min;
        }

        public void setGold_per_min(int gold_per_min) {
          this.gold_per_min = gold_per_min;
        }

        public int getXp_per_min() {
          return xp_per_min;
        }

        public void setXp_per_min(int xp_per_min) {
          this.xp_per_min = xp_per_min;
        }

        public int getGold_spent() {
          return gold_spent;
        }

        public void setGold_spent(int gold_spent) {
          this.gold_spent = gold_spent;
        }

        public int getHero_damage() {
          return hero_damage;
        }

        public void setHero_damage(int hero_damage) {
          this.hero_damage = hero_damage;
        }

        public int getTower_damage() {
          return tower_damage;
        }

        public void setTower_damage(int tower_damage) {
          this.tower_damage = tower_damage;
        }

        public int getHero_healing() {
          return hero_healing;
        }

        public void setHero_healing(int hero_healing) {
          this.hero_healing = hero_healing;
        }

        public int getLevel() {
          return level;
        }

        public void setLevel(int level) {
          this.level = level;
        }

        public List<AbilityUpgrade> getAbility_upgrades() {
          return ability_upgrades;
        }

        public void setAbility_upgrades(List<AbilityUpgrade> ability_upgrades) {
          this.ability_upgrades = ability_upgrades;
        }

        public List<AdditionalUnits> getAdditional_units() {
          return additional_units;
        }

        public void setAdditional_units(List<AdditionalUnits> additional_units) {
          this.additional_units = additional_units;
        }

      }

      public int getTower_status_radiant() {
        return tower_status_radiant;
      }

      public void setTower_status_radiant(int tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
      }

      public int getTower_status_dire() {
        return tower_status_dire;
      }

      public void setTower_status_dire(int tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
      }

      public int getBarracks_status_radiant() {
        return barracks_status_radiant;
      }

      public void setBarracks_status_radiant(int barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
      }

      public int getBarracks_status_dire() {
        return barracks_status_dire;
      }

      public void setBarracks_status_dire(int barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
      }

      public int getCluster() {
        return cluster;
      }

      public void setCluster(int cluster) {
        this.cluster = cluster;
      }

      public int getFirst_blood_time() {
        return first_blood_time;
      }

      public void setFirst_blood_time(int first_blood_time) {
        this.first_blood_time = first_blood_time;
      }

      public int getHuman_players() {
        return human_players;
      }

      public void setHuman_players(int human_players) {
        this.human_players = human_players;
      }

      public int getLeagueid() {
        return leagueid;
      }

      public void setLeagueid(int leagueid) {
        this.leagueid = leagueid;
      }

      public int getPositive_votes() {
        return positive_votes;
      }

      public void setPositive_votes(int positive_votes) {
        this.positive_votes = positive_votes;
      }

      public int getNegative_votes() {
        return negative_votes;
      }

      public void setNegative_votes(int negative_votes) {
        this.negative_votes = negative_votes;
      }

      public int getGame_mode() {
        return game_mode;
      }

      public void setGame_mode(int game_mode) {
        this.game_mode = game_mode;
      }

      public int getEngine() {
        return engine;
      }

      public void setEngine(int engine) {
        this.engine = engine;
      }

      public boolean getRadiant_win() {
        return radiant_win;
      }

      public void setRadiant_win(boolean radiant_win) {
        this.radiant_win = radiant_win;
      }

      public int getDuration() {
        return duration;
      }

      public void setDuration(int duration) {
        this.duration = duration;
      }

      public long getRadiant_captain() {
        return radiant_captain;
      }

      public void setRadiant_captain(long radiant_captain) {
        this.radiant_captain = radiant_captain;
      }

      public long getDire_captain() {
        return dire_captain;
      }

      public void setDire_captain(long dire_captain) {
        this.dire_captain = dire_captain;
      }

      public List<BanPick> getPicks_bans() {
        return picks_bans;
      }

      public void setPicks_bans(List<BanPick> picks_bans) {
        this.picks_bans = picks_bans;
      }

      public int getRadiant_team_id() {
        return radiant_team_id;
      }

      public void setRadiant_team_id(int radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
      }

      public int getDire_team_id() {
        return dire_team_id;
      }

      public void setDire_team_id(int dire_team_id) {
        this.dire_team_id = dire_team_id;
      }

      public String getRadiant_name() {
        return radiant_name;
      }

      public void setRadiant_name(String radiant_name) {
        this.radiant_name = radiant_name;
      }

      public String getRadiant_logo() {
        return radiant_logo;
      }

      public void setRadiant_logo(String radiant_logo) {
        this.radiant_logo = radiant_logo;
      }

      public int getRadiant_team_complete() {
        return radiant_team_complete;
      }

      public void setRadiant_team_complete(int radiant_team_complete) {
        this.radiant_team_complete = radiant_team_complete;
      }

      public String getDire_name() {
        return dire_name;
      }

      public void setDire_name(String dire_name) {
        this.dire_name = dire_name;
      }

      public String getDire_logo() {
        return dire_logo;
      }

      public void setDire_logo(String dire_logo) {
        this.dire_logo = dire_logo;
      }

      public int getDire_team_complete() {
        return dire_team_complete;
      }

      public void setDire_team_complete(int dire_team_complete) {
        this.dire_team_complete = dire_team_complete;
      }

      public int getRadiant_guild_id() {
        return radiant_guild_id;
      }

      public void setRadiant_guild_id(int radiant_guild_id) {
        this.radiant_guild_id = radiant_guild_id;
      }

      public String getRadiant_guild_name() {
        return radiant_guild_name;
      }

      public void setRadiant_guild_name(String radiant_guild_name) {
        this.radiant_guild_name = radiant_guild_name;
      }

      public String getRadiant_guild_logo() {
        return radiant_guild_logo;
      }

      public void setRadiant_guild_logo(String radiant_guild_logo) {
        this.radiant_guild_logo = radiant_guild_logo;
      }

      public int getDire_guild_id() {
        return dire_guild_id;
      }

      public void setDire_guild_id(int dire_guild_id) {
        this.dire_guild_id = dire_guild_id;
      }

      public String getDire_guild_name() {
        return dire_guild_name;
      }

      public void setDire_guild_name(String dire_guild_name) {
        this.dire_guild_name = dire_guild_name;
      }

      public String getDire_guild_logo() {
        return dire_guild_logo;
      }

      public void setDire_guild_logo(String dire_guild_logo) {
        this.dire_guild_logo = dire_guild_logo;
      }
  
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
  
  }
  
}
