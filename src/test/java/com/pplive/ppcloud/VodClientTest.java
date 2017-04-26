package com.pplive.ppcloud;

import com.pplive.ppcloud.quick.model.VodCategoryInfoModel;
import com.pplive.ppcloud.quick.model.VodChannelInfoModel;
import com.pplive.ppcloud.request.VodChannelCreateRequest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * VodClient Tester.
 *
 * @author robertpicyu
 * @version 1.0
 * @since <pre>12/09/2016</pre>
 */
public class VodClientTest extends TestCase {
    public void testExample() throws Exception {
        String categoryName = "SDK-VOD-TEST-" + System.currentTimeMillis();
        String channelWebId = "0a2dnq6apqGkmqiL4K2fm6iWp-ydoKyZpqSh";

        // 0. 初始化Client
        String accessKey = "xxxxxxxxxxxx";//"替换您的 AccessKey";
        String secretKey = "xxxxxxxxxxxx";//"替换您的 SecretKey";
        VodClient vodClient = new VodClient(accessKey, secretKey);
        vodClient.setProxyConfig("http://svc.pptvyun.ppqa.com",80);

        //1、创建视频分类
        VodCategoryInfoModel category = vodClient.addCategory(categoryName);

        // 获取用户所有的视频分类
        VodCategoryInfoModel[] categoryList = vodClient.getVodCategoryList(0);

        //2、修改视频分类信息
        vodClient.updateCategory(category.getId(), categoryName + "-updated");

        //3、获取某个分类下视频的列表
        vodClient.getVodChannelList(category.getId());

        //3、创建点播（得到上传upToken）
        VodChannelCreateRequest request = new VodChannelCreateRequest();
        request.setName("vodName");
        request.setSummary("my test vod name");
        vodClient.createVod("D://xxxx.mp4", request);

        //4、获取视频信息
        VodChannelInfoModel vodChannel = vodClient.getVodChannelInfo(channelWebId);

        //4、修改视频信息
        vodClient.updateVodChannelName(vodChannel.getChannelWebId(), vodChannel.getChannelName() + "updated!");

        //6、删除视频
        vodClient.deleteVodChannel(channelWebId);

        //7、删除分类
        vodClient.deleteCategory(category.getId());

    }
} 
