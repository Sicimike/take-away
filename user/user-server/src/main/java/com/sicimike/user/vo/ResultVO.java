package com.sicimike.user.vo;

import com.sicimike.user.enums.EnumResult;
import lombok.Data;

/**
 * @author sicimike
 * @create 2018-12-05 11:46
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static ResultVO error(EnumResult enumResult){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(enumResult.getCode());
        resultVO.setMsg(enumResult.getMessage());
        return resultVO;
    }

}
