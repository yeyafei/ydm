package com.framework.core.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.core.annotation.Code;
import com.framework.core.annotation.FieldName;
import com.framework.core.annotation.Primary;
import com.framework.core.annotation.TableName;
import com.framework.core.annotation.TempField;

/**
 * Sql生成工具类
 * 
 * @param <T>
 *            要生成Sql的实体类
 */
public class SqlCore<T extends Po> {

	private SqlCore() {
	}

	private static SqlCore<?> instance = null;

	@SuppressWarnings("unchecked")
	public static synchronized SqlCore getInstance() {
		if (instance == null)
			instance = new SqlCore();
		return instance;
	}

	/**
	 * 获取实体类的某个字段
	 * 
	 * @param t
	 * @param fieldName
	 * @return
	 */
	public Field getField(Class<?> t, String fieldName) {
		Field[] fields = t.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 获取code信息
	 * 
	 * @param po
	 * @return
	 */
	public Map<String, String> getCodeMess(Po po) {
		Map<String, String> m = new HashMap<String, String>();
		Class<? extends Po> thisClass = po.getClass();
		Field[] fields = thisClass.getDeclaredFields();
		int i = 0;
		for (Field f : fields) {
			if (f.isAnnotationPresent(Code.class)) {
				m.put("name", f.getName());
				m.put("type", f.getAnnotation(Code.class).name());
				i++;
			}
		}
		if (i > 1)
			throw new RuntimeException("错误: " + po + "不能拥有两个code注解");
		if (i < 1)
			throw new RuntimeException("错误: " + po + "无code注解");
		return m;
	}

	/**
	 * 获取查询sql的字段列表
	 * 
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListOfSelect(Po po) {
		List<Pram> list = new ArrayList<Pram>();
		Class<? extends Po> thisClass = po.getClass();
		Field[] fields = thisClass.getDeclaredFields();
		for (Field f : fields) {
			try {
				if (!f.isAnnotationPresent(TempField.class)) {
					String fName = f.getName();
					// 判断是否是boolean类型
					String get = "get";
					String fieldType = f.getGenericType().toString();
					if (fieldType.indexOf("boolean") != -1
							|| fieldType.indexOf("Boolean") != -1) {
						get = "is";
					}
					if (f.isAnnotationPresent(FieldName.class)) {
						String fieldName = f.getAnnotation(FieldName.class)
								.name();
						Pram pram = new Pram(fieldName + " as " + fName,
								thisClass.getMethod(
										get
												+ fName.substring(0, 1)
														.toUpperCase()
												+ fName.substring(1))
										.invoke(po));
						list.add(pram);
					} else {
						String fieldName = toTableString(fName);
						Pram pram = new Pram(fieldName + " as " + fName,
								thisClass.getMethod(
										get
												+ fName.substring(0, 1)
														.toUpperCase()
												+ fName.substring(1))
										.invoke(po));
						list.add(pram);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 获取实体类对应的表名
	 * 
	 * @param po
	 * @return
	 */
	public String getTableName(Po po) {
		Class<? extends Po> c = po.getClass();
		if (c.isAnnotationPresent(TableName.class)) {
			return c.getAnnotation(TableName.class).name();
		} else {
			throw new RuntimeException("错误: " + po + "没有TableName注解");
		}
	}

	/**
	 * 获取实体类对应的Primary和ID的值（唯一值） 只取最先取到的一个含有值的Primary 用于对单条数据的更新 、查找、 删除这些 动作
	 * 注:Primary不考虑boolean类型
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public Pram getPrimaryName(Po po) throws Exception {
		Pram pram = null;
		Class<? extends Po> thisClass = po.getClass();
		Field[] fields = thisClass.getDeclaredFields();
		boolean type = false;
		for (Field f : fields) {
			if (!type
					&& (f.getName().equalsIgnoreCase("ID") || f
							.isAnnotationPresent(Primary.class))) {
				String fName = f.getName();
				String fieldName = "";
				if (f.isAnnotationPresent(FieldName.class)) {
					fieldName = f.getAnnotation(FieldName.class).name();
				} else {
					fieldName = toTableString(fName);
				}
				Method get = thisClass.getMethod("get"
						+ fName.substring(0, 1).toUpperCase()
						+ fName.substring(1));
				Object getValue = get.invoke(po);
				if (getValue != null && !"".equals(getValue)
						&& !"0".equals(getValue.toString())) {
					pram = new Pram(fieldName, getValue);
					type = true;
				}
			}
		}
		return pram;
	}

	/**
	 * 获取实体类的数据库里字段的名字和值
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public List<Pram> getPramList(Po po) throws Exception {
		List<Pram> list = new ArrayList<Pram>();
		Class<? extends Po> thisClass = po.getClass();
		Field[] fields = thisClass.getDeclaredFields();
		for (Field f : fields) {
			if (!f.getName().equalsIgnoreCase("ID")
					&& !f.isAnnotationPresent(TempField.class)) {
				String fName = f.getName();
				String fieldName = "";
				// 判断是否是boolean类型
				String getf = "get";
				String fieldType = f.getGenericType().toString();
				if (fieldType.indexOf("boolean") != -1
						|| fieldType.indexOf("Boolean") != -1) {
					getf = "is";
				}
				if (f.isAnnotationPresent(FieldName.class)) {
					fieldName = f.getAnnotation(FieldName.class).name();
				} else {
					fieldName = toTableString(fName);
				}
				Method get = thisClass.getMethod(getf
						+ fName.substring(0, 1).toUpperCase()
						+ fName.substring(1));
				Object getValue = get.invoke(po);
				Pram pram = new Pram(fieldName, getValue);
				list.add(pram);
			}
		}
		return list;
	}

	/**
	 * 获取实体类的数据库里字段的名字和值
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public List<Pram> getAllPramList(Po po) throws Exception {
		List<Pram> list = new ArrayList<Pram>();
		Class<? extends Po> thisClass = po.getClass();
		Field[] fields = thisClass.getDeclaredFields();
		for (Field f : fields) {
			if (!f.isAnnotationPresent(TempField.class)) {
				String fName = f.getName();
				String fieldName = "";
				// 判断是否是boolean类型
				String getf = "get";
				String fieldType = f.getGenericType().toString();
				if (fieldType.indexOf("boolean") != -1
						|| fieldType.indexOf("Boolean") != -1) {
					getf = "is";
				}
				if (f.isAnnotationPresent(FieldName.class)) {
					fieldName = f.getAnnotation(FieldName.class).name();
				} else {
					fieldName = toTableString(fName);
				}
				Method get = thisClass.getMethod(getf
						+ fName.substring(0, 1).toUpperCase()
						+ fName.substring(1));
				Object getValue = get.invoke(po);
				Pram pram = new Pram(fieldName, getValue);
				list.add(pram);
			}
		}
		return list;
	}

	/**
	 * 通过Class获取生成对应Sql查询的字段
	 * 
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListOfSelect(Class<T> po) {
		List<Pram> list = new ArrayList<Pram>();
		Class<? extends Po> thisClass = po;
		Field[] fields = thisClass.getDeclaredFields();
		try {
			Object o = thisClass.newInstance();
			for (Field f : fields) {
				if (!f.isAnnotationPresent(TempField.class)) {
					String fName = f.getName();
					// 判断是否是boolean类型
					String getf = "get";
					String fieldType = f.getGenericType().toString();
					if (fieldType.indexOf("boolean") != -1
							|| fieldType.indexOf("Boolean") != -1) {
						getf = "is";
					}
					if (f.isAnnotationPresent(FieldName.class)) {
						String fieldName = f.getAnnotation(FieldName.class)
								.name();
						Method get = thisClass.getMethod(getf
								+ fName.substring(0, 1).toUpperCase()
								+ fName.substring(1));
						Object getValue = get.invoke(o);
						Pram pram = new Pram(fieldName + " as " + fName,
								getValue);
						list.add(pram);
					} else {
						String fieldName = toTableString(fName);
						Method get = thisClass.getMethod(getf
								+ fName.substring(0, 1).toUpperCase()
								+ fName.substring(1));
						Object getValue = get.invoke(o);
						Pram pram = new Pram(fieldName + " as " + fName,
								getValue);
						list.add(pram);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过Class获取生成对应Sql查询的字段
	 * 
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListByBean(Class<T> po) {
		List<Pram> list = new ArrayList<Pram>();
		Class<?> thisClass = po;
		Field[] fields = thisClass.getDeclaredFields();
		try {
			Object o = thisClass.newInstance();
			for (Field f : fields) {
				if (!f.getName().equalsIgnoreCase("ID")
						&& !f.isAnnotationPresent(TempField.class)) {
					String fName = f.getName();

					// 判断是否是boolean类型
					String getf = "get";
					String fieldType = f.getGenericType().toString();
					if (fieldType.indexOf("boolean") != -1
							|| fieldType.indexOf("Boolean") != -1) {
						getf = "is";
					}
					if (f.isAnnotationPresent(FieldName.class)) {
						String fieldName = f.getAnnotation(FieldName.class)
								.name();
						Method get = thisClass.getMethod(getf
								+ fName.substring(0, 1).toUpperCase()
								+ fName.substring(1));
						Object getValue = get.invoke(o);
						Pram pram = new Pram(fieldName + " as " + fName,
								getValue);
						list.add(pram);
					} else {
						String fieldName = toTableString(fName);
						Method get = thisClass.getMethod(getf
								+ fName.substring(0, 1).toUpperCase()
								+ fName.substring(1));
						Object getValue = get.invoke(o);
						Pram pram = new Pram(fieldName + " as " + fName,
								getValue);
						list.add(pram);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过Class获取生成对应Sql查询的字段
	 * 
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListByBeanOfSelect(Class<T> po) {
		List<Pram> list = new ArrayList<Pram>();
		Class<?> thisClass = po;
		Field[] fields = thisClass.getDeclaredFields();
		try {
			Object o = thisClass.newInstance();
			for (Field f : fields) {
				if (!f.isAnnotationPresent(TempField.class)) {
					String fName = f.getName();
					// 判断是否是boolean类型
					String getf = "get";
					String fieldType = f.getGenericType().toString();
					if (fieldType.indexOf("boolean") != -1
							|| fieldType.indexOf("Boolean") != -1) {
						getf = "is";
					}
					if (f.isAnnotationPresent(FieldName.class)) {
						String fieldName = f.getAnnotation(FieldName.class)
								.name();
						Method get = thisClass.getMethod(getf
								+ fName.substring(0, 1).toUpperCase()
								+ fName.substring(1));
						Object getValue = get.invoke(o);
						Pram pram = new Pram(fieldName + " as " + fName,
								getValue);
						list.add(pram);
					} else {
						String fieldName = toTableString(fName);
						Method get = thisClass.getMethod(getf
								+ fName.substring(0, 1).toUpperCase()
								+ fName.substring(1));
						Object getValue = get.invoke(o);
						Pram pram = new Pram(fieldName + " as " + fName,
								getValue);
						list.add(pram);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取Sql字段名
	 * 
	 * @param po
	 * @return
	 */
	public String getTableName(Class<T> clazz) {
		TableName tName = (TableName) clazz.getAnnotation(TableName.class);
		if (tName == null)
			throw new RuntimeException("错误: " + clazz.getName()
					+ "没有TableName注解");
		return tName.name();
	}

	/**
	 * 获取Sql字段名
	 * 
	 * @param po
	 * @return
	 */
	public String getTableNameByBean(Class<T> po) {
		if (po.isAnnotationPresent(TableName.class)) {
			return po.getAnnotation(TableName.class).name();
		} else {
			String tName = toTableString(po.getSimpleName());
			if ("po".equals(tName.substring(tName.length() - 3,
					tName.length() - 1))) {
				tName = tName.substring(0, tName.length() - 3);
			}
			return tName;
		}
	}

	/** getFileValue
	 * 
	 * @param po
	 * @param fileName
	 * @return
	 */
	public static <T> Serializable getFileValue(Class<T> po, String fileName) {
		try {
			Method method = po.getMethod("get"
					+ fileName.substring(0, 1).toUpperCase()
					+ fileName.substring(1));
			Object o = po.newInstance();
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable) invoke;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * getFileValue
	 * 
	 * @param po
	 * @param fileName
	 * @return
	 */
	public Serializable getFileValue(Po po, String fileName) {
		try {
			Class<? extends Po> cla = po.getClass();
			Method method = cla.getMethod("get"
					+ fileName.substring(0, 1).toUpperCase()
					+ fileName.substring(1));
			Object o = po;
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable) invoke;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 驼峰标识转下划线标识 非小写和数字加"_"
	 * 
	 * @param text
	 * @return
	 */
	private String toTableString(String text) {
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

	public String getTableNameByClazz(Class<? extends Po> po) {
		// TODO Auto-generated method stub
		if (po.isAnnotationPresent(TableName.class)) {
			return po.getAnnotation(TableName.class).name();
		} else {
			String tName = toTableString(po.getSimpleName());
			if ("po".equals(tName.substring(tName.length() - 3,
					tName.length() - 1))) {
				tName = tName.substring(0, tName.length() - 3);
			}
			return tName;
		}
	}

}
