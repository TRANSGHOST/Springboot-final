package com.shuihuo.blog.result;

import org.springframework.stereotype.Component;


@Component
public class ResultFactory {

    private static final String SUCCESS = "success";
    //成功
    public Result buildSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS);
    }
    //成功，附带额外数据
    public Result buildSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                .setData(data);
    }
    //成功，自定义消息及数据
    public Result buildSuccessResult(String message, Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }
    public Result buildSuccessResult(String message,int number, Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setNumber(number)
                .setData(data);
    }
    //失败，附带消息
    public Result buildFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
    public Result buildFailResult(String message,int number, Object data) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setNumber(number)
                .setData(data);
    }
    //失败，自定义消息及数据
    public Result buildFailResult(String message, Object data) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setData(data);
    }
    //自定义创建
    public Result buildFreeResult(ResultCode code, String message, Object data) {
        return new Result()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

}
