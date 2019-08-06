package com.loveseriously.crawler.core.verify;

/**
 * @author lw
 * @date 2019-08-06
 */
public interface VerigyApption {
    /**
     * 提交表单验证， 验证成功还回目标uri. 失败重定向到验证页面。
     *
     * @return
     */
    boolean submit();
}
