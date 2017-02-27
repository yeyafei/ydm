package com.framework.crud.bean.manage;

import java.util.List;

import com.framework.base.BaseEntity;
import com.framework.core.annotation.Primary;
import com.framework.core.annotation.TableName;
import com.framework.core.annotation.TempField;

/**
 * 功能模块
 * 
 * @author yyf
 */
@TableName(name = "module")
public class Module extends BaseEntity{

	/**
	 * 主键ID
	 */
	private int id;
	@TempField
	private String ids;
	
	/**
	 * 模块编码
	 */
	@Primary
	private String num;
	
	/**
	 * 模块编码
	 */
	@Primary
	private String code;
	
	/**
	 * 模块描述
	 */
	private String description;
	
	/**
	 * 模块url
	 */
	private String url;
	
	/**
	 * 是否激活 true 激活  false 未激活
	 */
	private String active;
	
	/**
	 * 上级ID
	 */
	private String parentid;
	
	/**
	 * 等级 A / B / C ....
	 */
	private String level;
	
	/**
	 * 创建人
	 */
	private String createOper;
	
	/**
	 * 创建时间
	 */
	private String createDate;
	@TempField
	private List<Module> list;
	
	public boolean equals(Object obj) {
		Module m = null;
		if (!(obj instanceof Module)) {
			return false;
		}

		m = (Module) obj;
		return this.id == m.getId();
	}
	
	public int hashCode(){  
	    return 255 + this.getId();  
	}

	public Module() {
	}
	
	public Module(int id, String code, String active) {
		this.id = id;
		this.code = code;
		this.active = active;
	}
	
	
	public Module(int id){
		this.id = id;
	}
	
	public Module(String parentid) {
		this.parentid = parentid;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCreateOper() {
		return createOper;
	}

	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Module> getList() {
		return list;
	}

	public void setList(List<Module> list) {
		this.list = list;
	}
	

}
