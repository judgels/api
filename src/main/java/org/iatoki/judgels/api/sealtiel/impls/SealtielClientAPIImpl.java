package org.iatoki.judgels.api.sealtiel.impls;

import com.google.gson.JsonObject;
import org.iatoki.judgels.api.impls.AbstractJudgelsClientAPIImpl;
import org.iatoki.judgels.api.sealtiel.SealtielClientAPI;
import org.iatoki.judgels.api.sealtiel.SealtielMessage;

public final class SealtielClientAPIImpl extends AbstractJudgelsClientAPIImpl implements SealtielClientAPI {

    public SealtielClientAPIImpl(String baseUrl, String clientJid, String clientSecret) {
        super(baseUrl, "/api/v2", clientJid, clientSecret);
    }

    @Override
    public SealtielMessage fetchMessage() {
        return sendPostRequest("/messages/receive").asObjectFromJson(SealtielMessage.class);
    }

    @Override
    public void acknowledgeMessage(long messageId) {
        sendPostRequest(interpolatePath("/messages/:messageId/confirm", messageId));
    }

    @Override
    public void sendMessage(String targetClientJid, String messageType, String message) {
        JsonObject body = new JsonObject();

        body.addProperty("targetJid", targetClientJid);
        body.addProperty("type", messageType);
        body.addProperty("content", message);

        sendPostRequestWithJsonBody("/messages/send", body);
    }

    @Override
    public void sendLowPriorityMessage(String targetClientJid, String messageType, String message) {
        sendMessage(targetClientJid, messageType, message);
    }
}
