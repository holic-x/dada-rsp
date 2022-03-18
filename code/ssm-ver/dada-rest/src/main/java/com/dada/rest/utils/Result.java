package com.dada.rest.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dada.common.utils.PageData;

/**
 * 
 * @ClassName：Result
 * @Description: 对外接口统一返回对象
 * @author KangMiao
 * @Date 2016年7月15日 下午1:29:43
 * 
 */
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 接口返回状态1为成功，0为失败
	 */
	private int result_code;
	private String result_message; // 接口返回信息
	private int totalResult; // 接口返回数据总记录数
	
	/**
	 * 接口返回单条数据,设置此属性
	 */
	private Map data;
	/**
	 * 接口返回多条数据，设置此属性
	 */
	private List<PageData> dataList;

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
	 * 接口返回单条数据,设置此属性
	 */
	public void setData(Map data) {
		this.data = data;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public List<PageData> getDataList() {
		return dataList;
	}

	/**
	 * 接口返回多条数据集合，设置此属性
	 */
	public void setDataList(List<PageData> dataList) {
		this.dataList = dataList;
	}

}
