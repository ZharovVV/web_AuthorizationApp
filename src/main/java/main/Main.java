package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.UsersServlet;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        accountService.addNewUser(new UserProfile("admin"));

        // Обработчик
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        // Добавление сервлета UsersServlet, а точнее Держателя экземпляра и контекста сервлета (ServletHolder).
        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/signup");
        // Добавление сервлета SessionsServlet
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/signin");

        // Обработчик ресурсов. Обслуживает статический контент (в нашем случае index.html в папке public_html)
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        /* Список обработчиков. Это расширение HandlerCollection будет вызывать каждый содержащийся обработчик
        по очереди, пока не будет сгенерировано исключение, не будет принят ответ или
        не будет установлен положительный статус ответа.*/
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        // Создание сервера jetty на порту 8080
        Server server = new Server(8080);
        // Установка обработчиков
        server.setHandler(handlers);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
