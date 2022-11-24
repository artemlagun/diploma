package com.lunchvoting.topjava.diploma.util;

import com.lunchvoting.topjava.diploma.model.AbstractBaseEntity;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import com.lunchvoting.topjava.diploma.util.exception.OutOfTimeException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    public static void votingTimeVerification(Clock clock) {
        if (LocalTime.now(clock).isAfter(LocalTime.of(11, 0, 0))) {
            throw new OutOfTimeException("Now " + LocalTime.now(clock).format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                    + ", sorry voting time expired. You could vote till 11:00:00");
        }
    }

    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }
}