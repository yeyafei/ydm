package com.framework.crud.bean.manage;

import com.framework.base.BaseEntity;
import com.framework.core.annotation.Primary;
import com.framework.core.annotation.TableName;

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
	
	/**
	 * 模块编码
	 */
	@Primary
	private String num;
	
	/**
	 * 模块编码
	 */
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
	private String create_oper;
	
	/**
	 * 创建时间
	 */
	private String create_date;
	
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

	public String getCreate_oper() {
		return create_oper;
	}

	public void setCreate_oper(String createOper) {
		create_oper = createOper;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String createDate) {
		create_date = createDate;
	}
}
