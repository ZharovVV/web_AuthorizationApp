package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 *  Класс, предназначенный для управления учетными записями.
 */
public class AccountService {
    /*private final Map<String, UserProfile> loginToProfile;*/
    /**
     * Мар Сессий пользователя. Если пользователь залогинился (sign in),
     * то этот пользователь добавляется  по ключу sessionId в sessionIdToProfile,
     * если вышел - удаляется.
     */
    private final Map<String, UserProfile> sessionIdToProfile;
    private final DBService dbService;

    public AccountService() {
        dbService = new DBService();
        /*loginToProfile = new HashMap<>();*/
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        /*loginToProfile.put(userProfile.getLogin(), userProfile);*/
        try {
            long userId = dbService.addUser(userProfile);
            System.out.println("Added user id: " + userId);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) throws DBException {
        try {
            UsersDataSet dataSet = dbService.getUser(login);
            System.out.println("User data set: " + dataSet);
            return new UserProfile(dataSet.getLogin(), dataSet.getPassword(), dataSet.getEmail());

        } catch (DBException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

    public DBService getDbService() {
        return dbService;
    }
}
