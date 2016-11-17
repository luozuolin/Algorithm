package com.dnp.web.service;

import com.dnp.util.JsonUtil;
import com.dnp.web.mapper.GetDataMapper;
import com.dnp.web.mapper.WD_StatisticsCircuitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public class WD_StatisticsCircuitService {
    @Autowired
    WD_StatisticsCircuitMapper wd_statisticsCircuitMapper;
    public List<HashMap> getAll(HashMap<String, Object> data) {
        List<HashMap> hashMaps = wd_statisticsCircuitMapper.getAll(data);
        for (int j = 0; j < hashMaps.size(); j++) {
            if (hashMaps.get(j).get("numValue") != null) {

                String huanBi = "";
                String tongBi = "";
                String yuCe = "";
                double numValue =0;
                double chainValue=0;
                double onYearValue=0;
                double onYearChainValue=0;
                if (hashMaps.get(j).get("numValue") !="" && hashMaps.get(j).get("numValue")!=null){

                     numValue = Double.parseDouble(hashMaps.get(j).get("numValue").toString());
                }
                if (hashMaps.get(j).get("chainValue") !="" && hashMaps.get(j).get("chainValue")!=null){

                    chainValue =Double.parseDouble(hashMaps.get(j).get("chainValue").toString());
                }
                if (hashMaps.get(j).get("onYearValue") !="" && hashMaps.get(j).get("onYearValue")!=null){

                     onYearValue = Double.parseDouble(hashMaps.get(j).get("onYearValue").toString());
                }
                if (hashMaps.get(j).get("onYearChainValue") !="" && hashMaps.get(j).get("onYearChainValue")!=null){

                    onYearChainValue = Double.parseDouble(hashMaps.get(j).get("onYearChainValue").toString());
                }
                if (numValue !=0 && chainValue!=0){

                    double chain = (numValue / chainValue)-1;//环比
                    DecimalFormat df1 = new DecimalFormat("##.00%");//保留小数点后几位
                    huanBi = df1.format(chain);//环比
                    hashMaps.get(j).put("huanBi", huanBi);

                }else {

                    hashMaps.get(j).put("huanBi", "---");

                }
                if (numValue !=0 && onYearValue!=0){

                    double YearValue = numValue / onYearValue;//同比
                    DecimalFormat df1 = new DecimalFormat("##.00%");//保留小数点后几位
                    tongBi = df1.format(YearValue);//同比
                    hashMaps.get(j).put("tongBi", tongBi);


                }else {
                    hashMaps.get(j).put("tongBi", "---");
                }
                if (numValue !=0 && chainValue!=0 && onYearChainValue !=0){

                    double YearChain = (onYearValue / onYearChainValue) *chainValue;//环比
                    DecimalFormat df1 = new DecimalFormat("######0.00");//保留小数点后几位
                    yuCe = df1.format(YearChain);//环比

                    hashMaps.get(j).put("yuCe", yuCe);

                }else {

                    hashMaps.get(j).put("yuCe", "---");

                }


            }
        }
        return hashMaps;
    }
    public List<HashMap> listAll(HashMap<String, Object> data) {
      return wd_statisticsCircuitMapper.listAll(data);

    }
    public List<HashMap> getchartdata(HashMap<String, Object> data) {

        String circuitID = (String) data.get("numCircuitID");
        if (!"".equals(circuitID) && circuitID !=null){
            return wd_statisticsCircuitMapper.getchartdata(data);//曲线图
        }else {
            return null;
        }
    }
    public int getAllCount(HashMap<String, Object> data) {
        return wd_statisticsCircuitMapper.getAllCount(data);
    }

    public List<HashMap> listByBeginningOfMonth(HashMap<String, Object> data) {
        return wd_statisticsCircuitMapper.listByBeginningOfMonth(data);

    }
    public List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {
        return wd_statisticsCircuitMapper.listAllByDateRangeAndnumCircuitGroupIDs(data);
    }
}
