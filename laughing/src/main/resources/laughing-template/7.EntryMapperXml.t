{"":""}
/${javaPath}/#packagetopath("${entityMapperPackageName}.${entityMapperClassName}").xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${entityMapperPackageName}.${entityMapperClassName}" >
	<insert id="insert" useGeneratedKeys="true" keyProperty="id" >
		INSERT INTO $tableName  (#_INSERT_COLUMNS_($attrs))
		VALUES(#_INSERT_VALUES_($attrs))  
	</insert>
	
	<update id="update" >
		UPDATE $tableName
		<set>
#foreach($attr in $!attrs)
#if(!$attr.aliasInJava.equals("id"))
			<if test="null != $attr.aliasInJava">
				$attr.aliasInDb = #{$attr.aliasInJava} ,
			</if>
#end
#end
		</set>
		<where>
#foreach($attr in $!attrs)
			<if test="null != _$attr.aliasInJava">
				AND  $attr.aliasInDb = #{_$attr.aliasInJava}
		  	</if>
#end
		</where>
	</update>
	
 	<delete id="delete">
		DELETE 
 		FROM $tableName
 		<include refid="CONDITIONS"/>
 	</delete>
 	
 	<select id="findList" parameterType="map" resultMap="${entityClassName}">
 		SELECT  <include refid="COLUMNS"/>
 		FROM (
 				SELECT row_number() over( order by id ) as rownum , i.* 
 			  	FROM  $tableName i
		 		<include refid="CONDITIONS"/>
	 	) t
 		<where>
			<if test="null != startIndex">
				AND  t.rownum > #{startIndex}
		  	</if>
			<if test="null != endIndex">
				<![CDATA[ AND  t.rownum <= #{endIndex} ]]>
		  	</if>
 		</where>
 	</select>
 	
 	<select id="findCount" parameterType="map" resultType="int">
 		SELECT COUNT(1)
 		FROM $tableName
 		<include refid="CONDITIONS"/>
 	</select>
	<!-- 返回实体map -->
	<resultMap id="${entityClassName}" type="${entityPackageName}.${entityClassName}">
#foreach($attr in $!attrs)
		<result column="$attr.aliasInDb" property="$attr.aliasInJava" jdbcType="$attr.typeInJdbc"/>
#end
	</resultMap>

	<sql id="CONDITIONS" >
		<where>
#foreach($attr in $!attrs)
			<if test="null != $attr.aliasInJava">
				AND  $attr.aliasInDb = #{$attr.aliasInJava}
			</if>
#end
		</where>
	</sql>
	
	<sql id="COLUMNS" >
#foreach($attr in $!attrs)
#if($foreach.hasNext)
		$attr.aliasInDb $attr.aliasInJava ,
#end
#if(!$foreach.hasNext)
		$attr.aliasInDb $attr.aliasInJava
#end
#end
	</sql>
</mapper>


#*insert 字段集合 的宏定义  *#
#macro(_INSERT_COLUMNS_ $attrs)
#foreach($attr in $!attrs)
#if(!$attr.aliasInJava.equals("id"))
	$attr.aliasInDb #if($foreach.hasNext),#end
#end
#end
#end
#*insert 字段设置值集合的宏定义  *#
#macro(_INSERT_VALUES_ $attrs)
#foreach($attr in $!attrs)
#if(!$attr.aliasInJava.equals("id"))
	#{$attr.aliasInJava} #if($foreach.hasNext),#end
#end
#end
#end