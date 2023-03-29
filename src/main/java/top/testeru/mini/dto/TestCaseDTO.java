package top.testeru.mini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.dto
 * @Description 用例新增实体类
 * @createTime 2023年03月28日 14:54:08
 */
@ApiModel(value="添加测试用例实体类")
@Getter
@Setter
@ToString
public class TestCaseDTO {
    @ApiModelProperty(value="测试用例id",required=true)
    private Integer id;
    /**
     * 用例名称
     */
    @ApiModelProperty(value="测试用例名称",required=true)
    private String caseName;

    /**
     * 测试用例数据
     */
    @ApiModelProperty(value="测试用例数据",
                        notes = "文件类型case时不传值", required=true)
    private String caseData;

    /**
     * 备注
     */
    @ApiModelProperty(value="测试用例备注")
    private String remark;
}
