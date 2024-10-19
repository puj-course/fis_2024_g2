package org.musify.service;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;;

public class VonageSmsService {
    private final VonageClient client = VonageClient.builder().apiKey("8ed53cef").apiSecret("piteAJkuZ9cShMZl").build();

    public boolean enviarSMS(String numeroDestino, String mensaje) {
        TextMessage message = new TextMessage("Musify",
                numeroDestino,
                mensaje
        );
        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            return true;
        } else {
            return false;
        }
    }
}