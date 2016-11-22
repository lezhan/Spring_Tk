import beans.Client;
import loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
//        Client client = new Client("1", "John Smith");
//        EventLogger eventLogger = new ConsoleEventLogger();
//        App app = new App(client, eventLogger);
//        app.client = new Client("1", "John Smith");
//        app.eventLogger = new ConsoleEventLogger();
//        app.logEvent("Some event for user 1");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//        Client client = (Client) ctx.getBean("client");
//        EventLogger eventLogger = (EventLogger) ctx.getBean("eventLogger");
        App app = (App) ctx.getBean("App");
        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");
    }
}
