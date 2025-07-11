


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ${mapperName} {
    // 查询所有用户
    List<${entity}> selectAll(${entity} ${entityLower});

    // 根据 ID 查询用户
    User selectById(Long id);

    // 插入用户
    int insert(${entity} ${entityLower});

    // 更新用户
    int update(${entity} ${entityLower});

    // 删除用户
    int deleteById(Long id);
}