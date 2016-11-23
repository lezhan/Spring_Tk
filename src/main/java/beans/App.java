package beans;

import loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(msg);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
//        Client client = new Client("1", "John Smith");
//        EventLogger eventLogger = new ConsoleEventLogger();
//        beans.App app = new beans.App(client, eventLogger);
//        app.client = new Client("1", "John Smith");
//        app.eventLogger = new ConsoleEventLogger();
//        app.logEvent("Some event for user 1");
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//        Client client = (Client) ctx.getBean("client");
//        EventLogger eventLogger = (EventLogger) ctx.getBean("eventLogger");
        App app = (App) ctx.getBean("app");
//        Event event = (Event) ctx.getBean("event", Event.class);
        Event event = ctx.getBean("event", Event.class);
        app.logEvent(event, "Some event for 1");
        event =  ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");
        ctx.close();
    }
}
