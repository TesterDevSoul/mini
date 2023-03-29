package top.testeru.mini.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.testeru.mini.dto.TestCaseDTO;
import top.testeru.mini.dto.TestTaskAddDTO;
import top.testeru.mini.dto.TestTaskDTO;
import top.testeru.mini.service.ProjectService;
import top.testeru.mini.util.R;
import top.testeru.mini.util.ResultCode;

import java.util.List;
import java.util.Objects;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.controller
 * @Description 项目任务请求控制类
 * @createTime 2023年03月28日 11:26:12
 */
@RestController
@RequestMapping("project")
//项目任务请求与前端交互的类
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
    //新增测试用例
    @ApiOperation(value = "新增测试用例")
    @PostMapping("/case")
    public R createTestCase(@RequestBody TestCaseDTO testCaseDto) {
        /**
         * 测试用例 不能Null、用例名不能为空、用例数据不能为空
         */
        if(Objects.isNull(testCaseDto)){
            return R.error(ResultCode.PARAMETER_ERROR);
        }

        if(!StringUtils.hasText(testCaseDto.getCaseName())){
            return R.error(ResultCode.PARAMETER_ERROR).message("用例名称不能为空");
        }
        if(!StringUtils.hasText(testCaseDto.getCaseData())){
            return R.error(ResultCode.PARAMETER_ERROR).message("用例数据不能为空");
        }
        int addCase = projectService.createTestCase(testCaseDto);
        return R.ok().data(testCaseDto).message(addCase + "条测试用例添加成功");
    }

    //用例列表查询
    @ApiOperation(value = "用例列表查询")
    @GetMapping("case/list")
    public R getTestCaseList(){
        List<TestCaseDTO> testCaseList = projectService.getTestCaseList();
        return R.ok().data(testCaseList);
    }

    @ApiOperation(value = "根据ID获取用例数据")
    @GetMapping("case/data/{caseId}")
    public R getOneCase(@PathVariable Integer caseId) {
        TestCaseDTO testCaseDTO = new TestCaseDTO();
        testCaseDTO.setId(caseId);
        //转为对象，下次根据用例名或者根据用例其他的获取用例数据都可以
        testCaseDTO = projectService.getTestCase(testCaseDTO);
        if(Objects.isNull(testCaseDTO))
            return R.error(ResultCode.PARAMETER_NOT_EXIST).message("用例不存在");
        return R.ok().data(testCaseDTO);
    }

    //用例任务的创建
    @ApiOperation(value = "用例任务的创建")
    @PostMapping("/task")
    public R createTask(@RequestBody TestTaskAddDTO testTaskDto) {
        if(Objects.isNull(testTaskDto)){
            return R.error(ResultCode.PARAMETER_ERROR);
        }
        //任务信息对象中名称不能为空
        if(Objects.isNull(testTaskDto.getTaskName())){
            return R.error(ResultCode.PARAMETER_ERROR).message("任务名称不能为空");
        }

        List<Integer> testCaseIdList = testTaskDto.getTestCaseIdList();
        //null || []
        int testCaseIdListsize = testCaseIdList.size();
        if(Objects.isNull(testCaseIdList) || testCaseIdListsize==0){
            return R.error(ResultCode.PARAMETER_ERROR).message("用例选择列表不能为空");
        }
        int task = projectService.createTask(testTaskDto);
        if(task > 0) {
            if(task == testCaseIdListsize)
                return R.ok().data(testTaskDto).message("1条测试任务对应" + task + "条测试用例全部添加成功");
            else
                return R.ok().data(testTaskDto).message("1条测试任务"+ testCaseIdListsize +"，成功添加了" + task + "条测试用例");
        }
        return R.error(ResultCode.PARAMETER_NOT_EXIST).data(testTaskDto);

    }

    @ApiOperation(value = "任务列表查询")
    @GetMapping("task/list")
    public R getTestTaskList(){
        List<TestTaskDTO> testTaskList = projectService.getTestTaskList();
        return R.ok().data(testTaskList);
    }
    @ApiOperation(value = "根据ID获取任务详情")
    @GetMapping("task/data/{taskId}")
    public R getOneTask(@PathVariable Integer taskId){
        TestTaskDTO testTaskDTO = new TestTaskDTO();
        testTaskDTO.setId(taskId);
        //转为对象，下次根据用例名或者根据用例其他的获取用例数据都可以
        testTaskDTO = projectService.getTestTask(testTaskDTO);
        return R.ok().data(testTaskDTO);
    }

    //用例任务的执行
    @ApiOperation(value = "用例任务的执行")
    @PostMapping("/run")
    public R runTask() {
        return R.ok();
    }

    //用例任务状态的修改
    @ApiOperation(value = "用例任务状态的修改")
    @PutMapping("task/status")
    public R updateTaskStatus() {
        return R.ok();
    }

    //获取Allure报告的修改
    @ApiOperation(value = "获取报告")
    @GetMapping("/report/{taskId}")
    public R getReport(@PathVariable Integer taskId) {
        return R.ok();
    }
}