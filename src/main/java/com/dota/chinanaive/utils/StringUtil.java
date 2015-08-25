package com.dota.chinanaive.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dota.chinanaive.DAO.MatchHistoryDAO;
import com.dota.chinanaive.entity.MatchHistory;
import com.dota.chinanaive.entity.MatchHistoryResult;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {
  @Autowired
  public static void main(String[] args) {
    long id = 1373094316;
    while (true) {
      System.out.println("get data:" + id);
      String strUrl = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v1/?key=76707E31AEC98FF8CAECE7E45677CD5D&start_at_match_seq_num="
          + id + "&matches_requested=5";

      try {
        URL url = new URL(strUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        System.out.println("lalalala");
        httpConn.setConnectTimeout(10000);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("GET");
        System.out.println("send request");

        int respCode = httpConn.getResponseCode();
        System.out.println("get response");
        if (respCode == 200) {
          System.out.println("in");
          //String jsonStr = "";
          StringBuilder content = new StringBuilder();
          System.out.println("111");
          
          try {
            System.out.println("333");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
              content.append(line + "\n");
            }
            bufferedReader.close();
            System.out.println("444");

          } catch (IOException e) {
            System.out.println("error occured when convert stream to json");
          } 
          
          System.out.println("out");
          ObjectMapper mapper = new ObjectMapper();
          System.out.println("begin to read");
          MatchHistoryResult result = mapper.readValue(content.toString(),
              MatchHistoryResult.class);
          if (result != null) {
            List<Match> matches = result.getResult().getMatches();
            System.out.println(id + ":" + matches.size());
            for (int i = 0; i < matches.size(); i++) {
              MatchHistory mh = new MatchHistory(matches.get(i));
              if (i != matches.size() - 1) {
                try {
                  Connection connection = java.sql.DriverManager
                      .getConnection(
                          "jdbc:mysql://127.0.0.1/dota2data?useUnicode=true&amp;characterEncoding=utf-8",
                          "root", "123123");
                  ResultSet ret = connection.createStatement().executeQuery(
                      "select * from t_matchhistory limit 1");
                  ResultSetMetaData meta = ret.getMetaData();
                  int count = meta.getColumnCount();
                  System.out.println(count);
                } catch (SQLException e) {
                  e.printStackTrace();
                }
              } else {
                id = mh.getMatch_seq_num();
              }
            }
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

}