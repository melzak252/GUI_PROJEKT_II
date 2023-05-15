package projekt.util.logger;

import java.time.LocalDateTime;

class Message {
    String message;
    LocalDateTime time;
    MessageType type;

    public Message(MessageType type, String message) {
        this.type = type;
        this.time = LocalDateTime.now();
        this.message = message;
    }

    @Override
    public String toString() {
        return time.withNano(0) + "\t|\t" + type.name() + "\t|\t" + message;
    }

    public MessageType getType() {
        return this.type;
    }
}
