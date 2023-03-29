package top.testeru.mini.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Repository;
import top.testeru.mini.dto.TestCaseDTO;
import top.testeru.mini.entity.TestCase;

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
public interface TestCaseConverter {
    @Mappings({
            @Mapping(target = "caseName",source = "caseName"),
            @Mapping(target = "caseData",source = "caseData"),
            @Mapping(target = "remark",source = "remark")
    })
    TestCase testCaseDTOForTestCase(TestCaseDTO testCaseDTO);

    @Mappings({
            @Mapping(target = "caseName",source = "caseName"),
            @Mapping(target = "caseData",source = "caseData"),
            @Mapping(target = "remark",source = "remark")
    })
    TestCaseDTO testCaseForTestCaseDTO(TestCase testCase);

    List<TestCaseDTO> testCaseListForTestCaseDTOList(List<TestCase> testCaseList);
}
