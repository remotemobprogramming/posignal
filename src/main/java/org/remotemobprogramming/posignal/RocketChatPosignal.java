package org.remotemobprogramming.posignal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.stereotype.Component;

@Component
public class RocketChatPosignal {

  private final HttpClient httpClient;

  public RocketChatPosignal(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  void posignal(PosignalConfiguration posignalConfiguration)
      throws URISyntaxException, IOException, InterruptedException {

    var zoomLink =
        String.format(
            posignalConfiguration.zoomPersonalRoomUrlWithEncryptedPassword(),
            posignalConfiguration.zoomPersonalRoomName());
    var request =
        HttpRequest.newBuilder()
            .uri(new URI(posignalConfiguration.rocketChatUrl() + "/api/v1/chat.postMessage"))
            .POST(
                BodyPublishers.ofString(
                    """
                        {
                        "channel": "%s",
                        "text": "%s to %s %s",
                        "emoji": ":posignal:"
                        }"""
                        .formatted(
                            posignalConfiguration.rocketChatChannel(),
                            posignalConfiguration.rocketChatProductOwnerHandle(),
                            posignalConfiguration.zoomPersonalRoomName(),
                            zoomLink)))
            .header("X-User-Id", posignalConfiguration.rocketChatAuthUserId())
            .header("X-Auth-Token", posignalConfiguration.rocketChatAuthToken())
            .header("Content-Type", "application/json")
            .build();
    httpClient.send(request, BodyHandlers.ofString());
  }
}
