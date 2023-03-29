package top.testeru.mini.dao;

import org.springframework.stereotype.Repository;
import top.testeru.mini.common.MySqlExtensionMapper;
import top.testeru.mini.entity.TestTask;
@Repository
public interface TestTaskMapper extends MySqlExtensionMapper<TestTask> {
}