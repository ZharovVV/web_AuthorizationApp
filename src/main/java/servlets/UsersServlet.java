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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (login == null || password == null || email == null || login.equals("") || password.equals("") || email.equals("")) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Ошибка, пропущено поле");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        if ( accountService.getDbService().checkUserByLogin(login)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь c таким логином уже существует!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if ( accountService.getDbService().checkUserByEmail(email)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь c таким Email уже существует!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        /*accountService.addNewUser(new UserProfile(login));*/
        accountService.addNewUser(new UserProfile(login, password, email));

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
