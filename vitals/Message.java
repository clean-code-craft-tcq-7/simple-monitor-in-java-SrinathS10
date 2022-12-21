package vitals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Message {
    static Map<String,String> messages;
    static String messageLanguage = "EN";

    static String lowBreach = "LOW_BREACH";
    static String lowWarn = "LOW_WARN";
    static String highBreach = "HIGH_BREACH";
    static String highWarn = "HIGH_WARN";

    /*
     Battery status messages are stored in text file(BatteryMessages.txt) with the below key structure in uppercase
     PROPERTY_NAME + _ + MESSAGE_TYPE + _ + MESSAGE_LANGUAGE
     */
    public static void loadMessages(){
        messages = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get("BatteryMessages.txt"))) {
            lines.filter(line -> line.contains("="))
                    .forEach(line -> {
                        String[] valuePair = line.split("=", 2);
                        messages.put(valuePair[0].trim(), valuePair[1].trim());
                    });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        loadMessages();
    }

    static String getMessage(BatteryProperty property, String messageType) {
        return messages.get(property.getPropertyName() + "_" + messageType + "_" + messageLanguage);
    }

    static void setMessageLanguage(String[] args) {
        /*
        Message language code passed in program arguments.
        If no arguments found assuming default language as English
        Language codes
        English - EN
        Deutsch - DE
        */
        if(args.length > 0) {
            messageLanguage = args[0].toUpperCase();
        }
    }
}
