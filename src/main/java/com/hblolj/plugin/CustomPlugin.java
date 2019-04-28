package com.hblolj.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author: hblolj
 * @Date: 2019/4/28 10:55
 * @Description:
 * @Version:
 **/
// 表示拦截 Executor 的 query 方法，query 方法的参数为 args 中标注的
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class CustomPlugin implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = ((MappedStatement) invocation.getArgs()[0]);
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        System.out.println(boundSql.getSql());
        // 获取到查询结果
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
