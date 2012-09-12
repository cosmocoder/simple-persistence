package org.simplepersistence;

import org.junit.Before;
import org.junit.Test;
import org.simplepersistence.testmodel.employee.PerformanceScore;

import java.sql.Connection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.simplepersistence.utils.DatabaseTestUtils.newConnection;
import static org.simplepersistence.utils.TestFactory.newEntityManager;
import static org.simplepersistence.utils.TestFactory.newSchemaManager;

public class EmployeeModelTest {

    private EntityManager entityManager;
    private Connection connection;
    private SchemaManager schemaManager;

    @Before
    public void initialize() {
        connection = newConnection();
        entityManager = newEntityManager();
        schemaManager = newSchemaManager();
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
