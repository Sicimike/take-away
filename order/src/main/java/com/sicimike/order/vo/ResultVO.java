package com.sicimike.order.vo;

import lombok.Data;

/**
 * @Author sicimike
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

    /**
     * 成功
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }

}
