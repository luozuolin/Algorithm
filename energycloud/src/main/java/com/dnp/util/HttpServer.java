package com.dnp.util;

import com.dnp.web.service.WD_TableDataInfosService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/8/17.
 */
@Controller
public class HttpServer {
    @RequestMapping(value = "/getdata", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
  /* public  String   get(HttpServletRequest req, String url)
    {
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = HttpClients.createDefault().execute(request);
            if(response.getStatusLine().getStatusCode()==200){
                String  result = EntityUtils.toString(response.getEntity());

                System.out.println(result);
                return   result;
            }
        }catch (Exception ex)
        {

            System.out.println(ex);
            return   "";
        }finally {

        }
        return  "";
    }*/
    public String get(HttpServletRequest req, String url)
    {
        String  result="";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    if(response.getStatusLine().getStatusCode()==200){
                          result = EntityUtils.toString(entity);
                    }
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return     result;
    }
    @Autowired
    WD_TableDataInfosService wd_tableDataInfosService;
    //从数据库中获取仪表的实时数据
    @RequestMapping(value = "/getrealdatafromdatabase", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getrealdatafromdatabase(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        return JsonUtil.toJson(wd_tableDataInfosService.getrealdatafromdatabase(object));
    }

}