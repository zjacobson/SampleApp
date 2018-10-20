package spothero.demo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class ConsoleLogger {

    public ConsoleLogger() {
        Handler handler =
                new ConsoleHandler()
                {
                    {
                        setOutputStream(System.out);
                    }
                };

        Logger.getLogger("").addHandler(handler);
    }
}
