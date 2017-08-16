package com.xcleans.plugin;

/**
 * 网络请求接口
 *
 * @param <VersionInfo>
 * @param <UpdateResult>
 */
public interface INetRequest<VersionInfo, UpdateResult> {
    /**
     * 异步队列实现
     *
     * @param info
     * @param response
     */
    void request(VersionInfo info, INetResponse<VersionInfo, UpdateResult> response);
}

