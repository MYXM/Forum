package forum.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import forum.web.dao.interfaces.RoleDao;
import forum.web.dao.pojo.Role;
import forum.web.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;
    
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

}
