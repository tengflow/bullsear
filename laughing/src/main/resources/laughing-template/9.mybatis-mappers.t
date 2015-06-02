{"":""}
/${resourcePath}/hotel-core-spring/${entityReferName}.mybatis-mappers.xml
<bean id="$mapperReferName" class="org.mybatis.spring.mapper.MapperFactoryBean">
    <property name="mapperInterface" value="${entityMapperPackageName}.${entityMapperClassName}"/>
</bean>
