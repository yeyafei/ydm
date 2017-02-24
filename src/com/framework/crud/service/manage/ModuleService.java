package com.framework.crud.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.crud.bean.manage.Module;
import com.framework.crud.dao.manage.ModuleDao;

/**
 * @author yyf
 * 
 */
@Service
public class ModuleService extends BaseService<Module, ModuleDao> {

	/**
	 * 获取所有Modules
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Module> getModules(Boolean active) throws Exception {
		Module module = new Module();
		if(active==true)
			module.setActive("true");
		return baseSelectEq(module);
	}

	/**
	 * 模块List转换成Json数据
	 * 
	 * @param all   所有模块信息
	 * @param has   拥有模块信息
	 * @return
	 */
	public String toJsonOfModules(List<Module> all, List<Module> has) {
		StringBuffer json = new StringBuffer("[");
		for (int i = 0; i < all.size(); i++) {
			Module module = all.get(i);
			json.append("{ id : " + module.getNum() + "," + " pId : "
					+ module.getParentid() + ", " + "name : \""
					+ module.getDescription() + "\"");

			json.append(" ,active:\"" + module.getActive() + "\"");
			json.append(" ,level:\"" + module.getLevel() + "\"");
			json.append(" ,moduleid:\"" + module.getId() + "\"");

			if (has != null && has.size() > 0) {
				for (Module m : has) {
					if (m.getNum().equals(module.getNum()))
						json.append(" ,checked:true ");
				}
			}

			json.append("}");
			if (i != all.size() - 1)
				json.append(",");
		}
		json.append("]");
		return json.toString();
	}

	/**
	 * 根据num获取module
	 * @param num
	 * @return
	 * @throws Exception 
	 */
	public Module getModuleByNum(String num) throws Exception {
		Module module = new Module(num);
		module.setNum(num);
		return baseParSelect(module);
	}

	/**
	 * 获取子模块
	 * @param module
	 * @return
	 * @throws Exception 
	 */
	public List<Module> getModuleList(Module module) throws Exception {
		return baseSelectEq(module);
	}

	/**
	 * 更新Active
	 * @param module
	 * @throws Exception 
	 */
	public void updateActive(Module module) throws Exception {
		baseUpdate(module);
		
	}

}
