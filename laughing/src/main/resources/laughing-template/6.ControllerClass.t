{"entityControllerClassName":"${entityName}Controller","entityControllerPackageName":"${basePackageName}.biz.${moduleName}.controller"}
/${javaPath}/#packagetopath("${entityControllerPackageName}.${entityControllerClassName}").java
package ${entityControllerPackageName};

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ${entityServicePackageName}.${entityServiceClassName};
import ${entityPackageName}.${entityClassName};
import ${entityQueryPackageName}.${entityQueryClassName};

import com.laughing.framework.abs.GenericController;
import com.laughing.framework.page.PageInfo;
import com.laughing.framework.util.JsonUtils2;


@Controller
@RequestMapping("/#lowerall($entityName)")
public class ${entityControllerClassName}  extends GenericController{
    @Resource
    protected ${entityServiceClassName} ${serviceReferName};
    
    @RequestMapping("create.init")
    public String createInit() {
        return "${moduleName}/${entityReferName}.create";
    }
    @RequestMapping("edit.init")
    public String editInit(@ModelAttribute("${entityReferName}") ${entityClassName} ${entityReferName}, Model model) {
        ${entityClassName} ${entityReferName}InDb=${serviceReferName}.get(${entityReferName}.getId());
        model.addAttribute("${entityReferName}", ${entityReferName}InDb);
        return "${moduleName}/${entityReferName}.edit";
    }
    
    @RequestMapping("create")
    public String create(@ModelAttribute("${entityReferName}") ${entityClassName} ${entityReferName}, Model model) {
        ${serviceReferName}.create(${entityReferName});
        return "redirect:edit.init?id="+${entityReferName}.getId();
    }
    
    @RequestMapping("delete")
    public String delete(@ModelAttribute("${entityReferName}") ${entityClassName} ${entityReferName}, Model model) {
        ${serviceReferName}.deleteByPk(${entityReferName}.getId());
        return "redirect:search";
    }
    @RequestMapping("edit")
    public String edit(@ModelAttribute("${entityReferName}") ${entityClassName} ${entityReferName}, Model model) {
        ${serviceReferName}.update(${entityReferName});
        model.addAttribute("${entityReferName}", ${entityReferName});
        return "redirect:search";
    }
    @RequestMapping("search")
    public String search(@ModelAttribute("${entityQueryName}") ${entityQueryClassName} cons, Model model) {
        PageInfo<${entityClassName}> pageInfo = ${serviceReferName}.findList(cons.getPageSize(), cons.getCurrentPage(),
                JsonUtils2.pojo2map(cons));
        model.addAttribute("${entityQueryName}", cons);
        model.addAttribute("pageInfo", pageInfo);
        return "${moduleName}/${entityReferName}.list";
    }
}
