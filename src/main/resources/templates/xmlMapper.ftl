<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.${entity}Mapper">

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#list columns as column>
            ${column.name}<#if column_has_next>,</#if>
        </#list>
    </sql>

    <!-- 查询所有记录（带条件） -->
    <select id="selectAll" parameterType="${entity}" resultType="${entity}">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table}
        <where>
            <#list columns as column>
                <if test="${column.name} != null and ${column.name} != ''">
                    AND ${column.name} = ${column.placeHolderName}
                </if>
            </#list>
        </where>
    </select>

    <!-- 根据 ID 查询用户 -->
    <select id="selectById" parameterType="long" resultType="${entity}">
        select <include refid="Base_Column_List"/> from ${table} where id = ${id};
    </select>


    <!-- 插入记录 -->
    <insert id="insert" parameterType="${entity}" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ${table} (
        <#list columns as column>
            ${column.name}<#if column_has_next>,</#if>
        </#list>
        ) VALUES (
        <#list columns as column>
            ${column.placeHolderName}<#if column_has_next>,</#if>
        </#list>
        )
    </insert>

    <!-- 更新记录 -->
    <update id="update" parameterType="${entity}">
        UPDATE ${table}
        <set>
            <#list columns as column>
                <#if column.name != 'id'>
                    ${column.name} = ${column.placeHolderName}<#if !column?is_last>,</#if>
                </#if>
            </#list>
        </set>
        WHERE id = ${id}
    </update>

    <!-- 根据ID删除 -->
    <delete id="deleteById" parameterType="Long">
        DELETE FROM ${table} WHERE id = ${id}
    </delete>



</mapper>
