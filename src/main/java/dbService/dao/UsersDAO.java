package dbService.dao;

import accounts.UserProfile;
import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
    }

    public boolean checkUserByLogin(String login) {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet usersDataSet = (UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        return usersDataSet != null;
    }

    public boolean checkUserByEmail(String email) {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet usersDataSet = (UsersDataSet) criteria.add(Restrictions.eq("email", email)).uniqueResult();
        return usersDataSet != null;
    }

    public long insertUser(String login, String password, String email) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login, password, email));
    }
}
