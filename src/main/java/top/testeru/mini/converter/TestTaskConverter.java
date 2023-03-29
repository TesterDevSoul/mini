package top.testeru.mini.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Repository;
import top.testeru.mini.dto.TestTaskAddDTO;
import top.testeru.mini.dto.TestTaskDTO;
import top.testeru.mini.entity.TestCase;
import top.testeru.mini.entity.TestTask;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.converter
 * @Description
 * @createTime 2023年03月28日 16:26:20
 */
//生成的映射器是一个单例范围的 Spring bean，可以通过以下方式检索@Autowired
@Mapper(componentModel = "spring")
@Repository
public interface TestTaskConverter {
    //target：TestTask   source：TestTaskAddDTO
    @Mappings({
            @Mapping(target = "name",source = "taskName"),
            @Mapping(target = "remark",source = "remark")
    })
    TestTask testTaskAddDtoForTestTask(TestTaskAddDTO testTaskDTO);

    List<TestTaskAddDTO> testTaskListForTestTaskAddDTOList(List<TestTask> testTaskList);

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "name",source = "taskName"),
            @Mapping(target = "buildUrl",source = "buildUrl"),
            @Mapping(target = "status",source = "status"),
            @Mapping(target = "testCommand",source = "command"),
            @Mapping(target = "remark",source = "remark")
    })
    TestTask testTaskDtoForTestTask(TestTaskDTO testTaskDTO);
    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "taskName",source = "name"),
            @Mapping(target = "buildUrl",source = "buildUrl"),
            @Mapping(target = "status",source = "status"),
            @Mapping(target = "command",source = "testCommand"),
            @Mapping(target = "remark",source = "remark")
    })
    TestTaskDTO testTaskForTestTaskDto(TestTask testTask);
}
