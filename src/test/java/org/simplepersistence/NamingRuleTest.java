package org.simplepersistence;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.simplepersistence.utils.DatabaseTestUtils.newConnection;
import static org.simplepersistence.utils.DatabaseTestUtils.queryFromDB;
import static org.simplepersistence.utils.TestFactory.newEntityManager;
import static org.simplepersistence.utils.TestFactory.newSchemaManager;
import static com.google.common.base.Throwables.propagate;
import static java.text.MessageFormat.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NamingRuleTest {

    private static class MyEntity {
        private long id;
        private String name;
        private String nameOfMother;

        public MyEntity(final long id) {
            this.id = id;
        }

        private MyEntity(final long id, final String name, final String nameOfMother) {
            this.id = id;
            this.name = name;
            this.nameOfMother = nameOfMother;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getNameOfMother() {
            return nameOfMother;
        }

        public void setNameOfMother(final String nameOfMother) {
            this.nameOfMother = nameOfMother;
        }
    }

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
    public void defaultNamingRuleIsOK() throws SQLException {
        MyEntity myEntity = new MyEntity(20L, "John Doe", "Mary Jane");
        schemaManager.createModel(MyEntity.class);
        entityManager.insert(myEntity);
        ResultSet resultSet = queryTable("MY_ENTITY");
        assertTrue(resultSet.next());
        assertColumn(resultSet, "NAME", myEntity.getName());
        assertColumn(resultSet, "NAME_OF_MOTHER", myEntity.getNameOfMother());
        assertColumn(resultSet, "ID", myEntity.getId());
    }

    private ResultSet queryTable(final String tableName) {
        try {
            return queryFromDB(connection, format("select * from {0}", tableName));
        } catch (SQLException e) {
            throw propagate(e);
        }
    }

    private void assertColumn(final ResultSet resultSet, final String columnName, final Object value) throws SQLException {
        Object found = resultSet.getObject(columnName);
        if(value instanceof Number) {
            assertThat(found, is(Number.class));
            assertThat(((Number) found).longValue(), is(((Number)value).longValue()));
        }
    }
}
