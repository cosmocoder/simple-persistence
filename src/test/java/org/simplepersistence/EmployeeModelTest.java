package org.simplepersistence;

import org.junit.Before;
import org.junit.Test;
import org.simplepersistence.jdbc.rule.JdbcPersistenceProvider;
import org.simplepersistence.testmodel.employee.PerformanceScore;

import java.sql.Connection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class EmployeeModelTest {

    private ObjectManager entityManager;
    private Connection connection;
    private SchemaManager schemaManager;

    @Before
    public void initialize() {
        connection = TemporaryDatabaseStuff.newConnection();
        PersistenceProvider persistenceProvider = new JdbcPersistenceProvider();
        entityManager = persistenceProvider.getObjectManager();
        schemaManager = persistenceProvider.getSchemaManager();
    }

    @Test
    public void schemaIsOK() {
        /*User user = new User("rodmguerra");
        user.setName("Rodrigo Mallmann Guerra");

        Employee employee = new Employee(user);
        employee.setHireDate(new Date());

        PerformanceScore performance = new PerformanceScore(2010, employee);
          */
        schemaManager.createModelWithDependencies(PerformanceScore.class);
        List<String> listenedStatements = newArrayList();


    }
}
