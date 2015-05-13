package dbunit.dataset.load;

import java.io.FileInputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.dao.factory.SqlSessionFactoryProvider;

public class DBUnitLoad{

	private static final Logger logger = LoggerFactory.getLogger(DBUnitLoad.class);
	
	public static SqlSessionFactory sessionFactory = new SqlSessionFactoryProvider().get();
	private static IDataSet idataSet = null;
	
	public static void setUp(final String dataSet) throws Exception{
		
		logger.debug("setUp...");
		
		SqlSession session = sessionFactory.openSession();
				
		DatabaseConnection databaseConnection = new DatabaseConnection(session.getConnection());
				
		idataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(dataSet));

        DatabaseOperation.INSERT.execute(databaseConnection, idataSet);
        
        session.close();
        
	}
	
	public static void tearDown() throws Exception{
		
		logger.debug("tearDown...");
		
		SqlSession session = sessionFactory.openSession();
		
		DatabaseConnection databaseConnection = new DatabaseConnection(session.getConnection());	
						
        DatabaseOperation.TRUNCATE_TABLE.execute(databaseConnection, idataSet);
        
        session.close();
 
        
	}
}
