import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class WelcomeMessageListener implements IListener<UserJoinEvent> {


    @Override
    public void handle(UserJoinEvent event) {
        IUser user = event.getUser();
        IChannel channel = event.getClient().getChannels().get(0);
        channel.sendMessage("Hi "+ user+", welcome to literally the best Discord server in the world!", true);
    }
}
