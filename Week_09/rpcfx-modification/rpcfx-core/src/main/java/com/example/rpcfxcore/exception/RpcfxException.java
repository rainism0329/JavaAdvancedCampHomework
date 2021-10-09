package com.example.rpcfxcore.exception;

/**
 * @version 1.0
 * @program: rpcfx-modification
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/10/9 3:19 PM
 */
public class RpcfxException extends RuntimeException {
    /**
     * 异常错误码
     **/
    private String code;

    /**
     * 异常描述
     **/
    private String msg;
    /**
     * 扩展异常描述（包括msg）
     **/
    private String extMsg;

    /**
     * @param code 错误码
     * @param msg  描述信息
     */
    public RpcfxException(String code, String msg, String extMsg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
        this.extMsg = extMsg;
    }
}
