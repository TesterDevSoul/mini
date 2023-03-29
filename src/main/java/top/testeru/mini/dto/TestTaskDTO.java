package top.testeru.mini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.dto
 * @Description
 * @createTime 2023年03月28日 18:42:28
 */
@ApiModel(value="测试任务实体类")
@Getter
@Setter
@ToString
public class TestTaskDTO {
    @ApiModelProperty(value="测试任务id",required=true)
    private Integer id;
    /**
     * 测试任务名称
     */
    @ApiModelProperty(value="测试任务名称",required=true)
    private String taskName;
    @ApiModelProperty(value="任务构建地址")
    private String buildUrl;
    @ApiModelProperty(value="任务状态")
    private Integer status;
    @ApiModelProperty(value="任务执行命令")
    private String command;
    /**
     * 测试任务备注
     */
    @ApiModelProperty(value="测试任务备注", required=true)
    private String remark;

    @ApiModelProperty(value="测试用例的ID列表",example = "[1,5,10]", required=true)
    private List<Integer> testCaseIdList;



}
