package com.dota.chinanaive.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.dota.chinanaive.entity.MatchHistoryResult;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {
  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

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
  }
  
}