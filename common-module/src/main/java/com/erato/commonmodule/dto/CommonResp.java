package com.erato.commonmodule.dto;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommonResp<T> {

    private int code;
    private String message;
    private T data;

    public static CommonResp success(){

        return new CommonResp().setCode(200).setMessage("success");
    }

    public static <T> CommonResp<T> success(T data) {
        return success().setData(data);
    }

    public static CommonResp fail() {
        return new CommonResp().setCode(1009).setMessage("fail");
    }

    public static <T> CommonResp<T> fail(T data) {
        return fail().setData(data);
    }
}
