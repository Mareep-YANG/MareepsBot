package org.mareep.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.*;
import org.mareep.entity.GroupMemberInfo;
import org.mareep.events.CommandEvent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class testCommandListener implements EventListener {
    @EventHandler
    public void onCommand(CommandEvent evt) throws IOException {

        if (evt.label.equals("bot")){
            if (evt.args[1].equals("getData")){
                if (evt.args.length == 3){
                    new get_group_member_info(evt.group_id , evt.args[2] , false).get_group_member_info(new get_group_member_info.ResponseCallback_G()
                    {
                        @Override
                        public void onSuccess(GroupMemberInfo vaule) {
                            new send_msg("group",evt.group_id,vaule.toString(), false).send_msg();
                        }

                        @Override
                        public void onFailure(String error) {
                            send_msg send_msg = new send_msg("group", evt.group_id, "获取失败", false);
                        }
                    });
                }
            }
            if (evt.args[1].equals("friends")){
                new get_friend_list().get_friend_list(new get_friend_list.ResponseCallback_G() {
                    @Override
                    public void onSuccess(JSONArray vaule) {
                        Workbook workbook = new HSSFWorkbook();
                        Sheet sheet = workbook.createSheet("UserData");

                        // 创建标题行
                        Row headerRow = sheet.createRow(0);
                        headerRow.createCell(0).setCellValue("user_id");
                        headerRow.createCell(1).setCellValue("nickname");
                        headerRow.createCell(2).setCellValue("remark");

                        // 将用户数据写入Excel表格
                        int rowNum = 1;
                        for (Object obj : vaule) {
                            System.out.println(rowNum);
                            JSONObject userData = (JSONObject)  obj;
                            Row row = sheet.createRow(rowNum++);
                            row.createCell(0).setCellValue(userData.getString("user_id"));
                            System.out.println(userData.getString("user_id"));
                            row.createCell(1).setCellValue(userData.getString("nickname"));
                            System.out.println(userData.getString("nickname"));
                            row.createCell(2).setCellValue(userData.getString("remark"));
                            System.out.println(userData.getString("remark"));
                        }

                        // 将工作簿保存到文件
                        try {
                            FileOutputStream outputStream = new FileOutputStream("UserData.xls ");
                            workbook.write(outputStream);
                            outputStream.close();
                            System.out.println("Excel文件已生成");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(String error) {

                    }
                }); ;
            }

        }





    }
}
