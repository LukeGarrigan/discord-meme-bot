import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SomeoneWroteSomethingInMyBloodyChannel implements IListener<MessageReceivedEvent> {




    public void handle(MessageReceivedEvent event) {
        boolean isMyChannel = event.getChannel().getName().equals("opinionated-quotes");
        if (isMyChannel) {
            tellThemToGetOutMyChannel(event);
        }
    }

    private void tellThemToGetOutMyChannel(MessageEvent event) {

        if ("Lukey".equals(event.getAuthor().getName())) {
            event.getChannel().sendMessage(event.getAuthor() + ", you can stay babe");
        } else {
            event.getChannel().sendMessage("Get out my channel mate " + event.getAuthor(), true);
        }

    }
}
