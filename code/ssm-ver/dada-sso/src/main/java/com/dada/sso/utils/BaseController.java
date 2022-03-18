package com.dada.sso.utils;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * Describe: 基础控制器
 */
public class BaseController {
    protected ResponseEntity<ResultManage> getJsonResult(Map<String, Object> resultMap) {
        ResultManage resultManage = new ResultManage();
        resultManage.setResult_code(1);
        resultManage.setData(resultMap);
        return new ResponseEntity<ResultManage>(resultManage, HttpStatus.OK);
    }

    protected ResponseEntity<ResultManage> getJsonResult() {
        ResultManage resultManage = new ResultManage();
        resultManage.setResult_code(1);
        resultManage.setData(null);
        return new ResponseEntity<ResultManage>(resultManage, HttpStatus.OK);
    }
}
