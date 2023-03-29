package top.testeru.mini.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import top.testeru.mini.dto.UserDTO;
import top.testeru.mini.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.service.impl
 * @Description
 * @createTime 2023年03月23日 19:39:32
 */
@Service
@Primary
public class UserServiceImpl implements UserService {
    List<UserDTO> userDTOList = new ArrayList<>();
    @Override
    public UserDTO add(UserDTO userDTO) {
        System.out.println("UserServiceImpl");
        userDTOList.add(userDTO);
        System.out.println(userDTOList);
        return userDTO;
    }
}
