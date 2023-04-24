package de.unternehmen.kundenmanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogHelper {

    public static <T> String entityNichtGefunden(Class<T> clazz, long id) {
        return String.format("%s-Entity mit der ID [%s] konnte nicht gefunden werden", clazz.getName(), id);
    }

}
