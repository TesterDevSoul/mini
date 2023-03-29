package top.testeru.mini.service;

import org.springframework.web.bind.annotation.RequestBody;
import top.testeru.mini.dto.TestCaseDTO;
import top.testeru.mini.dto.TestTaskAddDTO;
import top.testeru.mini.dto.TestTaskDTO;

import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.service
 * @Description 项目任务的service
 * @createTime 2023年03月28日 14:09:16
 */

public interface ProjectService {

    //创建测试用例
    int createTestCase(TestCaseDTO testCaseDto);
    //获取所有测试用例
    List<TestCaseDTO> getTestCaseList();
    //根据ID获取具体的测试用例
    TestCaseDTO getTestCase(TestCaseDTO testCaseDTO);

    //创建任务
    int createTask(@RequestBody TestTaskAddDTO testTaskDto);
    //获取任务列表
    List<TestTaskDTO> getTestTaskList();
    //根据ID获取具体的测试任务
    TestTaskDTO getTestTask(TestTaskDTO testTaskDTO);
}
