package org.simplepersistence;

import org.junit.Before;
import org.junit.Test;
import org.simplepersistence.testmodel.employee.Employee;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FieldAccessorTest {

    private String login = "testLogin";
    private User user = new User(login);
    private Field loginField;

    public class UserSubClass extends User {
        public UserSubClass(String login) {
            super(login);
        }
    }

    @Before
    public void before() throws NoSuchFieldException {
        loginField = User.class.getDeclaredField("login");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenfieldTypeIsDifferentFromExpectedFromTypeOfField() throws Exception {
        FieldAccessor.create(loginField, User.class, Long.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenTypeOfObjectIsDifferentFromDeclaringClassOfField() throws Exception {
        FieldAccessor.create(loginField, Employee.class, String.class);
    }

    @Test
    public void testGet() throws Exception {
        FieldAccessor<User, String> userLogin = FieldAccessor.create(loginField, User.class, String.class);
        assertThat(userLogin.getFrom(user), is(user.getLogin()));
    }

    @Test
    public void testSet() throws Exception {
        FieldAccessor<User, String> userLogin = FieldAccessor.create(loginField, User.class, String.class);
        userLogin.setTo(user, "newLogin");
        assertThat(user.getLogin(), is("newLogin"));
    }

    @Test
    public void testCreateUsingSubClass() {
        FieldAccessor<UserSubClass, String> userLogin = FieldAccessor.create(loginField, UserSubClass.class, String.class);
    }
    
    
    @Test
    public void multipleTests() throws NoSuchMethodException {
        Getter<User,CharSequence> superclassGetter = Getter.create(User.class.getMethod("getName"),User.class,CharSequence.class);
        //Getter<User,String> subClassGetter = Getter.create(User.class.getMethod("getCharSequence"),User.class,String.class);
        Setter<User,String> subclassSetter = Setter.create(User.class.getMethod("setCharSequence", CharSequence.class), User.class, String.class);
        //Setter<User,CharSequence> superclassSetter = Setter.create(User.class.getMethod("setName", String.class), User.class, CharSequence.class);
    }


    public class User {
        private final String login;
        private String name;
        private CharSequence charSequence;

        public User(String login) {
            this.login = login;
        }

        public String getLogin() {
            return login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CharSequence getCharSequence() {
            return charSequence;
        }

        public void setCharSequence(CharSequence charSequence) {
            this.charSequence = charSequence;
        }
    }

    
    
}
