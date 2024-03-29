package dbService.dataSets;

import javax.persistence.*;     // Поддержка необходимых аннотаций
import java.io.Serializable;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 *
 * DataSet (набор данных) - объект, содержащий данные одной строки таблицы. На каждую таблицу свой DataSet-класс.
 * Объект DataSet - строка в таблице.
 * Вместе с DAO (Data Access Object, объект доступа данных) являются элементами ORM (Object-Relational Mapping,
 * объектно-реляционного отображения).
 */
@Entity //Объект класса можно "переложить" в таблицу.
@Table(name = "users")  //Связывает класс и таблицу
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id //Является первичным ключом
    @Column(name = "id")    //Связывает поле и колонку в таблице
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password", updatable = false)
    private String password;

    @Column(name = "email", unique = true, updatable = false)
    private String email;


    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String login, String password, String email) {
        /*this.setId(id);*/
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
    }

    @Deprecated
    public UsersDataSet(String login) {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(login);
        this.setEmail(login + '@' + login);
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + login +
                ", password='" + password +
                ", email='" + email + '\'' +
                '}';
    }
}