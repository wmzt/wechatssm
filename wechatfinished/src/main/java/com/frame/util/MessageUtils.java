package com.frame.util;

import com.frame.model.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtils {

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：语音
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * 请求消息类型：短视频
     */
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：事件
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 响应消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 响应消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";


    /**
     * 事件推送类型：关注
     */
    public static final String EVENT_PUSH_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件推送类型：取消关注
     */
    public static final String EVENT_PUSH_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件推送类型：上报地理位置
     */
    public static final String EVENT_PUSH_TYPE_LOCATION = "LOCATION";

    /**
     * 事件推送类型：点击菜单拉取消息
     */
    public static final String EVENT_PUSH_TYPE_CLICK = "CLICK";

    /**
     * 事件推送类型：点击菜单跳转链接
     */
    public static final String EVENT_PUSH_TYPE_VIEW = "VIEW";


    /**
     * 微信xml消息解析
     *
     * @param request 获取的消息体
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXmlString(String request) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        InputStream inputStream = new ByteArrayInputStream(request.getBytes("UTF-8"));
        //解析xml
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        //获取根元素
        Element root = document.getRootElement();
        //获取根元素的子节点
        List<Element> childrenList = root.elements();
        //遍历获取内容
        for (Element e : childrenList) {
            map.put(e.getName(), e.getText());
        }

        //关闭资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

//    /**
//     * 图片消息对象转换成xml
//     *
//     * @param imageMessage 图片消息对象
//     * @return
//     */
//    public static String imageMessageToXml(ImageMessage imageMessage) {
//        xstream.alias("xml", imageMessage.getClass());
//        return xstream.toXML(imageMessage);
//    }
//
//    /**
//     * 音乐消息对象转换成xml
//     *
//     * @param voiceMessage 音乐消息对象
//     * @return xml
//     */
//    public static String musicMessageToXml(VoiceMessage voiceMessage) {
//        xstream.alias("xml", voiceMessage.getClass());
//        return xstream.toXML(voiceMessage);
//    }
//
//    /**
//     * 视频消息对象转换成xml
//     *
//     * @param videoMessage 视频消息对象
//     * @return
//     */
//    public static String newsMessageToXml(VideoMessage videoMessage) {
//        xstream.alias("xml", videoMessage.getClass());
//        return xstream.toXML(videoMessage);
//    }
//
//    /**
//     * 图文消息对象转换成xml
//     *
//     * @param newsMessage 图文消息对象
//     * @return
//     */
//    public static String newsToXml(NewsMessage newsMessage) {
//        xstream.alias("xml", newsMessage.getClass());
//        return xstream.toXML(newsMessage);
//    }
//
//    /**
//     * 语音消息对象转换成xml
//     *
//     * @param voiceMessage 语音消息对象
//     * @return
//     */
//    public static String voiceMessageToXml(VoiceMessage voiceMessage) {
//        xstream.alias("xml", voiceMessage.getClass());
//        return xstream.toXML(voiceMessage);
//    }

}
