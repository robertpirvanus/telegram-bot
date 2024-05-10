package ro.robertpirvanus.telegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static ro.robertpirvanus.telegrambot.bot.Constants.START_DESCRIPTION;

@Component("telegramBot")
public class Bot extends AbilityBot {
    private final ResponseHandler responseHandler;
    @Autowired
    public Bot(Environment env) {
        super("bottoken", "botname");
        responseHandler = new ResponseHandler(silent, db);
        startBot();
    }

    public Ability startBot() {
        return Ability
                .builder()
                .name("start")
                .info(START_DESCRIPTION)
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> responseHandler.replyToStart(ctx.chatId()))
                .build();
    }

//    public Reply replyToButtons() {
//        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> responseHandler.replyToButtons(getChatId(upd), upd.getMessage());
//        return Reply.of(action, Flag.TEXT,upd -> responseHandler.userIsActive(getChatId(upd)));
//    }

    @Override
    public long creatorId() {
        return 1L;
    }
}