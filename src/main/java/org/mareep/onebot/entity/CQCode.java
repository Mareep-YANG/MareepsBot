package org.mareep.onebot.entity;

public class CQCode {

    // 构造普通文本消息的CQ码
    public static String textCQ(String content) {
        return "[CQ:text,text=" + content + "]";
    }

    // 构造图片消息的CQ码
    public static String imageCQ(String file) {
        return "[CQ:image,file=" + file + "]";
    }

    // 构造分享链接消息的CQ码
    public static String shareCQ(String url, String title, String content, String image) {
        return "[CQ:share,url=" + url + ",title=" + title + ",content=" + content + ",image=" + image + "]";
    }

    // 构造at某人消息的CQ码
    public static String atCQ(long qq) {
        return "[CQ:at,qq=" + qq + "]";
    }
}
