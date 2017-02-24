package com.framework.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.core.domain.Po;
import com.framework.core.domain.Pram;
import com.framework.core.domain.SqlCore;
import com.framework.util.DateUtils;

/**
 * CURD SQL拼接操作类
 * 
 * @author yyf
 *
 * @param <T>
 */
public class BaseSQL<T extends Po> {

	private SqlCore<?> sc;

	private BaseSQL() {
		this.sc = SqlCore.getInstance();
	}

	private static BaseSQL<?> instance = null;

	@SuppressWarnings("unchecked")
	public static synchronized BaseSQL getInstance() {
		if (instance == null)
			instance = new BaseSQL();
		return instance;
	}

	/**
	 * 数据库新增操作
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public String baseSave(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		String sql = "insert into " + tableName + " (";
		String prams = "";
		String values = "";
		List<Pram> pramList = this.sc.getPramList(t);
		for (int i = 0; i < pramList.size(); i++) {
			prams += pramList.get(i).getFile();
			if (pramList.get(i).getValue() == null) {
				values += "null";
			} else {
				values += "'" + pramList.get(i).getValue() + "'";
			}
			if (i < pramList.size() - 1) {
				prams += ",";
				values += ",";
			}
		}
		sql += prams + ") value (" + values + ");";
		System.out.println(sql);
		return sql;
	}

	/**
	 * 数据库删除操作 根据实体类的Primary值或者ID 实体类中有一个唯一值即可（Primary 或 ID） 注：物理删除
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseParDel(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		Pram pram = this.sc.getPrimaryName(t);
		if (pram == null) {
			throw new Exception("实体类无查询条件数据！");
		}
		String sql = "delete from " + tableName + " where " + pram.getFile()
				+ "='" + pram.getValue() + "'";
		System.out.println(sql);
		return sql;
	}

	/**
	 * 数据库删除操作 根据实体类的Primary值或者ID 实体类中有一个唯一值即可（Primary 或 ID） 注：逻辑删除
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseLogParDel(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		Pram pram = this.sc.getPrimaryName(t);
		if (pram == null) {
			throw new Exception("实体类无查询条件数据！");
		}
		String sql = "update " + tableName + " set flag ='1' where "
				+ pram.getFile() + "='" + pram.getValue() + "'";
		System.out.println(sql);
		return sql;
	}

	/**
	 * 数据库跟新操作 根据实体类的Primary值或者ID（实体类顺序查找的第一个作为跟新依据） 实体类中有一个唯一值即可（Primary 或 ID）
	 * 注1：如果根据查到的第一个之后的唯一值来跟新前面的唯一值 此方法无效 注2:
	 * 由于实体类默认String为"",不支持更新""操作,如有必要可以更新" "
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseUpdate(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		Pram pram = this.sc.getPrimaryName(t);
		if (pram == null) {
			throw new Exception("实体类无查询条件数据！");
		}
		List<Pram> pramList = this.sc.getPramList(t);
		String s = "";
		for (int i = 0; i < pramList.size(); i++) {
			if (pramList.get(i).getValue() != null
					&& !"".equals(pramList.get(i).getValue().toString().trim())) {
				s += pramList.get(i).getFile() + "= '"
						+ pramList.get(i).getValue() + "'";
				if (i < pramList.size() - 1) {
					s += ",";

				}
			}
		}
		if ("".equals(s)) {
			throw new Exception("实体类无更新数据！");
		}
		if (s.endsWith(",")) {
			s = s.substring(0, s.length() - 1);
		}
		String sql = "update " + tableName + " set " + s + " where "
				+ pram.getFile() + "='" + pram.getValue() + "'";
		System.out.println(sql);
		return sql;
	}

	/**
	 * 数据库查询操作 根据实体类的Primary值或者ID（实体类顺序查找的第一个作为跟新依据） 实体类中有一个唯一值即可（Primary 或 ID）
	 * 注：此sql返回值为一个对象
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseParSelect(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		Pram pram = this.sc.getPrimaryName(t);
		if (pram == null) {
			throw new Exception("实体类无查询条件数据！");
		}
		String sql = "select * from " + tableName + " where " + pram.getFile()
				+ "='" + pram.getValue() + "'";
		System.out.println(sql);
		return sql;
	}

	/**
	 * 数据库查询操作 查询实体类数据库所有的信息
	 * 注：有flag为0限制
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseSelectAll(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		String sql = "select * from " + tableName + " where flag ='0'" ;
		System.out.println(sql);
		return sql;
	}

	/**
	 * 数据库查询操作 根据实体类的值进行绝对查询 注：无时间大小比较
	 * （含有分页信息）
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseSelectEq(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		List<Pram> pramList = this.sc.getAllPramList(t);
		String sql = "select * from " + tableName + " where 1=1 ";
		for (int i = 0; i < pramList.size(); i++) {
			if ("id".equals(pramList.get(i).getFile().toLowerCase())) {
				if (!"0".equals(pramList.get(i).getValue().toString()))
					sql += "and " + pramList.get(i).getFile() + " = '"
							+ pramList.get(i).getValue() + "' ";
			} else {
				if (pramList.get(i).getValue() != null
						&& !"".equals(pramList.get(i).getValue().toString()
								.trim()))
					sql += "and " + pramList.get(i).getFile() + " = '"
							+ pramList.get(i).getValue() + "' ";
			}
		}
		if(t.getPageSize()!=0){
			sql +=" ORDER BY id DESC limit "+t.getStartNum() +"," +t.getPageSize();
		}
		System.out.println(sql);
		return sql;
	}

	/**
	 * 数据库查询操作 根据实体类的值进行绝对查询 注：无时间大小比较
	 * （含有分页信息）
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseSelectEqCount(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		List<Pram> pramList = this.sc.getAllPramList(t);
		String sql = "select count(*) from " + tableName + " where 1=1 ";
		for (int i = 0; i < pramList.size(); i++) {
			if ("id".equals(pramList.get(i).getFile().toLowerCase())) {
				if (!"0".equals(pramList.get(i).getValue().toString()))
					sql += "and " + pramList.get(i).getFile() + " = '"
							+ pramList.get(i).getValue() + "' ";
			} else {
				if (pramList.get(i).getValue() != null
						&& !"".equals(pramList.get(i).getValue().toString()
								.trim()))
					sql += "and " + pramList.get(i).getFile() + " = '"
							+ pramList.get(i).getValue() + "' ";
			}
		}
		System.out.println(sql);
		return sql;
	}
	
	
	
	
	/**
	 * 数据库查询操作 根据实体类的值进行模糊查询 注：无时间大小比较
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String baseSelectLike(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		List<Pram> pramList = this.sc.getAllPramList(t);
		String sql = "select * from " + tableName + " where 1=1 ";
		for (int i = 0; i < pramList.size(); i++) {
			if ("id".equals(pramList.get(i).getFile().toLowerCase())) {
				if (!"0".equals(pramList.get(i).getValue().toString()))
					sql += "and " + pramList.get(i).getFile() + " = '"
							+ pramList.get(i).getValue() + "' ";
			} else {
				if (pramList.get(i).getValue() != null
						&& !"".equals(pramList.get(i).getValue().toString()
								.trim()))
					sql += "and " + pramList.get(i).getFile() + " like '%"
							+ pramList.get(i).getValue() + "%' ";
			}
		}
		System.out.println(sql);
		return sql;
	}

	/**
	 * 用于获取code操作 进行了数据库比较操作 注：人为定义code格式 注：实体T数据库code字段必须要有@iscode注解
	 * 此注解的value值为Code头部 注：实体T数据库必须有@Tabel注解
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> baseGetCode(T t) throws Exception {
		String tableName = this.sc.getTableName(t);
		Map<String, String> cm = this.sc.getCodeMess(t);
		Map<String, String> rm = new HashMap<String, String>();
		String date = DateUtils.getStringDateShort().replaceAll("-", "")
				.substring(2, 6);
		date = cm.get("type") + date;
		String sql = "select max(" + cm.get("name") + ") from " + tableName
				+ " where " + cm.get("name") + " like '" + date + "%'";
		rm.put("sql", sql);
		rm.put("date", date);
		rm.put("type", cm.get("type"));
		return rm;
	}

}
