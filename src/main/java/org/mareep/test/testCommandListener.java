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
import org.mareep.entity.CQCode;
import org.mareep.entity.GroupMemberInfo;
import org.mareep.entity.Message;
import org.mareep.entity.MessageData;
import org.mareep.events.CommandEvent;
import org.mareep.utils.HttpUtil;
import org.mareep.utils.IPCheckUtil;
import org.mareep.utils.PhoneNumberCheckUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class testCommandListener implements EventListener {
    @EventHandler
    public void onCommand(CommandEvent evt) throws IOException {
        if (evt.label.equals("bot")) {
            if (evt.args[1].equals("帮助")) {
                String helpText = "/bot 帮助\n" + "/bot 微博热搜\n" + "/bot B站热搜\n" + "/bot 历史上的今天\n" + "/bot 知乎热搜\n" + "/bot 随机一言\n";
                helpText += "/bot 动漫图\n" + "/bot 查IP <要查的IP>\n" + "/bot 查手机 <要查的手机>\n";
                new send_msg("group", evt.group_id, helpText, false).send_msg();
            }
            if (evt.args[1].equals("微博热搜")) {
                JSONObject response = HttpUtil.httpGet("https://tenapi.cn/v2/weibohot");
                JSONArray jsonArray = response.getJSONArray("data");
                String result = "";
                for (int i = 0; i < 10; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    result += "微博热搜第" + String.valueOf(i + 1) + ":" + jsonObject.getString("name") + "\n";
                }
                new send_msg("group", evt.group_id, result, false).send_msg();


            }
            if (evt.args[1].equalsIgnoreCase("B站热搜")) {
                JSONObject response = HttpUtil.httpGet("https://tenapi.cn/v2/bilihot");
                JSONArray jsonArray = response.getJSONArray("data");
                String result = "";
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    result += "B站热搜第" + String.valueOf(i + 1) + ":" + jsonObject.getString("name") + "\n";
                }
                new send_msg("group", evt.group_id, result, false).send_msg();

            }
            if (evt.args[1].equalsIgnoreCase("历史上的今天")) {
                JSONObject response = HttpUtil.httpGet("https://tenapi.cn/v2/history");
                JSONArray jsonArray = JSON.parseObject(response.toString()).getJSONObject("data").getJSONArray("list");
                String result = "";
                String today = JSON.parseObject(response.toString()).getJSONObject("data").getString("today");
                result += "今天是" + today + "\n";
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    result += jsonObject.getString("year") + "年:" + jsonObject.getString("title") + "\n";
                }
                new send_msg("group", evt.group_id, result, false).send_msg();


            }
            if (evt.args[1].equalsIgnoreCase("知乎热搜")) {
                JSONObject response = HttpUtil.httpGet("https://tenapi.cn/v2/zhihuhot");
                JSONArray jsonArray = response.getJSONArray("data");
                String result = "";
                for (int i = 0; i < 10; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    result += "知乎热搜第" + String.valueOf(i + 1) + ":" + jsonObject.getString("name") + "\n";
                }
                System.out.println(result);
                new send_msg("group", evt.group_id, result, false).send_msg();
            }
            if (evt.args[1].equalsIgnoreCase("随机一言")) {
                JSONObject response = HttpUtil.httpGet("https://tenapi.cn/v2/yiyan");
                String result = response.getString("data");
                new send_msg("group", evt.group_id, result, false).send_msg();
            }
            if (evt.args[1].equalsIgnoreCase("动漫图")) {
                JSONObject response = HttpUtil.httpPost("https://tenapi.cn/v2/acg", "format=json");
                String url = response.getJSONObject("data").getString("url");
                MessageData msgdata = MessageData.builder().file(url).build();
                Message msgs[] = new Message[]{Message.builder().data(msgdata).type("image").build()};
                new send_msg("group", evt.group_id, msgs).send_msg();

            }
            if (evt.args[1].equalsIgnoreCase("查IP")) {
                if (evt.args.length == 3) {
                    if (IPCheckUtil.validateIP(evt.args[2])) {
                        JSONObject data = HttpUtil.httpPost("https://tenapi.cn/v2/getip", "ip=" + evt.args[2]).getJSONObject("data");
                        String result = "IP地址:" + data.getString("ip") + "\n";
                        String country = data.getString("country");
                        String province = data.getString("province");
                        String city = data.getString("city");
                        String area = data.getString("area");
                        String isp = data.getString("isp");
                        result += "国家:" + country + "\n" + "省份:" + province + "\n" + "城市:" + city + "\n" + "地区:" + area + "\n" + "运营商:" + isp + "\n";
                        new send_msg("group", evt.group_id, result, false).send_msg();
                    } else {
                        new send_msg("group", evt.group_id, "IP地址格式错误", false).send_msg();
                    }
                } else {
                    new send_msg("group", evt.group_id, "参数错误", false).send_msg();
                }
            }
            if (evt.args[1].equalsIgnoreCase("查手机")) {
                if (evt.args.length == 3) {
                    if (PhoneNumberCheckUtil.isValidPhoneNumber(evt.args[2])) {
                        JSONObject data = HttpUtil.httpPost("https://tenapi.cn/v2/phone", "tel=" + evt.args[2]).getJSONObject("data");
                        if (data != null) {
                            String result = "手机号码:" + evt.args[2] + "\n";
                            String local = data.getString("local");
                            String type = data.getString("type");
                            String isp = data.getString("isp");
                            String std = data.getString("std");
                            result += "归属地:" + local + "\n" + "卡类型:" + type + "\n" + "运营商:" + isp + "\n" + "通信标准:" + std + "\n";
                            new send_msg("group", evt.group_id, result, false).send_msg();
                        } else {
                            new send_msg("group", evt.group_id, "查询失败", false).send_msg();
                        }
                    } else {
                        new send_msg("group", evt.group_id, "手机号码格式错误", false).send_msg();
                    }
                } else {
                    new send_msg("group", evt.group_id, "参数错误", false).send_msg();
                }
            }
            if (evt.args[1].equalsIgnoreCase("MinecraftUUID")) {
                if (evt.args.length == 3) {
                    JSONObject data = HttpUtil.httpPost("https://tenapi.cn/v2/mc", "uid=" + evt.args[2]).getJSONObject("data");
                    if (data != null) {
                        String result = "";
                        String name = data.getString("name");
                        String id = data.getString("id");
                        result += "玩家昵称:" + name + "\n" + "UUID:" + id;
                        new send_msg("group", evt.group_id, result, false).send_msg();
                    } else {
                        new send_msg("group", evt.group_id, "查询失败", false).send_msg();
                    }
                } else {
                    new send_msg("group", evt.group_id, "参数错误", false).send_msg();
                }
            }
        }
    }
}




