package com.dnp.test;

import com.dnp.QuakeApplication;
import com.dnp.test.algorithm.Algorithm4;
import com.dnp.test.algorithm.Canadatrip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by luozl on 2016/11/16.
 */
public class AlgorithmMain {
    private static final Logger logger = LoggerFactory.getLogger(QuakeApplication.class);
    public  static   void main(String[] args)
    {
      //  logger.debug("test start");
     //   logger.error("error alert");
        Algorithm4 algorithm4=new Algorithm4();
      //  algorithm4.findAppearMost(new int[]{0,1,1,3,4,3,4,3,1,1,3,3});
     //   algorithm4.betterMaxSum(new int[]{-7,4,-3,6,3,-8,-3,3,4,4});
        ArrayList<Canadatrip> canadatripList=new ArrayList<Canadatrip>();
        canadatripList.add(new Canadatrip(500,100,10));
        canadatripList.add(new Canadatrip(504,16,4));
        canadatripList.add(new Canadatrip(510,60,6));
        //第k个路牌出现的位置
        algorithm4.calcCanadatrip(canadatripList,15);

        ArrayList<Canadatrip> canadatripList1=new ArrayList<Canadatrip>();
        canadatripList1.add(new Canadatrip(8030000,8030000,1));
        canadatripList1.add(new Canadatrip(2,2,1));
        //第k个路牌出现的位置
        algorithm4.calcCanadatrip(canadatripList1,1234567);
    }
}
