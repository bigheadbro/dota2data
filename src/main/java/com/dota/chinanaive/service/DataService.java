package com.dota.chinanaive.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dota.chinanaive.DAO.HeroDAO;
import com.dota.chinanaive.DAO.MatchHistoryDAO;
import com.dota.chinanaive.entity.Hero;
import com.dota.chinanaive.entity.HeroesResult;
import com.dota.chinanaive.entity.MatchHistory;
import com.dota.chinanaive.entity.MatchHistoryResult;
import com.dota.chinanaive.entity.MatchHistoryResult.Result;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataService {

	/** STEAM配置文件 */
	public final static String STEAM_PROPERTIES_FILENAME = "/steamapi.properties";

	/** 配置文件路径 */
	private String propertiesPath;

	private String access_key;

	/** 默认配置文件 */
	private Properties defaultProperties;

	/** 配置文件 */
	private Properties properties;

	@Autowired
	@Qualifier("mhDAO")
	private MatchHistoryDAO mhDAO;

	@Autowired
	@Qualifier("heroDAO")
	private HeroDAO heroDAO;
	
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
		// 首先从用户配置文件读取，读取到为NULL时再从默认配置读取
		Properties pro;
		if (properties.containsKey(key)) {
			pro = properties;
		} else {
			pro = defaultProperties;
		}
		// 读取配置值
		String value = pro.getProperty(key);
		if (value == null) {
			value = defaultValue;
		}
		if (value == null) {
			if (!allowNull) {
				throw new RuntimeException(
				    "配置文件（" + propertiesPath + "）缺少属性：key=" + key);
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

	protected String ConvertStream2Json(InputStream inputStream) {
    System.out.println("in");

    StringBuilder content = new StringBuilder();
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
      String line;
      while ((line = bufferedReader.readLine()) != null)
      {
        System.out.println(line);
        content.append(line + "\n");
      }
      bufferedReader.close();
    } catch (IOException e) {
      System.out.println("error occured when convert stream to json");
    } catch (Exception e) {
      System.out.println("error occured when convert stream to json");
    }
    
    System.out.println("out");
    return content.toString();
  }
	
	public void insertMatchHistory() {
		long id = 1373101046;
		while (true) {
			System.out.println("get data:" + id);
			MatchHistoryResult result = getMatchHistory(
			    "&start_at_match_seq_num=" + id + "&matches_requested=5");
		
			System.out.println("finish");
			if(result != null) {
  			List<Match> matches = result.getResult().getMatches();
  			System.out.println(id + ":" + matches.size());
  			for (int i = 0; i < matches.size(); i++) {
  				MatchHistory mh = new MatchHistory(matches.get(i));
  				if (i != matches.size() - 1) {
  					mhDAO.insertMatchHistory(mh);
  				} else {
  					id = mh.getMatch_seq_num();
  				}
  			}
			}
		}

	}

	public MatchHistoryResult getMatchHistory(String param) {
		String strUrl = getProperty("getMatchHistory", null, false);
		strUrl = replaceAccessKey(strUrl);
		if (StringUtils.isNotEmpty(param)) {
			strUrl = strUrl + param;
		}
		try {
			URL url = new URL(strUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			System.out.println("lalalala");
			httpConn.setConnectTimeout(0);
			httpConn.setReadTimeout(0);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			System.out.println("send request");

			int respCode = httpConn.getResponseCode();
			System.out.println("get response");
			if (respCode == 200) {
				String tmp = ConvertStream2Json(httpConn.getInputStream());
				ObjectMapper mapper = new ObjectMapper();
				System.out.println("begin to read");
				MatchHistoryResult result = mapper.readValue(tmp,
				    MatchHistoryResult.class);
				System.out.println("finish to read");
				return result;
			} else {
				System.out.println("response error");
			}
		} catch (SocketTimeoutException e) {
			System.out.println("getMatchHistory timeout");
		} catch (MalformedURLException e) {
			System.out.println("getMatchHistory exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			
		}
		return null;
	}

	public void getMatchHistoryToFile(long param) {
		String strUrl = getProperty("getMatchHistory", null, false);
		strUrl = replaceAccessKey(strUrl);
		strUrl = strUrl + "&start_at_match_seq_num=" + param;

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
				File file = new File("D:\\data\\" + param + ".json");
				OutputStream os = new FileOutputStream(file);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = httpConn.getInputStream().read(buffer, 0,
				    8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
			} else {
				System.out.println("response error");
			}
		} catch (SocketTimeoutException e) {
			System.out.println("getMatchHistory timeout");
		} catch (MalformedURLException e) {
			System.out.println("getMatchHistory exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateHeroes() {
		for(int i = 1373083139;i<=1373092051;i++) {
			MatchHistory mh = mhDAO.queryMatchHistoryBySeqnum(i);
			if(mh != null) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					String heroes = "";
					List<Player> players = mapper.readValue(
							mh.getPlayers(),
							mapper.getTypeFactory().constructCollectionType(
	                    List.class, Player.class));
					for(int j = 0;j<players.size();j++) {
						if(j != players.size() - 1) {
							heroes = heroes + players.get(j).getHero_id() + ",";
						} else {
							heroes = heroes + players.get(j).getHero_id();
						}
					}
					mh.setHeroes(heroes);
					mhDAO.updateMatchHistoryById(mh);
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
	}
	
	public void insertHero() {
		try {
			URL url = new URL("https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1/?key=76707E31AEC98FF8CAECE7E45677CD5D&language=zh");
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(10000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");

			int respCode = httpConn.getResponseCode();

			if (respCode == 200) {
				String tmp = ConvertStream2Json(httpConn.getInputStream());
				ObjectMapper mapper = new ObjectMapper();

				HeroesResult result = mapper.readValue(tmp, HeroesResult.class);
				List<HeroesResult.Result.Hero> heroes = result.getResult().getHeroes();
				for(int i = 0;i<heroes.size();i++){
					Hero hero = new Hero();
					hero.setId(heroes.get(i).getId());
					hero.setName(heroes.get(i).getName());
					hero.setLocalized_name(heroes.get(i).getLocalized_name());
					heroDAO.insertHero(hero);
				}
			} else {
				System.out.println("response error");
			}
		} catch (SocketTimeoutException e) {
			System.out.println("getMatchHistory timeout");
		} catch (MalformedURLException e) {
			System.out.println("getMatchHistory exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			
		}
		
		
	}
}
