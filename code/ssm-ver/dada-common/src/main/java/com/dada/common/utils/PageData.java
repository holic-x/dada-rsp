package com.dada.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.dada.common.constant.Const;

/**
 * 
 * @ClassName：PageData     
 * @Description: request请求参数和返回数据对象   
 * @author KangMiao
 * @Date 2016年7月11日 上午10:34:44
 *
 */
public class PageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	
	Map map = null;
	HttpServletRequest request;
	
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		returnMap.put("jdbcDialect", Const.jdbcDialect);
		//获取数据格式为json字符串的参数
		/*String json = (String) request.getAttribute(Const.JSON_PARAMS_DATA);
		if (StringUtil.isNotBlank(json)) {
			JSONObject jsonObj = JSONObject.parseObject(json);
			for (Map.Entry<String,Object> ent : jsonObj.entrySet()) {
				returnMap.put(ent.getKey(), ent.getValue());
			}
		}*/
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	@Override
	public Object get(Object key) {
		Object obj = map.get(key);
		if (obj == null && key != null) {
			//当获取的value为空时，将key转换为大写重新获取，解决某些情况下存储的键自动转换为大写而获取不到值的问题
			String keyStr = (String) key;
			obj = map.get(keyStr.toUpperCase());
		}
		if(obj instanceof Object[]) {
			Object[] arr = (Object[]) obj;
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} 
		return obj;
	}
	
	public String getString(Object key) {
		String value = (String) get(key);
		if (StringUtils.isBlank(value) && key != null) {
			//当获取的value为空时，将key转换为大写重新获取，解决某些情况下存储的键自动转换为大写而获取不到值的问题
			String keyStr = (String) key;
			value = (String) get(keyStr.toUpperCase());
		}
		value = value == null ? "" : value;
		return value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set entrySet() {
		return map.entrySet();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set keySet() {
		return map.keySet();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection values() {
		return map.values();
	}
	
}
