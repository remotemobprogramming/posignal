package org.remotemobprogramming.posignal;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "posignal")
public record PosignalConfiguration(
    String zoomPersonalRoomName,
    String zoomPersonalRoomUrlWithEncryptedPassword,
    String rocketChatUrl,
    String rocketChatChannel,
    String rocketChatProductOwnerHandle,
    String rocketChatAuthUserId,
    String rocketChatAuthToken) {}
