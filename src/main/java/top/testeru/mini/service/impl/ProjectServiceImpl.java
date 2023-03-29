package top.testeru.mini.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.testeru.mini.common.Constants;
import top.testeru.mini.converter.TestCaseConverter;
import top.testeru.mini.converter.TestTaskConverter;
import top.testeru.mini.dao.TestCaseMapper;
import top.testeru.mini.dao.TestTaskCaseRelMapper;
import top.testeru.mini.dao.TestTaskMapper;
import top.testeru.mini.dto.TestCaseDTO;
import top.testeru.mini.dto.TestTaskAddDTO;
import top.testeru.mini.dto.TestTaskDTO;
import top.testeru.mini.entity.TestCase;
import top.testeru.mini.entity.TestTask;
import top.testeru.mini.entity.TestTaskCaseRel;
import top.testeru.mini.service.ProjectService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.service.impl
 * @Description log打印输出
 * @createTime 2023年03月28日 14:16:17
 */
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {


    private TestCaseMapper testCaseMapper;
    private TestTaskMapper testTaskMapper;



    private TestTaskCaseRelMapper testTaskCaseRelMapper;

    private TestCaseConverter testCaseConverter;
    private TestTaskConverter testTaskConverter;

    @Autowired
    public void setTestTaskCaseRelMapper(TestTaskCaseRelMapper testTaskCaseRelMapper) {
        this.testTaskCaseRelMapper = testTaskCaseRelMapper;
    }
    @Autowired
    public void setTestTaskMapper(TestTaskMapper testTaskMapper) {
        this.testTaskMapper = testTaskMapper;
    }
    @Autowired
    public void setTestTaskConverter(TestTaskConverter testTaskConverter) {
        this.testTaskConverter = testTaskConverter;
    }
    @Autowired
    public void setTestCaseConverter(TestCaseConverter testCaseConverter) {
        this.testCaseConverter = testCaseConverter;
    }

    @Autowired
    public void setTestCaseMapper(TestCaseMapper testCaseMapper) {
        this.testCaseMapper = testCaseMapper;
    }

    @Override
    public int createTestCase(TestCaseDTO testCaseDto) {
        //TestCaseDTO 转为数据库表 TestCase 2种方式：BeanUtils
        log.info("添加用例传入:{}",testCaseDto);
        //Spring自带工具类 - BeanUtils
        //TestCase testCase = new TestCase();
        //BeanUtils.copyProperties(testCaseDto, testCase);
        //mapstruct
        TestCase testCase = testCaseConverter.testCaseDTOForTestCase(testCaseDto);
        //实体类转换完成后，往数据库添加 mapper操作
        testCase.setCreateTime(new Date());//添加创建时间
        testCase.setUpdateTime(new Date());//添加更新时间
        log.info("数据库添加:{}", testCase);
        //Mybatis 是否开启主键自动生成，并将自动生成的主键值回填到对象中。
        //Mybatis 会使用数据库的自增长功能生成一个主键值，
        // 并将该值回填到对象中，从而避免了手动设置主键值的繁琐操作。
        return testCaseMapper.insertUseGeneratedKeys(testCase);
    }

    @Override
    public List<TestCaseDTO> getTestCaseList() {
        List<TestCase> testCaseList = testCaseMapper.selectAll();
        System.out.println(testCaseList);
        return testCaseConverter.testCaseListForTestCaseDTOList(testCaseList);
    }

    @Override
    public TestCaseDTO getTestCase(TestCaseDTO testCaseDTO){
        TestCase testCase = testCaseConverter.testCaseDTOForTestCase(testCaseDTO);
        testCase = testCaseMapper.selectOne(testCase);
        System.out.println(testCase);
        return testCaseConverter.testCaseForTestCaseDTO(testCase);
    }

    @Override
    //由于不只是往任务表里面插入数据，还需要往关联表中添加数据，所以开启事务
    @Transactional(rollbackFor = Exception.class)
    //只要有报错就回滚 指定事务回滚的异常类型，如果发生该类型的异常，事务将回滚。
    public int createTask(TestTaskAddDTO testTaskDto) {
        log.info("创建任务传入:{}",testTaskDto);
        //根据用例ID查询出来所有的用例
        List<Integer> testCaseIdList = testTaskDto.getTestCaseIdList();
        //将List转换成一个Stream对象
        String testCaseIds = testCaseIdList.stream()
        //使用map()方法将每个Integer对象转换为它的字符串表示
                                .map(String::valueOf)
        //使用Collectors.joining()方法将所有字符串连接起来，中间用逗号加空格分隔。
                                .collect(Collectors.joining(", "));
        List<TestCase> testCases = testCaseMapper.selectByIds(testCaseIds);
        //根据传入的ID查找测试用例
        System.out.println(testCases);
        //如果测试用例不为空
        if(Objects.nonNull(testCases) && testCases.size()>0) {
            TestTask testTask = testTaskConverter.testTaskAddDtoForTestTask(testTaskDto);
            testTask.setStatus(Constants.STATUS_ONE);
            testTask.setCreateTime(new Date());
            testTask.setUpdateTime(new Date());
            //要执行的命令
            StringBuilder sb = new StringBuilder();
            //默认命令为
            sb.append("pwd");
            testTask.setTestCommand(sb.toString());
            int i = testTaskMapper.insertUseGeneratedKeys(testTask);
            //测试任务和测试用例对应关系添加 test_task_case_rel
            //一个测试任务 对应 多条测试用例，所以放在list内一次性添加到数据库
            List<TestTaskCaseRel> testTaskCaseList = new ArrayList<>();
            //根据查询出来的testcase有的ID进行插入测试任务对应的用例ID
            testCases.forEach(testCase -> {
                TestTaskCaseRel testTaskCaseRel = new TestTaskCaseRel();
                testTaskCaseRel.setTaskId(testTask.getId());
                testTaskCaseRel.setCaseId(testCase.getId());
                testTaskCaseList.add(testTaskCaseRel);
            });
            //数据进行批量插入
            return testTaskCaseRelMapper.insertList(testTaskCaseList);
        }
        return 0;

    }

    @Override
    public List<TestTaskDTO> getTestTaskList() {
        List<TestTask> testTaskList = testTaskMapper.selectAll();
        System.out.println(testTaskList);
        //没有任务列表
        if(Objects.isNull(testTaskList)){
            return null;
        }
        testTaskList.forEach(testTask -> {
            //关联表中根据taskId查询
            //TestTaskCaseRel testTaskCaseRel = new TestTaskCaseRel();
            //testTaskCaseRel.setTaskId(testTask.getId());
            //System.out.println("testTaskCaseRel:" + testTaskCaseRel);

            Example testTaskCaseRel = new Example(TestTaskCaseRel.class);
            Example.Criteria criteria = testTaskCaseRel.createCriteria();
            criteria.andEqualTo("task_id", testTask.getId());
            //根据任务ID查询出来关联的用例ID
            List<TestTaskCaseRel> testTaskCaseRels = testTaskCaseRelMapper.selectByExample(testTaskCaseRel);
            System.out.println("testTaskCaseRels:" + testTaskCaseRels);
        });
        List<TestTaskAddDTO> testTaskAddDTOS = testTaskConverter.testTaskListForTestTaskAddDTOList(testTaskList);
        System.out.println("testTaskAddDTOS:" + testTaskAddDTOS);

        return null;
    }

    @Override
    public TestTaskDTO getTestTask(TestTaskDTO testTaskDTO) {
        TestTask testTask = testTaskConverter.testTaskDtoForTestTask(testTaskDTO);
        //查询任务详情
        testTask = testTaskMapper.selectOne(testTask);

        //查询用例列表
        Example testTaskCaseRel = new Example(TestTaskCaseRel.class);
        Example.Criteria criteria = testTaskCaseRel.createCriteria();
        //声明的实体类属性
        criteria.andEqualTo("taskId", testTask.getId());
        //根据任务ID查询出来关联的用例ID
        List<TestTaskCaseRel> testTaskCaseRels = testTaskCaseRelMapper.selectByExample(testTaskCaseRel);
        System.out.println("testTaskCaseRels:" + testTaskCaseRels);
        List<Integer> caseIdList = new ArrayList<>();
        testTaskCaseRels.forEach(testTaskCaseRel1 -> caseIdList.add(testTaskCaseRel1.getCaseId()));
        //任务拼接
        testTaskDTO = testTaskConverter.testTaskForTestTaskDto(testTask);
        testTaskDTO.setTestCaseIdList(caseIdList);
        System.out.println(testTaskDTO);
        return testTaskDTO;
    }
}
