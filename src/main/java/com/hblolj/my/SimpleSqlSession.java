package com.hblolj.my;

import java.lang.reflect.Proxy;

/**
 * @author: hblolj
 * @Date: 2019/4/29 8:38
 * @Description:
 * @Version:
 **/
public class SimpleSqlSession {

    private SimpleConfiguration configuration;

    private SimpleExecutor executor = new SimpleExecutorImpl();

    public SimpleSqlSession() {
    }

//    public SimpleSqlSession(SimpleConfiguration configuration, SimpleExecutor executor) {
//        this.configuration = configuration;
//        this.executor = executor;
//    }

    // getMapper
    public <T> T getMapper(Class<T> clazz){
        // 按 clazz 获取 mapper
        // 根据 nameSpace 匹配 mapper
        // 返回一个代理 Mapper，代理 Mapper 执行方法时，按方法所属的类的全限定名称匹配 nameSpace 获取对应的 xml
        // 然后按方法名称从 xml 中获取对应的 sql 语句
        // 然后使用 sqlSession 的 selectOne 语句执行 sql
        // sqlSession 中调用 Executor 使用 jdbc 执行 sql
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new SimpleMapperProxy(this, clazz));
    }

    // selectOne
    public <T> T selectOne(String statement, Object parameter){
        return executor.query(statement, parameter);
    }
}
