package projekt.util.logger;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Logger extends Thread {
    static Boolean debug = Boolean.FALSE;

    protected static final Object LOCK = new Object();

    public static void info(String strMessage) {
        MessageType type = MessageType.INFO;
        Message message = new Message(type, strMessage);

        System.out.println(message);
        logMessage(type, message);
    }

    public static void warning(String strMessage) {
        if (!debug) return;
        MessageType type = MessageType.WARNING;
        Message message = new Message(type, strMessage);

        logMessage(type, message);
    }

    public static void error(String strMessage) {
        if (!debug) return;
        MessageType type = MessageType.ERROR;
        Message message = new Message(type, strMessage);

        logMessage(type, message);
    }

    public static void error(Exception ex) {
        if (!debug) return;
        MessageType type = MessageType.ERROR;

        StackTraceElement[] stack = ex.getStackTrace();
        String strMessage = ex.getMessage() + "\n" + Arrays.stream(stack).map(StackTraceElement::toString).collect(Collectors.joining("\n\t"));
        Message message = new Message(type, strMessage);
        logMessage(type, message);
    }

    public static void debug(String strMessage) {
        if (!debug) return;
        MessageType type = MessageType.DEBUG;
        Message message = new Message(type, strMessage);
        logMessage(type, message);
    }

    private static void logMessage(MessageType type, Message message) {

        String fileName = type.name() + "-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".txt";
        Thread logWriter = new Thread(new LogWriter(message, fileName, LOCK));
        logWriter.start();
    }

    static class LogWriter implements Runnable {
        Message message;
        String fileName;

        LogWriter(Message message, String fileName, Object lock){
            this.message = message;
            this.fileName = fileName;
        }
        @Override
        public void run() {
            synchronized (LOCK){
                if (message == null) return;

                try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true), true)) {
                    pw.println(message);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

}
