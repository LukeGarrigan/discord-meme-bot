import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SomeoneWroteSomethingInMyBloodyChannel implements IListener<MessageReceivedEvent>, SomeoneMessageInChannel{



    private static final Logger log = LoggerFactory.getLogger(SomeoneWroteSomethingInMyBloodyChannel.class);
    public void handle(MessageReceivedEvent event) {
        log.debug("Someone wrote in the channel");
        boolean isMyChannel = event.getChannel().getName().equals("opinionated-quotes");
        if (isMyChannel) {
            messagePersonInChannel(event);
        }
    }

    @Override
    public void messagePersonInChannel(MessageEvent event) {
        if ("Lukey".equals(event.getAuthor().getName())) {
            event.getChannel().sendMessage(event.getAuthor() + ", you can stay babe");
        } else {
            event.getChannel().sendMessage("Get out my channel mate " + event.getAuthor(), true);
        }
    }
}
