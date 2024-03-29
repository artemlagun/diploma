package com.lunchvoting.topjava.diploma.util;

import com.lunchvoting.topjava.diploma.HasId;
import com.lunchvoting.topjava.diploma.util.exception.ErrorType;
import com.lunchvoting.topjava.diploma.util.exception.IllegalRequestDataException;
import com.lunchvoting.topjava.diploma.util.exception.NotFoundException;
import com.lunchvoting.topjava.diploma.util.exception.OutOfTimeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@UtilityClass
public class ValidationUtil {

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

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }

    public static <T, ID> void checkExisted(CrudRepository<T, ID> repository, ID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
    }

    public static <T> List<T> checkExisted(List<T> objets, int id) {
        if (objets.isEmpty()) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
        return objets;
    }

    public static <T> List<T> checkExisted(List<T> objets, LocalDate date) {
        if (objets.isEmpty()) {
            throw new NotFoundException("Entities for this date " + date + " not found");
        }
        return objets;
    }

    public static <T> List<T> checkExisted(List<T> objets, LocalDate date, int id) {
        if (objets.isEmpty()) {
            throw new NotFoundException("Entities from " + id + " for this date " + date + " not found");
        }
        return objets;
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
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

    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable logAndGetRootCause(Logger log, HttpServletRequest req, Exception e, boolean logStackTrace, ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logStackTrace) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return rootCause;
    }
}