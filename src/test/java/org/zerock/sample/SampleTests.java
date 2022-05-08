package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@WebAppConfiguration
@Log4j
public class SampleTests {

    @Setter(onMethod_ = { @Autowired })
    private Restaurant restaurant;

    @Test
    public void testExist() {
        assertNotNull(restaurant);  // restaurant 변수가 null이 아니어야만 테스트 성공

        log.info(restaurant);
        log.info("-----------------------------------------");
        log.info(restaurant.getChef());
    }
}
