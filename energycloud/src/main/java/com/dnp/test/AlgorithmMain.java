package com.dnp.test;

import com.dnp.QuakeApplication;
import com.dnp.test.algorithm.Algorithm4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luozl on 2016/11/16.
 */
public class AlgorithmMain {
    private static final Logger logger = LoggerFactory.getLogger(QuakeApplication.class);
    public  static   void main(String[] args)
    {
        logger.debug("test start");
        logger.error("error alert");
        Algorithm4 algorithm4=new Algorithm4();
      //  algorithm4.findAppearMost(new int[]{0,1,1,3,4,3,4,3,1,1,3,3});
        algorithm4.betterMaxSum(new int[]{-7,4,-3,6,3,-8,-3,3,4,4});
    }
}
