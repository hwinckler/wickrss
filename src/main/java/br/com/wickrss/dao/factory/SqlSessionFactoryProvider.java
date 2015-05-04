package br.com.wickrss.dao.factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import javax.inject.Singleton;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;

@Singleton
public class SqlSessionFactoryProvider implements Provider<SqlSessionFactory>{

	private static final Logger logger = LoggerFactory.getLogger(SqlSessionFactoryProvider.class);

	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory get() {
		try {

			if (sqlSessionFactory == null) {

				logger.debug("obtendo arquivo de configuração do mybatis...");
				
				try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
				
					logger.debug("criando SqlSessionFactory...");

					sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				}
			}

		}

		catch (FileNotFoundException e) {
			logger.error("error", e);
		} catch (IOException e) {
			logger.error("error", e);
		}
		
		return sqlSessionFactory;
	}

}
