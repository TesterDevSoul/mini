package top.testeru.mini.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.testeru.mini.common.MySqlExtensionMapper;
import top.testeru.mini.entity.TestCase;
//@Mapper
@Repository
public interface TestCaseMapper extends MySqlExtensionMapper<TestCase> {
}