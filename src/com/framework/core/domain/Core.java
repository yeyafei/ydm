package com.framework.core.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.core.annotation.TableName;

public class Core implements Cloneable {
	/**
	 * 获取TableName
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getTableName(Class clazz) {
		TableName tName = (TableName) clazz.getAnnotation(TableName.class);
		if (tName == null)
			throw new RuntimeException("错误: " + clazz.getName()
					+ "没有TableName注解");
		return tName.name();
	}

	/**
	 * map -> po
	 * 
	 * @param map
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Object M2P(Map<String, Object> map, Object o)
			throws Exception {
		if (!map.isEmpty()) {
			for (String k : map.keySet()) {
				Object v = "";
				if (!k.isEmpty()) {
					v = map.get(k);
				}
				Field[] fields = null;
				fields = o.getClass().getDeclaredFields();
				for (Field field : fields) {
					int mod = field.getModifiers();
					if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
						continue;
					}
					if (toTableString(field.getName()).equals(k)) {	//转驼峰
						field.setAccessible(true);
						field.set(o, v);
						continue;
					}
				}
			}
		}
		return o;
	}

	/**
	 * mapList -> poList
	 * 
	 * @param map
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static List<Object> ML2PL(List<Map<String, Object>> maps, Object o)
			throws Exception {

		List<Object> os = new ArrayList<Object>();
		for (Map<String, Object> map : maps) {
			Object o2 = new Object();
			o2 = Clone.deepClone(o); // 此处要进行复制操作到另外一个引用对象
			os.add(M2P(map, o2));
		}
		return os;
	}

	/**
	 * po -> map
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> P2M(Object o) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = null;
		fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String proName = field.getName();
			Object proValue = field.get(o);
			map.put(proName, proValue);
		}
		return map;
	}
	
	/**
	 * 驼峰标识转下划线标识 非小写和数字加"_"
	 * 
	 * @param text
	 * @return
	 */
	private static String toTableString(String text) {
		String tName = text.substring(0, 1).toLowerCase();
		for (int i = 1; i < text.length(); i++) {
			if (!Character.isLowerCase(text.charAt(i))
					&& !Character.isDigit(text.charAt(i))) {
				tName += "_";
			}
			tName += text.substring(i, i + 1);
		}
		return tName.toLowerCase();
	}


}
