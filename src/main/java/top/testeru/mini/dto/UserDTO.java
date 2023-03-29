package top.testeru.mini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.testeru.mini.entity.BaseEntityNew;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.dto
 * @Description 用户业务实体类
 * 可以承载不同层之间的数据交互，与实体类的业务逻辑解耦，
 * 避免了在不同层之间直接传递实体类带来的一些问题，比如字段暴露、依赖问题等等。
 * 方便地扩展新字段，而不会影响原有的实体类结构。
 * @createTime 2023年03月23日 19:14:43
 */

@Getter//getter方法
@Setter//setter方法
@ToString//tostring方法
@ApiModel(description = "业务用户实体类")
public class UserDTO extends BaseEntityNew {

    @ApiModelProperty(value = "用户名", example = "李四", required = true)
    private String name;
    @ApiModelProperty(value = "年龄", example = "100", required = true)
    private Integer age;
}