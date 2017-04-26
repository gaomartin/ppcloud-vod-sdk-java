package com.pplive.ppcloud;

import com.pplive.ppcloud.quick.model.VodCategoryInfoModel;
import com.pplive.ppcloud.request.VodChannelCreateRequest;
import com.pplive.ppcloud.response.VodChannelUploadResponse;
import com.pplive.ppcloud.utils.LogUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * VodClient Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/07/2016</pre>
 */
public class APPTest extends TestCase {
    private final String testCategoryName = "[VOD_SDK]-testCategory";
    private VodClient vodClient;
    private String testVodChannelWebId = "0a2dnq6apqGkmqiL4K2fm6iWp-ydoKyZpqSh";

    public APPTest(){
        // 0. 初始化Client
        String accessKey = "xxxxxxxxxx";//"替换您的 AccessKey";
        String secretKey = "xxxxxxxxxx";//"替换您的 SecretKey";
        vodClient = new VodClient(accessKey, secretKey);
        //vodClient.setProxyConfig("10.200.12.243",80);
    }
    /**
     * Method: addCategory(String categoryName)
     */
    public void testAddCategory() throws Exception {
        VodCategoryInfoModel category = vodClient.addCategory(testCategoryName);
    }

    /**
     * Method: deleteCategory(Integer categoryId)
     */
    public void testDeleteCategory() throws Exception {
        List<VodCategoryInfoModel> categorys = vodClient.getVodCategoryByName(testCategoryName);
        for(VodCategoryInfoModel category:categorys) {
            vodClient.deleteCategory(category.getId());
        }
    }

    /**
     * Method: getVodCategoryById(Integer id)
     */
    public void testGetVodCategoryInfo() throws Exception {
        List<VodCategoryInfoModel> categorys = vodClient.getVodCategoryByName(testCategoryName);
        for(VodCategoryInfoModel category:categorys) {
            LogUtils.log(" get categery :  " + vodClient.getVodCategoryById(category.getId()).toString());
        }
    }

    /**
     * Method: getVodCategoryList()
     */
    public void testGetVodCategoryList() throws Exception {
        LogUtils.log(" get categery list:  " + vodClient.getVodCategoryList(1));
    }

    /**
     * Method: getVodChannelList(String categoryId, Integer pageNum, Integer pageSize)
     */
    public void testGetVodChannelList() throws Exception {
        VodCategoryInfoModel category = vodClient.addCategory(testCategoryName);
        LogUtils.log(" get channel list:  " + vodClient.getVodChannelList(category.getId()).length);
    }

    /**
     * Method: updateCategory(Integer categoryId, String finalName)
     */
    public void testUpdateCategory() throws Exception {
        List<VodCategoryInfoModel> categorys = vodClient.getVodCategoryByName(testCategoryName);
        for(VodCategoryInfoModel categoryInfoModel:categorys) {
            vodClient.updateCategory(categoryInfoModel.getId(), testCategoryName + "updated");
            vodClient.deleteCategory(categoryInfoModel.getId());
        }
    }

    /**
     * Method: deleteVodChannel
     */
    public void testDeleteVodChannel() throws Exception {
        //vodClient.deleteVodChannel("0a2dnq6boqCjnq6L4K2fm6iWp-ydoa6WqaKh");
    }

    /**
     * Method: getVodChannelInfo(String channelWebId)
     */
    public void testGetVodChannelInfo() throws Exception {
        vodClient.getVodChannelInfo(testVodChannelWebId);
    }

    /**
     * Method: enableVodChannel(Set<String> channelWebids)
     */
    public void testEnableVodChannel() throws Exception {
        vodClient.enableVodChannel(testVodChannelWebId);
    }

    /**
     * Method: disableVodChannel(Set<String> channelWebids)
     */
    public void testDisableVodChannel() throws Exception {
        vodClient.disableVodChannel(testVodChannelWebId);
    }

    public void testCreateVod() throws Exception {
        VodChannelCreateRequest request = new VodChannelCreateRequest();
        request.setName("SDK_vodName");
        request.setSummary("my test vod name");
        VodChannelUploadResponse result = vodClient.createVod("D:\\video\\out_video.mp4", request);
    }

    /**
     * Method: updateVodChannelName(String channelWebId, String channelName)
     */
    public void testUpdateVodChannelName() throws Exception {
        vodClient.updateVodChannelName(testVodChannelWebId, "updated name");
    }

    /**
     * Method: updateVodChannelSummary(String channelWebId, String channelSummary)
     */
    public void testUpdateVodChannelSummary() throws Exception {
        vodClient.updateVodChannelSummary(testVodChannelWebId, "updated summary");
    }

    /**
     * Method: updateVodChannelCoverImage(String channelWebId, String coverImage)
     */
    public void testUpdateVodChannelCoverImage() throws Exception {
        vodClient.updateVodChannelCoverImage(testVodChannelWebId, "updated cover image url");
    }

    public static Test suite() {
        return new TestSuite(APPTest.class);
    }

}
