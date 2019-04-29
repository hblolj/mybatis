package com.hblolj.my;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: hblolj
 * @Date: 2019/4/29 9:11
 * @Description: 通用 Mapper 代理类
 * @Version:
 **/
public class SimpleMapperProxy<T> implements InvocationHandler {

    private final SimpleSqlSession sqlSession;

    private final Class<T> mapperInterface;

    public SimpleMapperProxy(SimpleSqlSession simpleSqlSession, Class<T> clazz) {
        this.sqlSession = simpleSqlSession;
        this.mapperInterface = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 按当前方法所属类的全限定名称匹配 nameSpace
        if (method.getDeclaringClass().getName().equals(TestMapperXml.NAME_SPACE)){
            // 获取 sql
            String sql = TestMapperXml.methodSqlMapping.get(method.getName());
            System.out.println(String.format("SQL [%s], parameter [%s] ", sql, args[0]));
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }

        return null;
    }
}
