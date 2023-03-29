package top.testeru.mini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.dto
 * @Description 用例新增实体类
 * @createTime 2023年03月28日 14:54:08
 */
@ApiModel(value="添加测试任务实体类")
@Getter
@Setter
@ToString
public class TestTaskAddDTO {

    /**
     * 测试任务名称
     */
    @ApiModelProperty(value="测试任务名称",required=true)
    private String taskName;

    /**
     * 测试任务备注
     */
    @ApiModelProperty(value="测试任务备注", required=true)
    private String remark;

    @ApiModelProperty(value="测试用例的ID列表",example = "[1,5,10]", required=true)
    private List<Integer> testCaseIdList;

}
