package top.testeru.mini.entity;

import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

/**
 * 表名：test_task
 * 表注释：测试任务表
*/
@ToString
@Table(name = "test_task")
public class TestTask extends BaseEntityNew {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * Jenkins的构建url
     */
    @Column(name = "build_url")
    private String buildUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0 无效 1 新建 2 执行中 3 执行完成
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * Jenkins执行测试时的命令脚本
     */
    @Column(name = "test_command")
    private String testCommand;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取Jenkins的构建url
     *
     * @return buildUrl - Jenkins的构建url
     */
    public String getBuildUrl() {
        return buildUrl;
    }

    /**
     * 设置Jenkins的构建url
     *
     * @param buildUrl Jenkins的构建url
     */
    public void setBuildUrl(String buildUrl) {
        this.buildUrl = buildUrl;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取状态 0 无效 1 新建 2 执行中 3 执行完成
     *
     * @return status - 状态 0 无效 1 新建 2 执行中 3 执行完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0 无效 1 新建 2 执行中 3 执行完成
     *
     * @param status 状态 0 无效 1 新建 2 执行中 3 执行完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return updateTime - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取Jenkins执行测试时的命令脚本
     *
     * @return testCommand - Jenkins执行测试时的命令脚本
     */
    public String getTestCommand() {
        return testCommand;
    }

    /**
     * 设置Jenkins执行测试时的命令脚本
     *
     * @param testCommand Jenkins执行测试时的命令脚本
     */
    public void setTestCommand(String testCommand) {
        this.testCommand = testCommand;
    }
}