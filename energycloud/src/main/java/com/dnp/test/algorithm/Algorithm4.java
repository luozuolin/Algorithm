package com.dnp.test.algorithm;

import com.dnp.QuakeApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by luozl on 2016/11/16.
 */
public class Algorithm4 {
    private static final Logger logger = LoggerFactory.getLogger(QuakeApplication.class);
    public void findAppearMost(int[] d)
    {
        Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
        int maxi=0;
        System.out.println(" -------------查找出现次数最多的数开始--------");
        for(int i=0;i<d.length;i++)
        {
            System.out.print(d[i]+" ");
            if(ht.get(d[i])!=null)
            {
                ht.put(d[i],ht.get(d[i])+1);
            }
            else
            {
                ht.put(d[i],1);
            }
            maxi=ht.get(d[maxi])>ht.get(d[i])?maxi:i;
        }
        System.out.println("");
        logger.debug("出现次数最多的数："+d[maxi]+" 出现次数:"+ht.get(d[maxi]));
        System.out.print(" -------------查找出现次数最多的数结束--------");
    }
    public void betterMaxSum(int[] d)
    {
        System.out.println(" -------------查找连续区间最大总和开始--------");
        //打印出输入
        for(int i=0;i<d.length;i++)
        {
            System.out.print(d[i]+" ");
        }
        int[][] data=new int[d.length][d.length];
        int start=0;int length=0;int max=d[0];
        for(int i=0;i<d.length;i++)
        {
            for(int j=0;j<d.length-i;j++)
            {
                if (j==0)
                    data[i][j]=d[i];
                else
                    data[i][j]=data[i][j-1]+d[i+j];
                if(max<data[i][j])
                {
                    max=data[i][j];
                    start=i;
                    length=j;
                }
            }
        }
        System.out.println("");
        logger.debug("最大值："+max+"开始位置:"+start+" 长度:"+length+"  区间为：");
        System.out.println("");
        for(int k=0;k<=length;k++)
        {
            System.out.print(d[start+k]+" ");
        }
        System.out.println("");
        System.out.println(" -------------查找连续区间最大总和结束--------");
    }
    //参考page323
    public  void calcCanadatrip(ArrayList<Canadatrip> canadatrips, int K)
    {
        int distinct=0;
        int k=0;
        //第一个路牌出现的位置
        int min=canadatrips.get(0).L-canadatrips.get(0).M;
        int max=canadatrips.get(0).L;
        for (Canadatrip c:canadatrips) {
            min=min>(c.L-c.M)?(c.L-c.M):min;
            max=max<c.L?c.L:max;
        }
        for(int i=min;i<=max;i++) {
            k=0;
            distinct = i;
            for (Canadatrip c : canadatrips) {
                if (distinct <= c.L && (distinct + c.M - c.L)>=0)
                    k += (distinct + c.M - c.L) / c.G + 1;
                else if(distinct > c.L)
                    k+=c.M/c.G+1;
            }
            if(k==K)
            {
                logger.debug("位置："+distinct);
                return;
            }
        }
    }
    //课程，去除课程数
    public  void calcWithDrawal(ArrayList<WithDrawal> withdrawals, int cutnum)
    {

    }

}

