package com.framework.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.core.domain.Core;
import com.framework.util.NumberFormat;

/**
 * MYBATIS CURD BaseService 
 * 一些增删改查的封装
 * @author yyf
 * 
 * @param <T>
 * @param <D>
 */
@Service
public class BaseService<T extends BaseEntity, D extends BaseDao<T>> {

	@Autowired
	public D dao;

	/**
	 * 保存
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean baseSave(T t) throws Exception {
		return dao.baseSave(BaseSQL.getInstance().baseSave(t));
	}

	/**
	 * 保存 用于保存List bean
	 * 
	 * @param ls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean baseSave(List<T> ls) throws Exception {
		Boolean bool = false;
		for (T t : ls) {
			bool = dao.baseSave(BaseSQL.getInstance().baseSave(t));
			if (bool == false)
				return bool;
		}
		return bool;
	}

	/**
	 * 删除 根据实体类的Primary值或者ID 实体类中有一个唯一值即可（Primary 或 ID） 注：物理删除
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean baseParDel(T t) throws Exception {
		return dao.baseParDel(BaseSQL.getInstance().baseParDel(t));
	}

	/**
	 * 删除 根据实体类的Primary值或者ID 实体类中有一个唯一值即可（Primary 或 ID） 注：逻辑删除
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean baseParLogicDel(T t) throws Exception {
		return dao.baseLogParDel(BaseSQL.getInstance().baseLogParDel(t));
	}

	/**
	 * 更新 根据实体类的Primary值或者ID（实体类顺序查找的第一个作为跟新依据） 实体类中有一个唯一值即可（Primary 或 ID）
	 * 注1：如果根据查到的第一个之后的唯一值来跟新前面的唯一值 此方法无效 注2:
	 * 由于实体类默认String为"",不支持更新""操作,如有必要可以更新" "
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean baseUpdate(T t) throws Exception {
		return dao.baseUpdate(BaseSQL.getInstance().baseUpdate(t));
	}

	/**
	 * 查询 根据实体类的Primary值或者ID（实体类顺序查找的第一个作为跟新依据） 实体类中有一个唯一值即可（Primary 或 ID）
	 * 注：此sql返回值为一个对象
	 * 
	 * @param t
	 * @return t
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T baseParSelect(T t) throws Exception {
		Map map = dao.baseParSelect(BaseSQL.getInstance().baseParSelect(t));
		if (map!=null)
			t = (T) Core.M2P(map, t);
		return t;
	}

	/**
	 * 数据库查询操作 根据实体类的值进行绝对查询 注：无时间大小比较
	 * (带分页信息)
	 * @param t
	 * @return list<t>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> baseSelectEq(T t) throws Exception {
		List<Map<String, Object>> maps = dao.baseSelectEq(BaseSQL.getInstance()
				.baseSelectEq(t));
		List<Object> os = Core.ML2PL(maps, t);
		List<T> ts = new ArrayList<T>();
		for (int i = 0; i < os.size(); i++) {
			ts.add((T) os.get(i));
		}
		return ts;
	}

	/**
	 * 数据库查询操作 根据实体类的值进行绝对查询 注：无时间大小比较
	 * 查询总数量
	 * 用于分页功能
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int baseSelectEqCount(T t) throws Exception{
		return dao.baseSelectEqCount(BaseSQL.getInstance()
				.baseSelectEqCount(t));
	}
	
	
	/**
	 * 数据库查询操作 查询实体类数据库所有的信息 注：有flag为0限制
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> baseSelectAll(T t) throws Exception {
		List<Map<String, Object>> maps = dao.baseSelectAll(BaseSQL
				.getInstance().baseSelectAll(t));
		List<Object> os = Core.ML2PL(maps, t);
		List<T> ts = new ArrayList<T>();
		for (int i = 0; i < os.size(); i++) {
			ts.add((T) os.get(i));
		}
		return ts;
	}

	/**
	 * 数据库查询操作 根据实体类的值进行模糊查询 注：无时间大小比较
	 * 
	 * @param t
	 * @return list<t>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> baseSelectLike(T t) throws Exception {
		List<Map<String, Object>> maps = dao.baseSelectLike(BaseSQL
				.getInstance().baseSelectLike(t));
		List<Object> os = Core.ML2PL(maps, t);
		List<T> ts = new ArrayList<T>();
		for (int i = 0; i < os.size(); i++) {
			ts.add((T) os.get(i));
		}
		return ts;
	}

	/**
	 * 通过注解获取Code操作 注：人为定义code格式 注：实体T数据库code字段必须要有@iscode注解 此注解的value值为Code头部
	 * 注：实体T数据库必须有@Tabel注解
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String baseGetCode(T t) throws Exception {
		Map<String, String> m = BaseSQL.getInstance().baseGetCode(t);
		String code = dao.baseGetCode(m.get("sql"));
		if ("".equals(code) || code == null)
			code = m.get("date") + "0001";
		else {
			code = "1" + code.substring(m.get("type").length());
			double d = Double.parseDouble(code) + 1;
			code = m.get("type")
					+ NumberFormat.formatNum(String.valueOf(d), 0, false)
							.substring(1);
		}
		return code;
	}
	/**
	 * 将LIst数据转换成Json数据
	 * @param list
	 * @param pageNum
	 * @param pageAllCount
	 * @return
	 */
	public String getListToTableHtml(List<T> list) {
		 return null;
	}

}
