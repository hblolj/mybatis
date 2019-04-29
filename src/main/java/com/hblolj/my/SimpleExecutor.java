package com.hblolj.my;

/**
 * @author: hblolj
 * @Date: 2019/4/29 8:40
 * @Description:
 * @Version:
 **/
public interface SimpleExecutor {

    <E> E query(String statement, Object parameter);
}
