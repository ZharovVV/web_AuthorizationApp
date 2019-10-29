package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 *
 *      Пользовательский Сервлет. Необходим для обработки POST-запросов регистрации пользователя (sign up).
 */
public class UsersServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры POST-запроса для /signup (см. index.html).
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Случай пустых полей
        if (login == null || password == null || email == null || login.equals("") || password.equals("") || email.equals("")) {
            // Ответ
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Ошибка, пропущено поле");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            // выход из метода
            return;
        }

        // Если пользователь с таким логином существует:
        if ( accountService.getDbService().checkUserByLogin(login)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь c таким логином уже существует!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Если пользователь с таким email...
        if ( accountService.getDbService().checkUserByEmail(email)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь c таким Email уже существует!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        /*accountService.addNewUser(new UserProfile(login));*/
        // Если все проверки прошли, добавляем пользователя в БД
        accountService.addNewUser(new UserProfile(login, password, email));
        // Ответ
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Пользователь зарегестрирован!");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }
}
