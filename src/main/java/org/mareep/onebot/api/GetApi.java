package org.mareep.onebot.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mareep.onebot.QQbot;
import org.mareep.onebot.entity.Request;

public class GetApi {
    public String echo = String.valueOf(System.currentTimeMillis());
    public interface ResponseCallback {
        void onSuccess(JSONObject vaule);

        void onFailure(String error);
    }

    public void SentRequest(Request request, ResponseCallback callback) {
        Thread asyncThread = new Thread(() -> {
            try {
                String json = JSON.toJSONString(request);
                QQbot.client.send(json);
                long timeoutMillis = 10000; // 设置超时时间为10秒
                long startTime = System.currentTimeMillis();
                while (true) {
                    Thread.sleep(1000);
                    if (QQbot.EchoPool.containsKey(echo)) {
                        JSONObject response = QQbot.EchoPool.get(echo);
                        QQbot.EchoPool.remove(echo);
                        callback.onSuccess(response);
                        return;
                    }
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - startTime > timeoutMillis) {
                        // 超时处理
                        callback.onFailure("操作超时");
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                callback.onFailure("操作失败");
            }
        });
        asyncThread.start();
    }
}
