package com.jumio.notificationSystem.util;
import com.jumio.notificationSystem.entity.User;

public class NotificationTemplateUtil {

    public static String personalizeContent(String content, User user) {
        if (content == null) {
            return "";
        }
        return content.replace("{name}", user.getName()).replace("{email}", user.getEmail());
    }

}
