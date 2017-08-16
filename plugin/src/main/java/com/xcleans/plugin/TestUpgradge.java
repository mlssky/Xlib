package com.xcleans.plugin;

/**
 * Created by mengliwei on 2017/8/16.
 * Function:
 */
public class TestUpgradge {


    public static class ApkInfo {

    }

    public static class UpdateInfo {

    }

    public static void test() {
        UpgradeCheckTools<ApkInfo, UpdateInfo, String> upgradeCheckTools = new UpgradeCheckTools();
        upgradeCheckTools.init(new UpgradeCheckTools.IUpdateInfoParser<UpdateInfo, String>() {
            @Override
            public UpdateInfo parse(String s) {
                //解析
                return null;
            }
        }, new UpgradeCheckTools.ICheckStrategy<ApkInfo>() {
            @Override
            public boolean canCheck(ApkInfo apkInfo) {
                //检测更新策略
                return false;
            }
        }, new INetRequest<ApkInfo, String>() {
            @Override
            public void request(ApkInfo apkInfo, INetResponse<ApkInfo, String> response) {
                //处理请求
            }
        });
    }
}
