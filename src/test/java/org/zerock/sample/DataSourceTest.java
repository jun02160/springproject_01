package org.zerock.sample;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.config.RootConfig;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@WebAppConfiguration
@Log4j
public class DataSourceTest {
    @Setter(onMethod_ = { @Autowired })
    private DataSource dataSource;

    @Test
    public void testConnection() {
        try (Connection con = dataSource.getConnection()){
            log.info(con);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Setter(onMethod_ = { @Autowired})
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMyBatis() {
        try (SqlSession session = sqlSessionFactory.openSession();
             Connection con = session.getConnection();
        ){
            log.info(session);
            log.info(con);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}

