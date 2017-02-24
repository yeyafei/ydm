package com.framework.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * MYBATIS CRUD BaseDao
 * @author yyf
 *
 * @param <T>
 */
public interface BaseDao<T> {
	public boolean baseSave(@Param("sql") String sql);

	public boolean baseParDel(@Param("sql") String sql);

	public boolean baseLogParDel(@Param("sql") String sql);

	public boolean baseUpdate(@Param("sql") String sql);

	public Map<String, Object> baseParSelect(@Param("sql") String sql);

	public List<Map<String, Object>> baseSelectEq(@Param("sql") String sql);

	public List<Map<String, Object>> baseSelectLike(@Param("sql") String sql);

	public String baseGetCode(@Param("sql") String sql);
	
	public List<Map<String,Object>> baseSelectAll(@Param("sql") String sql);
	
	public int baseSelectEqCount(@Param("sql") String sql);

}
