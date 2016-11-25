package beans;

import loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(msg);
        EventLogger eventLogger = loggers.get(eventType);
        if (eventLogger == null)
            eventLogger = defaultLogger;
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
        Event event = ctx.getBean( Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event =  ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event =  ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 2");

        ctx.close();
    }
}
