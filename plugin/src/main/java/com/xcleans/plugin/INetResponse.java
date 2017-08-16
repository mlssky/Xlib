package com.xcleans.plugin;

/**
 * 网络返回接口
 *
 * @param <VersionInfo>
 * @param <UpdateResult>
 */
public interface INetResponse<VersionInfo, UpdateResult> {
    void onResponse(VersionInfo info, UpdateResult result);
}