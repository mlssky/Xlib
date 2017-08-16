package com.xcleans.plugin;

import java.util.List;

/**
 * Created by mengliwei on 2017/8/16.
 * Function:
 */
public class UpgradeCheckTools<VersionInfo, UpdateInfo, UpdateResult> implements INetResponse<VersionInfo, UpdateResult> {

    private IUpdateInfoParser<UpdateInfo, UpdateResult> mUpdateInfoParser;
    private INetRequest<VersionInfo, UpdateResult> mNetRequest;
    private ICheckStrategy<VersionInfo> mVersionInfoICheckStrategy;

    public void init(IUpdateInfoParser<UpdateInfo, UpdateResult> parser, ICheckStrategy<VersionInfo> strategy, INetRequest<VersionInfo, UpdateResult> netRequest) {
        mUpdateInfoParser = parser;
        mVersionInfoICheckStrategy = strategy;
        mNetRequest = netRequest;
    }

    /**
     * 检测更新
     */
    public void check(List<VersionInfo> toUpdateInfos) throws Exception {
        if (mUpdateInfoParser == null || mVersionInfoICheckStrategy == null) {
            throw new Exception("not init");
        }
        if (toUpdateInfos != null && toUpdateInfos.size() > 0) {
            for (VersionInfo info : toUpdateInfos) {
                if (mVersionInfoICheckStrategy.canCheck(info)) {
                    mNetRequest.request(info, this);
                }
            }
        }

    }


    @Override
    public void onResponse(VersionInfo info, UpdateResult result) {
        if (mUpdateInfoParser != null) {
            UpdateInfo updateInfo = mUpdateInfoParser.parse(result);
            if (updateInfo != null) {
                onUpdateSucc(info, updateInfo);
            } else {
                onUpdateErr(info, result);
            }
        }
    }

    /**
     * 在更新成功后调用
     *
     * @param info
     * @param updateInfo
     */
    public void onUpdateSucc(VersionInfo info, UpdateInfo updateInfo) {

    }

    /**
     * 在更新失败时调用
     *
     * @param info
     * @param result
     */
    public void onUpdateErr(VersionInfo info, UpdateResult result) {

    }


    ///////////////////////////////////////////////////////////////////////////
    // inner class define
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param <UpdateInfo>   更新信息
     * @param <UpdateResult> 请求返回的数据<string,json>
     */
    public static interface IUpdateInfoParser<UpdateInfo, UpdateResult> {
        UpdateInfo parse(UpdateResult result);
    }

    public static interface ICheckStrategy<VersionInfo> {
        boolean canCheck(VersionInfo info);
    }


}
