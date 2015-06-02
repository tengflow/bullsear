{"":""}
/${resourcePath}/mybatis/${moduleName}/${entityMapperClassName}.xml
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
 		FROM $tableName $entityReferName
 		<include refid="CONDITIONS"/>
 	</delete>
 	
 	<select id="findList" parameterType="map" resultMap="${entityClassName}">
 		SELECT  *
 		FROM (
 				SELECT row_number() over( order by ${entityReferName}.id ) as rownum_ , <include refid="COLUMNS"/>
 			  	FROM  $tableName $entityReferName
		 		<include refid="CONDITIONS"/>
	 	) T
 		<where>
			<if test="null != startIndex">
				AND  T.rownum_ > #{startIndex}
		  	</if>
			<if test="null != endIndex">
				<![CDATA[ AND  T.rownum_ <= #{endIndex} ]]>
		  	</if>
 		</where>
 	</select>
 	
 	<select id="findCount" parameterType="map" resultType="int">
 		SELECT COUNT(1)
 		FROM $tableName $entityReferName
 		<include refid="CONDITIONS"/>
 	</select>
	<!-- 返回实体map -->
	<resultMap id="${entityClassName}" type="${entityPackageName}.${entityClassName}">
	<!--
#foreach($attr in $!attrs)
		<result column="$attr.aliasInDb" property="$attr.aliasInJava" jdbcType="$attr.typeInJdbc"/>
#end
	-->
	</resultMap>

	<sql id="CONDITIONS" >
		<where>
#foreach($attr in $!attrs)
			<if test="null != $attr.aliasInJava">
				AND  ${entityReferName}.$attr.aliasInDb = #{$attr.aliasInJava}
			</if>
#end
		</where>
	</sql>
	
	<sql id="COLUMNS" >
#foreach($attr in $!attrs)
#if($foreach.hasNext)
		${entityReferName}.$attr.aliasInDb $attr.aliasInJava ,
#end
#if(!$foreach.hasNext)
		${entityReferName}.$attr.aliasInDb $attr.aliasInJava
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