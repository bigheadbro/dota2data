package com.dota.chinanaive;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.dota.chinanaive.service.DataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class DataServiceTest {

  @Autowired
  private DataService dataSrvc;
  
  @Test
  public void test() throws Exception {
    List<Match> matches = dataSrvc.getMatchHistory("&account_id=141310951");
    System.out.println(matches.size());
  }
}
