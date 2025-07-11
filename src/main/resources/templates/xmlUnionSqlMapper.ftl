<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.${entity}Mapper">

    <!-- 查询所有用户 -->
    <select id="selectAll" parameterType="${entity}"
            resultType="${entity}">

        select * from (
        ${unionSql}
        ) m
        <where>
            <#list columns as column>
                <if test="${column.name} != null">
                    AND m.${column.name} = ${column.placeHolderName}
                </if>
            </#list>
        </where>

    </select>

</mapper>
