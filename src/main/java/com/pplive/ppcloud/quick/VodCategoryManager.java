package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.vod.VodManager;
import com.pplive.ppcloud.quick.model.VodCategoryInfoModel;
import com.pplive.ppcloud.quick.model.VodChannelInfoModel;
import com.pplive.ppcloud.request.*;
import com.pplive.ppcloud.response.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodCategoryManager {
    private VodCategoryManager(){}

    public static VodCategoryManager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static VodCategoryManager instance = new VodCategoryManager();
    }

    /**
     * 获取单个视频分类的信息
     * @param id ：视频分类的id
     * @return VodCategoryInfoModel
     */
    public VodCategoryInfoModel getVodCategory(Long id){
        VodCategoryListRequset request = new VodCategoryListRequset();
        request.setNeedCount(1);
        VodCategoryListData vodCategoryListData = VodManager.getInstance().getCategoryList(request);

        VodCategoryInfoModel vodCategoryInfoModel = null;
        VodCategoryInfoResponse[] vodCategorys = vodCategoryListData.getData();

        for (int i=0;i<vodCategorys.length;i++){
            if(id.equals(vodCategorys[i].getId())){
                vodCategoryInfoModel = new VodCategoryInfoModel(vodCategorys[i]);
                break;
            }
        }
        return vodCategoryInfoModel;
    }

    /**
     * 获取单个视频分类的信息
     * @param id ：视频分类的id
     * @return VodCategoryInfoModel
     */
    public VodCategoryInfoModel getVodCategoryWithChannelNumber(Long id){
        VodCategoryListRequset request = new VodCategoryListRequset();
        request.setNeedCount(1);
        VodCategoryListData vodCategoryListData = VodManager.getInstance().getCategoryList(request);

        VodCategoryInfoModel vodCategoryInfoModel = null;
        VodCategoryInfoResponse[] vodCategorys = vodCategoryListData.getData();

        for (int i=0;i<vodCategorys.length;i++){
            if(id.equals(vodCategorys[i].getId())){
                vodCategoryInfoModel = new VodCategoryInfoModel(vodCategorys[i]);
                break;
            }
        }
        return vodCategoryInfoModel;
    }

    /**
     * 获取用户的视频分类的信息
     * @param categoryName :视频分类的名称
     * @return 所有同名的分类
     */
    public List<VodCategoryInfoModel> getVodCategorys(String categoryName){
        List<VodCategoryInfoModel> results = new LinkedList<VodCategoryInfoModel>();
        VodCategoryListRequset request = new VodCategoryListRequset();
        request.setNeedCount(0);
        VodCategoryListData vodCategoryListData = VodManager.getInstance().getCategoryList(request);

        VodCategoryInfoModel vodCategoryInfoModel = null;
        VodCategoryInfoResponse[] vodCategorys = vodCategoryListData.getData();

        for (int i=0;i<vodCategorys.length;i++){
            if( categoryName.equals(vodCategorys[i].getCategoryName()) ){
                results.add(new VodCategoryInfoModel(vodCategorys[i]));
            }
        }
        return results;
    }


    /**
     * 获取视频分类列表
     * @param  needCount : 是否显示列表包含的视频个数
     * @return VodCategoryInfoModel[] 视频列表
     */
    public  VodCategoryInfoModel[] getVodCategoryList(Integer needCount){
        VodCategoryListRequset request = new VodCategoryListRequset();
        request.setNeedCount(needCount);
        VodCategoryListData vodCategoryListData = VodManager.getInstance().getCategoryList(request);

        VodCategoryInfoResponse[] vodCategorys = vodCategoryListData.getData();
        List<VodCategoryInfoModel> results = new LinkedList<VodCategoryInfoModel>();

        for (int i=0;i<vodCategorys.length;i++){
            results.add(new VodCategoryInfoModel(vodCategorys[i]));
        }
        return results.toArray(new VodCategoryInfoModel[0]);
    }

    /**
     * 添加分类
     * @param categoryName ：分类的名称
     * @return VodCategoryInfoModel ：分类信息
     */
    public VodCategoryInfoModel addCategory(String categoryName){
        VodCategoryInfoModel vodCategoryInfoModel = new VodCategoryInfoModel();
        VodCategoryAddRequest vodCategoryAddRequest = new VodCategoryAddRequest();
        vodCategoryAddRequest.setCategoryName(categoryName);

        /* create category */
        VodCategoryAddResponse vodCategoryAddResponse = VodManager.getInstance().addCategory(vodCategoryAddRequest);
        if(null == vodCategoryAddResponse) {
            vodCategoryInfoModel.setErr("113098");
            vodCategoryInfoModel.setMsg("no response!");
            return vodCategoryInfoModel;
        }else if(!"0".equals(vodCategoryAddResponse.getErr())){
            vodCategoryInfoModel.setErr(vodCategoryAddResponse.getErr());
            vodCategoryInfoModel.setMsg(vodCategoryAddResponse.getMsg());
            return vodCategoryInfoModel;
        }

        /* get category info */
        VodCategoryInfoModel addVodCategoryResult = getVodCategory(vodCategoryAddResponse.getId());

        return addVodCategoryResult;
    }

    /**
     * 删除分类
     * @param categoryId  ： 视频类id
     */
    public void deleteCategory(Long categoryId){
        VodCategoryInfoModel category = getVodCategoryWithChannelNumber(categoryId);
        if (null == category || category.getChannelNum() != 0){
            throw new RuntimeException("catogory is not empty,can not deleted!");
        }
        BaseResponse deleteResponse = null;
        VodCategoryDeleteRequest  deleteRequest  = new VodCategoryDeleteRequest();
        deleteRequest.setCategoryId(categoryId);

        deleteResponse = VodManager.getInstance().deleteCategory(deleteRequest);
        if (deleteResponse == null || !"0".equals(deleteResponse.getErr())){
            throw new RuntimeException("delete catogory failed! ");
        }
    }

    /**
     * 更新分类信息
     * @param categoryId ：分类Id
     * @param finalName ：更新后的分类名称
     */
    public void updateCategory(Long categoryId, String finalName){
        BaseResponse vodCategoryUpdateResponse = null;
        VodCategoryUpdateRequest vodCategoryUpdateRequest = new VodCategoryUpdateRequest();
        vodCategoryUpdateRequest.setCategoryId(categoryId);
        vodCategoryUpdateRequest.setCategoryName(finalName);

        vodCategoryUpdateResponse = VodManager.getInstance().updateCategory(vodCategoryUpdateRequest);
        if(vodCategoryUpdateResponse == null || !"0".equals(vodCategoryUpdateResponse.getErr())){
            throw new RuntimeException("update catogory failed!");
        }
    }

    /**
     * 获取视频分类下所有视频的详细信息
     * @param categoryId    ：分类Id
     * @param pageNum       ：分页号
     * @param pageSize      ：分页大小
     * @return VodChannelInfoModel[] 视频信息列表
     */
    public VodChannelInfoModel[] getVodChannelList(Long categoryId, Integer pageNum, Integer pageSize){
        VodChannelListRequest request = new VodChannelListRequest();
        VodChannelListResponse response = null;
        request.setCategoryId(categoryId);
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);

        response = VodManager.getInstance().getChannelList(request);
        if (response == null || !response.getErr().equals("0")){
            throw new RuntimeException("get vod list fail!");
        }
        return VodChannelInfoModel.getVodChannelList(response.getData());
    }

    /**
     * 获取视频分类下所有视频的详细信息
     * @param categoryId    ：分类Id
     * @return VodChannelInfoModel[] 视频信息列表
     */
    public VodChannelInfoModel[] getVodChannelList(Long categoryId){
        VodChannelListRequest request = new VodChannelListRequest();
        VodChannelListResponse response = null;
        request.setCategoryId(categoryId);
        request.setPageNum(1);
        request.setPageSize(1);

        response = VodManager.getInstance().getChannelList(request);
        if (response == null || !response.getErr().equals("0")){
            throw new RuntimeException("get vod list fail!");
        }

        Integer pageSize = response.getTotalnum();
        request.setPageNum(1);
        request.setPageSize(pageSize);
        response = VodManager.getInstance().getChannelList(request);
        if (response == null || !response.getErr().equals("0")){
            throw new RuntimeException("get vod list fail!");
        }

        return VodChannelInfoModel.getVodChannelList(response.getData());
    }
}
