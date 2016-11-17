package com.dnp.web.controller;

import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by luozl on 2016/10/20.
 */
@Controller
@RequestMapping("/fileupload")
public class fileupload {


    @RequestMapping(value = "/fileupload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String upload(HttpServletRequest request, Model model) throws IOException, ServletException {

        HashMap<String, Object> ret = new HashMap<>();
        //获取id
        try {
            int numImplementID = Integer.parseInt(request.getParameter("numImplementID"));
            // String photo = request.getParameter("photo");
            //判断 是否有url----有删除
            String urls ="";
            if (urls !=null && !urls.equals("")) {
                System.out.println(urls);
                File uploadurl = new File(urls);
                String oldurl = uploadurl.toString();
                oldurl = oldurl.replaceAll("//","\\\\");
                // oldurl = oldurl.replaceAll("'","");

                new File(oldurl).delete();
            }

//项目的绝对路径   request.getSession().getServletContext().getRealPath()这个是得到项目的绝对地址
            String ctxPath = request.getSession().getServletContext().getRealPath("")+"/"+numImplementID;
            File file = new File(ctxPath);


            if (!file.exists()) {
                file.mkdir();
            }
            Collection<Part> parts = request.getParts();
            int id = 0;
            if (parts != null && !"".equals(parts)) {

                for (Part part : parts) {


                    String fileName = getFileName(part);
                    if (!fileName.contains("; name=\"numImplementID")&& !fileName.contains("; name=\"photo ")){
                        //; name="numImplementID           ; name="photo     ; name="photo   ; name="photo

                        File Files = new File(file + "/" + fileName);
                        String url = Files.toString();

                        System.out.print(url);
                        InputStream inputStream = part.getInputStream();
                        FileOutputStream outputStream = new FileOutputStream(Files);
                        IOUtils.copy(inputStream, outputStream);//将两个流对接
                        url = url.replaceAll("\\\\","//");
                        System.out.println(url);
                        // String varplan="'"+url+"'";
                      //  wd_implementManageService.updateUrl(url,numImplementID);


                        //关闭资源
                        inputStream.close();
                        outputStream.close();
                    }

                }
            }
            ret.put("type", Def.AlertType.success);
            ret.put("message", "操作成功");
        } catch (NumberFormatException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } catch (IOException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } catch (ServletException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } finally {
            return JsonUtil.toJson(ret);
        }

        //把你要的数据放进去，key 和 value，直接返回map，主要<span style="font-size: 1em; line-height: 1.5;">@ResponseBody，直接返回的是json</span>



    }

    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String submittedFileName = part.getSubmittedFileName();
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        header.lastIndexOf("\"");
        return fileName;
    }

    @RequestMapping(value = "/fileupload1", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String upload1(HttpServletRequest request, Model model) throws IOException, ServletException {

        HashMap<String, Object> ret = new HashMap<>();
        //获取id
        try {
            int numImplementID = Integer.parseInt(request.getParameter("numImplementID"));
            // String photo = request.getParameter("photo");
            //判断 是否有url----有删除
            String urls ="";
            if (urls !=null && !urls.equals("")) {
                System.out.println(urls);
                File uploadurl = new File(urls);
                String oldurl = uploadurl.toString();
                oldurl = oldurl.replaceAll("//","\\\\");
                // oldurl = oldurl.replaceAll("'","");

                new File(oldurl).delete();
            }

//项目的绝对路径   request.getSession().getServletContext().getRealPath()这个是得到项目的绝对地址
            String ctxPath = request.getSession().getServletContext().getRealPath("")+"/"+numImplementID;
            File file = new File(ctxPath);


            if (!file.exists()) {
                file.mkdir();
            }
            Collection<Part> parts = request.getParts();
            int id = 0;
            if (parts != null && !"".equals(parts)) {

                for (Part part : parts) {


                    String fileName = getFileName(part);
                    if (!fileName.contains("; name=\"numImplementID")&& !fileName.contains("; name=\"photo ")){
                        //; name="numImplementID           ; name="photo     ; name="photo   ; name="photo

                        File Files = new File(file + "/" + fileName);
                        String url = Files.toString();

                        System.out.print(url);
                        InputStream inputStream = part.getInputStream();
                        FileOutputStream outputStream = new FileOutputStream(Files);
                        IOUtils.copy(inputStream, outputStream);//将两个流对接
                        url = url.replaceAll("\\\\","//");
                        System.out.println(url);
                        // String varplan="'"+url+"'";
                        //  wd_implementManageService.updateUrl(url,numImplementID);


                        //关闭资源
                        inputStream.close();
                        outputStream.close();
                    }

                }
            }
            ret.put("type", Def.AlertType.success);
            ret.put("message", "操作成功");
        } catch (NumberFormatException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } catch (IOException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } catch (ServletException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } finally {
            return JsonUtil.toJson(ret);
        }

        //把你要的数据放进去，key 和 value，直接返回map，主要<span style="font-size: 1em; line-height: 1.5;">@ResponseBody，直接返回的是json</span>



    }
}
