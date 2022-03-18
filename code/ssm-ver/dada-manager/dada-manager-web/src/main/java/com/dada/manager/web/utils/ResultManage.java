package com.dada.manager.web.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultManage implements Serializable {

    // 补充属性
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final long serialVersionUID = 1L;

    /**
     * 接口返回状态1为成功，0为失败
     */
    private int result_code;

    /**
     * 接口失败时，返回信息
     */
    private String result_message;

    /**
     * 接口返回的业务数据
     */
    private Map data;
    
    // 无参构造方法
    public ResultManage() {
        
    }

    public int getResult_code() {
        return result_code;
    }

    /**
     * 接口返回状态1为成功，0为失败
     */
    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public Map getData() {
        return data;
    }

    /**
     * 接口返回的业务数据
     */
    public void setData(Map data) {
        this.data = data;
    }

    // ---------------------------------------------------
    // 补充方法
    public ResultManage(int result_code, String result_message, Map data) {
        this.result_code = result_code;
        this.result_message = result_message;
        this.data = data;
    }

    /**
     * 将json结果集转化为ResultManage对象
     * 
     * @param jsonData json数据
     * @param clazz ResultManage中的object类型
     * @return
     */
    public static ResultManage formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultManage.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return new ResultManage(jsonNode.get("result_code").intValue(), jsonNode.get("result_message").asText(), (Map) obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static ResultManage format(String json) {
        try {
            return MAPPER.readValue(json, ResultManage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ResultManage formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return new ResultManage(jsonNode.get("result_code").intValue(), jsonNode.get("result_message").asText(), (Map) obj);
        } catch (Exception e) {
            return null;
        }
    }

}
