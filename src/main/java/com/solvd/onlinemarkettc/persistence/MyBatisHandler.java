package com.solvd.onlinemarkettc.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisHandler {
    private static final String configName = "mybatis_configuration.xml";
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        sqlSessionFactory = buildFactory();
    }

    private static SqlSessionFactory buildFactory() {
        InputStream stream;
        try {
            stream = Resources.getResourceAsStream(configName);
        } catch (IOException e) {
            throw new RuntimeException("some issue with getting conf", e);
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(stream);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
