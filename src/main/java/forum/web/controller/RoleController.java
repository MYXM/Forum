package forum.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.web.dao.pojo.Role;
import forum.web.service.RoleService;

@Controller
public class RoleController {
    
    @Autowired
    RoleService roleService;
    
    @RequestMapping("/addRole")
    public String haha(HttpServletRequest request,Model model) {

        Role role = new Role();
        role.setRoleName("haqwe");
        role.setRoleCode("a002");
        role.setIfDelete(0);
        role.setCreateTime(new Date());
        role.setModifyTime(new Date());
        roleService.addRole(role);;
        
        return "login";
    }
}
