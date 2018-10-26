import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

public class OpinionatedBot implements CreateBot{
    private static final Logger log = LoggerFactory.getLogger(OpinionatedBot.class);

    public OpinionatedBot() {
        log.debug("Creating opinionated bot");
        createBot();
    }


    @Override
    public void createBot() {
        IDiscordClient discordClient = createClient(BotConstants.BOT_ID, true);
        EventDispatcher dispatcher = discordClient.getDispatcher();
        dispatcher.registerListener(new SomeoneWroteSomethingInMyBloodyChannel());
        dispatcher.registerListener(new OnConnectionMadeListener());
    }
}
