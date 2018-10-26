import org.json.JSONArray;
import org.json.JSONObject;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class OnConnectionMadeListener implements IListener<ReadyEvent> {
    public void handle(ReadyEvent event) {
        System.out.println("Bot is live");
        IChannel myChannel = getMyChannel("opinionated-quotes", event);
        postOnceADay(myChannel);

    }

    private void postOnceADay(final IChannel myChannel) {
        Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(24 * 60 * 60 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Just wasn't tired");
                }
                postUnrealBanterToChannel(myChannel);
            }
        );
        thread.start();
    }


    private IChannel getMyChannel(String channelName, ReadyEvent event) {
        for (IChannel channel : event.getClient().getChannels()) {
            if (channel.getName().equals(channelName)) {
                return channel;
            }
        }
        return null;
    }


    private void postUnrealBanterToChannel(IChannel myChannel) {
        boolean alreadyBeenUsed = true;
        String opinionatedQuote = "";
        while (alreadyBeenUsed || opinionatedQuote.equals("")) {
            opinionatedQuote = getOpinionatedQuote();
            alreadyBeenUsed = hasQuoteBeenUsed(opinionatedQuote, myChannel);
        }
        myChannel.sendMessage(opinionatedQuote);
    }

    private boolean hasQuoteBeenUsed(String opinionatedQuote, IChannel myChannel) {

        for (IMessage iMessage : myChannel.getFullMessageHistory()) {
            if (iMessage.equals(opinionatedQuote)) {
                return true;
            }
        }

        return false;
    }

    public String getOpinionatedQuote() {

        try {
            JSONObject quote = getJsonQuote();
            try {
                StringBuilder outputMessage = createQuoteString(quote);
                return outputMessage.toString();
            } catch (Exception e) {
                return "";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private StringBuilder createQuoteString(JSONObject quote) {
        Object actualQuote = quote.get("quote");
        Object author = quote.get("author");


        StringBuilder outputMessage = new StringBuilder();

        outputMessage.append((String) actualQuote)
                .append(" - ")
                .append((String) author);
        return outputMessage;
    }

    private JSONObject getJsonQuote() throws IOException {
        JSONObject json = new JSONObject(org.apache.commons.io.IOUtils.toString(new URL("https://opinionated-quotes-api.gigalixirapp.com/v1/quotes"), Charset.forName("UTF-8")));

        JSONArray quotes = json.getJSONArray("quotes");
        return quotes.getJSONObject(0);
    }
}
