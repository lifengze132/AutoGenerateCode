

<#--import com.flink.learnthird.entity.AccountUser;-->
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ${mapperName} {

    // 查询所有用户
    List<${entity}> selectAll(${entity} ${entityLower});

}
