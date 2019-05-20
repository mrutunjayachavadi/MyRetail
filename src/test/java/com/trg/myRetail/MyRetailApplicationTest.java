package com.trg.myRetail;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({ System.class, SpringApplication.class})
public class MyRetailApplicationTest {


   @Test
   public void testMain() throws IOException {
      MockitoAnnotations.initMocks(this);
      PowerMockito.mockStatic(SpringApplication.class);
      MyRetailApplication.main(new String[0]);
   }
}
