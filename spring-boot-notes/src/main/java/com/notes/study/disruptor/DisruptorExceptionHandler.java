package com.notes.study.disruptor;

import com.lmax.disruptor.ExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.util.function.BiConsumer;

@Log4j2
@AllArgsConstructor
public class DisruptorExceptionHandler<T> implements ExceptionHandler<T> {


    public final String name;

    public final BiConsumer<Throwable, Long> onException;

    @Override
    public void handleEventException(Throwable throwable, long l, T t) {
        if (log.isDebugEnabled()) {
            log.debug("disruptor {} seq ={} exit exception",name,l);
        }
        onException.accept(throwable,l);
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        log.info("disruptor {} ",name);
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        log.info("disruptor {} ",name);
    }
}
