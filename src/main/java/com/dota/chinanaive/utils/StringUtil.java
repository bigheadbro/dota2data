package com.dota.chinanaive.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.dota.chinanaive.entity.MatchHistory;
import com.dota.chinanaive.entity.MatchHistoryResult;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {
  public static void main(String[] args) {
//    try {
//      Class.forName("com.mysql.jdbc.Driver");
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//    }
//
//    try {
//      Connection connection = java.sql.DriverManager
//          .getConnection(
//              "jdbc:mysql://127.0.0.1/dota2data?useUnicode=true&amp;characterEncoding=utf-8",
//              "root", "123123");
//      ResultSet ret = connection.createStatement().executeQuery(
//          "select * from t_matchhistory limit 1");
//      ResultSetMetaData meta = ret.getMetaData();
//      int count = meta.getColumnCount();
//      System.out.println(count);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
  	long id = 1373091499;
    while(true) {
      System.out.println("get data:"+id);
      String strUrl = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v1/?key=76707E31AEC98FF8CAECE7E45677CD5D&start_at_match_seq_num=" + id;
      
      try
      {
          URL url = new URL(strUrl);
          HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
          System.out.println("lalalala");
          httpConn.setConnectTimeout(10000);
          httpConn.setDoInput(true);
          httpConn.setRequestMethod("GET");
          System.out.println("send request");
  
          int respCode = httpConn.getResponseCode();
          System.out.println("get response");
          if (respCode == 200)
          {
          	File file = new File("D:\\data\\"+id+".json");
          	OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = httpConn.getInputStream().read(buffer, 0, 8192)) != -1) {
               os.write(buffer, 0, bytesRead);
            }
            os.close();
          } else {
            System.out.println("response error");
          }
          System.out.println("finish");
      }
      catch (SocketTimeoutException e) {
        System.out.println("getMatchHistory timeout");
      }
      catch (MalformedURLException e)
      {
        System.out.println("getMatchHistory exception");
      }
      catch (IOException e)
      {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
    }
  }
  
}