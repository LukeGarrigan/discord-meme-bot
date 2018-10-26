import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

public class FriendlyBot implements CreateBot{

    private static final Logger log = LoggerFactory.getLogger(FriendlyBot.class);

    public FriendlyBot() {
        log.debug("Creating a friendly bot");
        createBot();
    }


    @Override
    public void createBot() {
        IDiscordClient discordClient = createClient(BotConstants.FRIENDLY_BOT_ID, true);
        EventDispatcher dispatcher = discordClient.getDispatcher();
        dispatcher.registerListener(new WelcomeMessageListener());
    }
}
