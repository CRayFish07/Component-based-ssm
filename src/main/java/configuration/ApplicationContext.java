package configuration;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Spring配置文件
 * @author sh4n7ie  firheroicths@gmail.com
 * @date   2016年5月13日 下午3:20:55
 * @since 0.1
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(
    {
        "classpath:jdbc.properties"
    })
@ComponentScan(
		basePackages="com.dofittech.cbdp",
		excludeFilters={@Filter(value=org.springframework.stereotype.Controller.class)}
		)
public class ApplicationContext
{

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    @Autowired
    public DataSource dataSource()
    {	
    	// 阿里 druid数据库连接池
        DruidDataSource dataSource = new DruidDataSource();
        // 数据库基本信息配置
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));  //env.getProperty("")
        dataSource.setUrl(env.getProperty("jdbc.url"));
        try {
			dataSource.setFilters(env.getProperty("jdbc.filters"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
        //最大并发连接数 
        dataSource.setMaxActive(Integer.valueOf(env.getProperty("jdbc.maxActive")));
        //初始化连接数量
        dataSource.setInitialSize(Integer.valueOf(env.getProperty("jdbc.initialSize")));
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(Integer.valueOf(env.getProperty("jdbc.maxWait")));
        //最小空闲连接数 
        dataSource.setMinIdle(Integer.valueOf(env.getProperty("jdbc.minIdle")));
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(env.getProperty("jdbc.timeBetweenEvictionRunsMillis")));
        //配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(env.getProperty("jdbc.minEvictableIdleTimeMillis")));
        dataSource.setValidationQuery(env.getProperty("jdbc.validationQuery"));
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("jdbc.testWhileIdle")));
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("jdbc.testOnBorrow")));
        dataSource.setTestOnReturn(Boolean.valueOf(env.getProperty("jdbc.testOnReturn")));
        dataSource.setMaxOpenPreparedStatements(Integer.valueOf(env.getProperty("jdbc.maxOpenPreparedStatements")));
        //打开removeAbandoned功能
        dataSource.setRemoveAbandoned(Boolean.valueOf(env.getProperty("jdbc.removeAbandoned")));
        // 1800秒，也就是30分钟
        dataSource.setRemoveAbandonedTimeout(Integer.valueOf(env.getProperty("jdbc.removeAbandonedTimeout")));
        //关闭abanded连接时输出错误日志
        dataSource.setLogAbandoned(Boolean.valueOf(env.getProperty("jdbc.logAbandoned")));
        //------------------------------
        return dataSource;
    }

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception
    {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        //设置mybatis-config路径
        sqlSessionFactory.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));
        //扫描Mapper  classpath*:sqlmap/*-mapper.xml"
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*/*.xml"));
        return sqlSessionFactory.getObject();
    }

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource)
    {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        return txManager;
    }
    
}
