package com.dota.chinanaive.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.dota.chinanaive.entity.MatchHistoryResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataService {

  /** STEAM�����ļ� */
  public final static String STEAM_PROPERTIES_FILENAME = "/steamapi.properties";

  /** �����ļ�·�� */
  private String propertiesPath;

  private String access_key;

  /** Ĭ�������ļ� */
  private Properties defaultProperties;

  /** �����ļ� */
  private Properties properties;

  public DataService() {
    this(STEAM_PROPERTIES_FILENAME);
  }

  public DataService(String propertiesPath) {
    this.propertiesPath = propertiesPath;
    InputStream in = null;
    try {
      // load default config
      URL url = this.getClass().getResource(STEAM_PROPERTIES_FILENAME);
      in = url.openStream();
      defaultProperties = new Properties();
      defaultProperties.load(in);
      in.close();
      // load user config
      url = this.getClass().getResource(propertiesPath);

      if (url == null) {
        throw new RuntimeException("no config file: " + propertiesPath);
      }
      in = url.openStream();
      properties = new Properties();
      properties.load(in);
      in.close();
      // read config
      access_key = getProperty("access_key", null, false);

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e.getMessage(), e);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  protected String getProperty(String key, String defaultValue,
      boolean allowNull) {
    // ���ȴ��û������ļ���ȡ����ȡ��ΪNULLʱ�ٴ�Ĭ�����ö�ȡ
    Properties pro;
    if (properties.containsKey(key)) {
      pro = properties;
    } else {
      pro = defaultProperties;
    }
    // ��ȡ����ֵ
    String value = pro.getProperty(key);
    if (value == null) {
      value = defaultValue;
    }
    if (value == null) {
      if (!allowNull) {
        throw new RuntimeException("�����ļ���" + propertiesPath + "��ȱ�����ԣ�key="
            + key);
      }
      return null;
    }
    return value.trim();
  }

  protected String replaceAccessKey(String url) {
    if (url.indexOf("ACCESS_KEY") != -1) {
      url = url.replaceFirst("ACCESS_KEY", access_key);
    }

    return url;
  }

  protected String ConvertStream2Json(InputStream inputStream)
  {
      String jsonStr = "";
      // ByteArrayOutputStream�൱���ڴ������
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int len = 0;
      // ��������ת�Ƶ��ڴ��������
      try
      {
          while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
          {
              out.write(buffer, 0, len);
          }
          // ���ڴ���ת��Ϊ�ַ���
          jsonStr = new String(out.toByteArray());
      }
      catch (IOException e)
      {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return jsonStr;
  }
  
  public MatchHistoryResult getMatchHistory() {
    String strUrl = getProperty("getMatchHistory", null, false);
    strUrl = replaceAccessKey(strUrl);
    try
    {
        URL url = new URL(strUrl);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();

        httpConn.setConnectTimeout(3000);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("GET");

        int respCode = httpConn.getResponseCode();
        if (respCode == 200)
        {
            String tmp = ConvertStream2Json(httpConn.getInputStream());
            ObjectMapper mapper = new ObjectMapper();
            MatchHistoryResult result = mapper.readValue(tmp, MatchHistoryResult.class);
            return result;
        }
    }
    catch (MalformedURLException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (IOException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
  }
}
