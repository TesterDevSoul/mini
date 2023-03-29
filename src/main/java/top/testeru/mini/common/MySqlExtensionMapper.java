package top.testeru.mini.common;


import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.common
 * @Description
 * @createTime 2023年03月27日 22:28:12
 */

public interface MySqlExtensionMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
}
