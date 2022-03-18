package com.dada.test;

import com.dada.report.utils.BeanGenerator;

// 对象数据生成器
public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 300; i++) {
			//System.out.println(BeanGenerator.randomStaff().toInsertString());
			System.out.println(BeanGenerator.randomEmployee().toInsertString());
		}

	}

}
