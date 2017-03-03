package com.framework.crud.controller.manage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.framework.base.BaseController;
import com.framework.crud.bean.manage.Module;
import com.framework.crud.bean.user.User;
import com.framework.crud.service.manage.ModuleService;
import com.framework.util.ManageUtils;
import com.framework.util.SystemUtilities;

/**
 * 模块管理
 * 
 * @author yyf
 * 
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController<Module, ModuleService> {
	@Autowired
	private ModuleService moduleService;

	/**
	 * 获取该登录用户所能显示的模块信息
	 * 
	 * @param request
	 * @param model
	 * @param parentid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/get", method = { RequestMethod.GET })
	public String getModule(HttpServletRequest request, Model model,
			@RequestParam(defaultValue = "") String num) throws Exception {
		List<Module> modules = (List<Module>) request.getSession()
				.getAttribute("MODULES_ALL");
		List<Module> menulist = null;
		 if (SystemUtilities.isNotNullString(num)) {
		menulist = ManageUtils.getModuleByParentId(num, modules);
		 }
		String zNodes = moduleService.toJsonOfModules((List<Module>) request
				.getSession().getAttribute("MODULES_ALL"), null);
		model.addAttribute("menulist", menulist);
		model.addAttribute("zNodes", zNodes);
		return viewpath("modulelist");
	}

	/**
	 * 进入新增或修改页面
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,
			@RequestParam(defaultValue = "") String id) {
		if (StringUtils.isNotBlank(id)) {
			Module module;
			try {
				module = moduleService.getModuleByNum(id);
				String code = module.getCode();
				module.setCode(code.substring(code.lastIndexOf("_") + 1, code
						.length()));
				model.addAttribute("module", module);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return viewpath("moduleedit");
	}

	/**
	 * 冻结/激活模块之前的判断[是否冻结所有子模块/上级模块已经冻结,请先激活上级模块]
	 * 
	 * @param response
	 * @param num
	 * @param active
	 */
	@RequestMapping(value = "/chstate", method = { RequestMethod.POST })
	public void decidestatus(HttpServletResponse response,
			@RequestParam(defaultValue = "") String num,
			@RequestParam(defaultValue = "") String active) {
		String code = "";
		try {
			Module module = moduleService.getModuleByNum(num);
			// 如果是激活操作 则需要判断上级是否是冻结状态
			if ("true".equals(active) && !"0".equals(module.getParentid())) {
				Module m = moduleService.getModuleByNum(module.getParentid());
				if ("false".equals(m.getActive())) {
					code = "-2";
					throw new Exception("上级模块处于冻结中,请先激活上级模块!");
				}
			}
			// 获取所有子模块
			List<Module> list = moduleService.getModuleList(new Module(num));
			for (Module md : list) {
				if (!active.equals(md.getActive())) {
					code = "-1";
					throw new Exception(("false".equals(active) ? "使用" : "冻结")
							+ "子模块!");
				}
			}

			ajaxJson("{\"code\" : \"0\", \"msg\" : \"不需要操作子模块!\" }", response);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson("{\"code\" : \"" + code + "\", \"msg\" : \""
					+ e.getMessage() + "\" }", response);
		}
	}

	/**
	 * 直接冻结/激活模块
	 * 
	 * @param response
	 * @param num
	 * @param active
	 */
	@RequestMapping(value = "active", method = { RequestMethod.POST })
	public void active(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(defaultValue = "") String num,
			@RequestParam(defaultValue = "") String active,
			@RequestParam(defaultValue = "false") String flag) {
		try {
			Module module = moduleService.getModuleByNum(num);
			// 冻结/激活 自身模块
			moduleService
					.updateActive(new Module(module.getId(), null, active));
			if ("true".equals(flag)) {
				// 冻结/激活 子模块
				List<Module> list = moduleService.getModuleList(new Module(num));
				for (Module md : list) {
					if (!active.equals(md.getActive()))
						moduleService.updateActive(new Module(md.getId(), null,
								active));
				}
			}
			refreshCache(request);
			ajaxJson("{\"code\" : \"0\", \"msg\" : \""
					+ ("false".equals(active) ? "冻结成功!" : "激活成功!") + "\" }",
					response);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson("{\"code\" : \"-1\", \"msg\" : \""
					+ ("false".equals(active) ? "冻结失败[" : "激活失败[")
					+ e.getMessage() + "]\" }", response);
		}
	}

	
	/**
	 * 删除模块
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public void delete(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String num) {
		try {
			Module module = moduleService.getModuleByNum(num);
			List<Module> list = moduleService.getModuleList(new Module(module.getNum()));
			if(list.size()>0){
				throw new Exception("请先删除该模块的下级模块!");
			}			
			moduleService.baseParDel(new Module(module.getId()));

			ajaxJson("{\"code\" : \"0\", \"msg\" : \"删除成功!\" }", response);
			refreshCache(request);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson("{\"code\" : \"-1\", \"msg\" : \"删除失败:" + e.getMessage() + "\" }", response);
		}
	}
	

	/**
	 * 获取上级模块
	 * 
	 * @param reques
	 * @param level
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getparent", method = { RequestMethod.POST })
	public void getParent(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String level) {
		try {
			List<Module> modules = (List<Module>) request.getSession().getAttribute("MODULES");

			List<Module> list = new ArrayList<Module>();
			StringBuffer json = new StringBuffer("[");
			if ("B".equals(level)) {
				for (Module m : modules)
					if ("A".equals(m.getLevel()))
						list.add(m);
			} else if ("C".equals(level)) {
				for (Module m : modules)
					if ("B".equals(m.getLevel()))
						list.add(m);
			}

			for (int i = 0; i < list.size(); i++) {
				json.append("{ \"key\":\"" + list.get(i).getNum() + "\" , \"value\":\"" + list.get(i).getDescription() + "\" }");
				if (i != list.size() - 1)
					json.append(",");
			}

			json.append("]");
			ajaxJson(json.toString(), response);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson("[{ \"key\" : \"-1\", \"value\" : \"获取失败" + e.getMessage() + "\" }]", response);
		}
	}
	/**
	 * 判断是否存在子模块
	 * 
	 * @param response
	 * @param id
	 * @throws Exception 
	 */
	@RequestMapping(value = "/ifchildmodule", method = { RequestMethod.POST })
	public void ifchildmodule(HttpServletResponse response, @RequestParam(defaultValue = "") String num) throws Exception {
		String result = "false";
		int count = moduleService.getModuleList(new Module(num)).size();
		if (count > 0)
			result = "true";
		ajaxText(result, response);
	}
	
	/**
	 * 刷新缓存
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void refreshCache(HttpServletRequest request) throws Exception {
		List<Module> MODULES = moduleService.getModules(true);
		List<Module> MODULES_ALL = moduleService.getModules(false);
		List<Module> fristMenu = ManageUtils.getModuleByParentId("0", MODULES);
		request.getSession().setAttribute("FRISTMENU", fristMenu);
		request.getSession().setAttribute("MODULES", MODULES);
		request.getSession().setAttribute("MODULES_ALL", MODULES_ALL);
	}
	
	/**
	 * 保存操作
	 * @param request
	 * @param model
	 * @param module
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(HttpServletRequest request, Model model, @ModelAttribute Module module) {
		try {
			User user = (User) request.getSession().getAttribute("USER");

			// 生成模块编号 (一级模块不用拼接上级模块的代码)
			String parentcode = "";
			if (!"0".equals(module.getParentid())) {
				Module m = moduleService.getModuleByNum(module.getParentid());
				parentcode = m.getCode();
				module.setCode(m.getCode() + "_" + module.getCode());
			}

			if ("".equals(module.getIds())) { // 新增
				module.setCode(StringUtils.upperCase(module.getCode()));
				// 判断代码是否已经存在
				if (moduleService.getModuleByCode(module.getCode()).getId()!=0) {
					throw new Exception("模块代码已存在,无法新增!");
				}

				module.setNum(moduleService.getNum(module));
				module.setCreateOper(user.getName());
			} else { // 修改
				module.setId(Integer.parseInt(module.getIds()));
				// 判断代码是否已经存在
				Module module2 =moduleService.getModuleByCode(module.getCode());
				if (module2.getId()!=0&&module2.getCode()!=module2.getCode()) {
					throw new Exception("模块代码已存在,无法修改!");
				}
			}
			moduleService.saveOrUpdate(module);

			if (module.getId() == 0)
				module = moduleService.getModuleByNum(module.getNum());

			model.addAttribute("msg", "[ 操作成功 ]");
			if (StringUtils.isNotBlank(parentcode)) {
				module.setCode(module.getCode().replace(parentcode + "_", ""));
			}
			model.addAttribute("module", module);
			model.addAttribute("ifoper", "true");
			// 刷新缓存
			refreshCache(request);
			model.addAttribute("module", module);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "[ " + e.getMessage() + "! ]");
		}
		model.addAttribute("module", module);
		return viewpath("moduleedit");
	}
	
	

}
