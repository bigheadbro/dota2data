package com.dota.chinanaive.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringUtil {
	
	public static Integer getStartMatchId(Integer start, Integer end) {
		Integer id = 0;
		try {
      Connection connection = java.sql.DriverManager
          .getConnection(
              "jdbc:mysql://127.0.0.1/dota2data?useUnicode=true&amp;characterEncoding=utf-8",
              "root", "123123");

      PreparedStatement stmt = 
          connection.prepareStatement("select max(match_seq_num) as maxid from t_matchhistory where match_seq_num > " + start.toString() + " and match_seq_num <= " + end.toString());
      
      ResultSet rs = stmt.executeQuery();
      if(rs.next())
      {
      	id=rs.getInt("maxid");
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
		return id;
	}
	
  public static void main(String[] args) {
  	Integer startid = getStartMatchId(1501000000, 1502000000) + 1;
    Runnable match1 = new MatchUtil(startid,1502000000);
    Thread thread1 = new Thread(match1);
    thread1.setName("match141");
    System.out.println("Starting match141 thread...");
    thread1.start();
    
    startid = getStartMatchId(1502000000, 1503000000) + 1;
    Runnable match2 = new MatchUtil(startid,1503000000);
    Thread thread2 = new Thread(match2);
    thread2.setName("match142");
    System.out.println("Starting match142 thread...");
    thread2.start();
    
    startid = getStartMatchId(1503000000, 1504000000) + 1;
    Runnable match3 = new MatchUtil(startid,1504000000);
    Thread thread3 = new Thread(match3);
    thread3.setName("match143");
    System.out.println("Starting match143 thread...");
    thread3.start();
    
    startid = getStartMatchId(1504000000, 1505000000) + 1;
    Runnable match4 = new MatchUtil(startid,1505000000);
    Thread thread4 = new Thread(match4);
    thread4.setName("match144");
    System.out.println("Starting match144 thread...");
    thread4.start();
    
    startid = getStartMatchId(1505000000, 1506000000) + 1;
    Runnable match5 = new MatchUtil(startid,1506000000);
    Thread thread5 = new Thread(match5);
    thread5.setName("match145");
    System.out.println("Starting match145 thread...");
    thread5.start();
    
    startid = getStartMatchId(1506000000, 1507000000) + 1;
    Runnable match6 = new MatchUtil(startid,1507000000);
    Thread thread6 = new Thread(match6);
    thread6.setName("match146");
    System.out.println("Starting match146 thread...");
    thread6.start();
    
    startid = getStartMatchId(1507000000, 1508000000) + 1;
    Runnable match7 = new MatchUtil(startid,1508000000);
    Thread thread7 = new Thread(match7);
    thread7.setName("match147");
    System.out.println("Starting match147 thread...");
    thread7.start();
    
    startid = getStartMatchId(1508000000, 1509000000) + 1;
    Runnable match8 = new MatchUtil(startid,1509000000);
    Thread thread8 = new Thread(match8);
    thread8.setName("match148");
    System.out.println("Starting match148 thread...");
    thread8.start();
    
    startid = getStartMatchId(1509000000, 1510000000) + 1;
    Runnable match9 = new MatchUtil(startid,1510000000);
    Thread thread9 = new Thread(match9);
    thread9.setName("match149");
    System.out.println("Starting match149 thread...");
    thread9.start();
    
    
  }

}