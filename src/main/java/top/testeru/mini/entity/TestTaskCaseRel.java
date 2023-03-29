package top.testeru.mini.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 表名：test_task_case_rel
 * 表注释：测试任务表
*/
@Table(name = "test_task_case_rel")
@ToString
@Getter
@Setter
public class TestTaskCaseRel extends BaseEntityNew {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务id
     */
    @Column(name = "task_id")
    private Integer taskId;

    /**
     * 用例id
     */
    @Column(name = "case_id")
    private Integer caseId;

}