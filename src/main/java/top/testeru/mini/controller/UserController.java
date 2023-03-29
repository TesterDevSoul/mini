package top.testeru.mini.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.testeru.mini.dto.UserDTO;
import top.testeru.mini.exception.UserNotFoundException;
import top.testeru.mini.service.UserService;
import top.testeru.mini.util.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.controller
 * @Description 用户请求控制类
 * @createTime 2023年03月21日 16:07:10
 */
// @RestController注解为组合注解，等同于Spring中@Controller+@ResponseBody注解
// @RestController告诉类与前端页面进行交互
@RestController
// UserController与前端交互的类
@Api("用户请求")
public class UserController {
    /**
     *   直接注入会标黄
     *   @Autowired
     *   @Qualifier("userService1Impl")
     *   //UserService userService;
     */
    private UserService userService;

    /**
     * @Autowired
     *     public UserController(@Qualifier("userService1Impl") UserService userService) {
     *         this.userService = userService;
     *     }
     *
     */
    @Autowired
    public void setUserService(@Qualifier("userService1Impl") UserService userService) {
        this.userService = userService;
    }

//---------------------  @RequestMapping  ---------------------
    //声明请求方式及路径path
    //path、value 对应内容一致，都为请求路径 没有参数声明时为path
    @RequestMapping("/user")
    //请求方法
    public String get(){
        return "user：张三，年龄：18";
    }

    @RequestMapping(path = "/user1",method = RequestMethod.GET)
    public String get1(){
        return "用户名1：张三，年龄1：18";
    }

//---------------------  @GetMapping  ---------------------
    @GetMapping("/user2")
    public String get2(){
        return "用户名2：张三，年龄2：18";
    }

    //name:给这个mapping分配一个名称，无实质作用，该请求为错误
    @GetMapping(name = "/usererror")
    public String getError(){
        return "用户名3：张三，年龄3：18";
    }

    @GetMapping(path = "/user3", value = "/user3",
                produces = MediaType.TEXT_PLAIN_VALUE)
    public String get3(){
        HashMap<String, String> userMap = new HashMap<>(){{
            put("用户名3","张三");
            put("年龄3","18");
        }};
        return userMap.toString();
    }

    @GetMapping(path = "/user5", value = "/user5",
            //consumes = MediaType.APPLICATION_JSON_VALUE,//没有参数传递
                produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> get5(){
        HashMap<String, Object> userMap = new HashMap<>(){{
            put("name","张三");
            put("age",18);
        }};
        return userMap;
    }

    @GetMapping(path = "/user6", value = "/user6",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO get6(){
        UserDTO user  = new UserDTO();
        user.setName("张三");
        user.setAge(18);
        return user;
    }

//---------------------  带单个参数的@GetMapping  ---------------------
    //请求路径为：http://127.0.0.1:8888/user10/{name}
    @GetMapping(path = "/user10/{name}", value = "/user10/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO get10(@PathVariable(value = "name") String userName){
        System.out.println(userName);
        List<UserDTO> userList = getUsers();
        UserDTO user3 = userList
                        .stream()
                        .filter(user -> userName.equals(user.getName()))
                        .findFirst().get();
                        //.toList()
                        //.get(0);
        return user3;
    }
    @GetMapping(path = "/user11/{name}", value = "/user11/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO get11(@PathVariable String name){
        List<UserDTO> userList = getUsers();
        UserDTO user3 = userList
                .stream()
                .filter(user -> name.equals(user.getName()))
                .findFirst().get();
                //.toList()
                //.get(0);
        return user3;
    }
    @GetMapping(path = "/user12")
    public UserDTO  get12(@RequestParam("name") String uname){
        List<UserDTO> userList = getUsers();
        UserDTO  user3 = userList
                        .stream()
                        .filter(user -> uname.equals(user.getName()))
                    .findFirst().get();
                        //.toList()
                        //.get(0);
        return user3;
    }

    @GetMapping(path = "/user13")
    public UserDTO get13(@RequestParam String name){
        List<UserDTO> userList = getUsers();
        UserDTO user3 = userList
                .stream()
                .filter(user -> name.equals(user.getName()))
                .findFirst().get();
                //.toList()
                //.get(0);
        return user3;
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable("userId") Long userId) {
        // do something with userId
        return userId.toString();
    }

    private static List<UserDTO> getUsers() {
        List<UserDTO> userList = new ArrayList<>();
        UserDTO user1 = new UserDTO();
        user1.setName("张三");
        user1.setAge(18);
        userList.add(user1);
        UserDTO user2 = new UserDTO();
        user2.setName("莉丝");
        user2.setAge(20);
        userList.add(user2);
        System.out.println(userList);
        return userList;
    }


//---------------------  @PostMapping  ---------------------

    @PostMapping("/add")
    public String add(@RequestBody UserDTO  user) {
        //List<UserDTO> userList = new ArrayList<>();
        //userList.add(user);
        //System.out.println(userList);
        userService.add(user);
        return "ok";
    }

    //统一code码返回值
    @PostMapping("/add1")
    public R add1(@RequestBody UserDTO  user) {
        userService.add(user);
        return R.ok();
    }
    @ApiOperation(value = "根据姓名获取用户", notes = "返回一个统一实体类", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400110, message = "异常姓名"),
            @ApiResponse(code = 404120, message = "用户查看不到")})
    @GetMapping(path = "/user66")
    public R get66(@ApiParam(value = "用户姓名", required = true, example = "张三")
                       @RequestParam("name") String uname){
        List<UserDTO> userList = getUsers();
        UserDTO  user3 = userList
                .stream()
                .filter(user -> uname.equals(user.getName()))
                .findFirst().get();
                //.toList()
                //.get(0);
        return R.ok().data(user3);
    }
    @GetMapping(path = "/usererror")
    public R usererror(@RequestParam("name") String uname){
        UserDTO user1 = null;
        if (user1 == null) {
            throw new UserNotFoundException(uname);
        }
        //user1.setName("张三");
        //user1.setAge(18);
        return R.ok().data(user1);
    }
    @GetMapping(path = "/usersall")
    public R usersall(){
        List<UserDTO> userList = new ArrayList<>();
        UserDTO user1 = new UserDTO();
        user1.setName("张三");
        user1.setAge(18);
        userList.add(user1);
        UserDTO user2 = new UserDTO();
        user2.setName("莉丝");
        user2.setAge(20);
        userList.add(user2);
        System.out.println(userList);
        //int a = 10 /0;
        return R.ok().data(userList);
    }
}
