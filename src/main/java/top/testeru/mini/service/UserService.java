package top.testeru.mini.service;

import org.springframework.stereotype.Service;
import top.testeru.mini.dto.UserDTO;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.service
 * @Description UserService现在变得更具自我描述性
 * @createTime 2023年03月23日 19:37:57
 */
//启动运行报错NoSuchBeanDefinitionException
//@Service
public interface UserService {
    public UserDTO add(UserDTO userDTO);
}
