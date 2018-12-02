package com.interview.yoti.robot;

import javax.sql.DataSource;

import org.jooq.ConnectionProvider;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.interview.yoti.robot.dao.HooverSimulationDao;
import com.zaxxer.hikari.HikariDataSource;

/**
 * DB Configuration of Spring Beans will be initiated within this class.
 * 
 * @author Aashutos Kakshepati
 */
@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan({ "com.interview.yoti.*" })
public class DBConfiguration {

	@Autowired
	private Environment environment;

	@Bean(name="dbProps")
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean(name="dataSource")
	public DataSource dataSource(@Qualifier("dbProps") DataSourceProperties props) {
		return DataSourceBuilder.create()
								.type(HikariDataSource.class)
								.url(props.getUrl())
								.username(props.getUsername())
								.password(props.getPassword())
								.build();
	}

	@Bean(name="txnDataSource")
	public TransactionAwareDataSourceProxy transactionAwareDataSource(@Qualifier("dataSource") DataSource dataSource) {
	    return new TransactionAwareDataSourceProxy(dataSource);
	}

	@Bean(name="txnManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name="conProv")
	public DataSourceConnectionProvider connectionProvider(@Qualifier("txnDataSource") DataSource dataSource) {
		return new DataSourceConnectionProvider(dataSource);
	}

	@Bean(name="jooqConfig")
	public DefaultConfiguration configuration(@Qualifier("conProv") ConnectionProvider conProv) {
		DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
		jooqConfiguration.set(conProv);

		String sqlDialectName = environment.getRequiredProperty("spring.jooq.sql-dialect");
		SQLDialect dialect = SQLDialect.valueOf(sqlDialectName);
		jooqConfiguration.set(dialect);

		return jooqConfiguration;
	}

	@Bean(name="dslContext")
	public DefaultDSLContext dsl(@Qualifier("jooqConfig") DefaultConfiguration configuration) {
		DefaultDSLContext context = new DefaultDSLContext(configuration);
		HooverSimulationDao.setDsl(context);
		return context;
	}
}
