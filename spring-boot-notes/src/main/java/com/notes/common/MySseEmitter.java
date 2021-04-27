package com.notes.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class MySseEmitter extends ResponseBodyEmitter {

    static final MediaType TEXT_PLAIN = new MediaType("text", "plain", StandardCharsets.UTF_8);

    static final MediaType TEXT_EVENTSTREAM = new MediaType("text", "event-stream", StandardCharsets.UTF_8);


    /**
     * Create a new MySseEmitter instance.
     */
    public MySseEmitter() {
        super();
    }

    /**
     * Create a MySseEmitter with a custom timeout value.
     * <p>By default not set in which case the default configured in the MVC
     * Java Config or the MVC namespace is used, or if that's not set, then the
     * timeout depends on the default of the underlying server.
     * @param timeout timeout value in milliseconds
     * @since 4.2.2
     */
    public MySseEmitter(Long timeout) {
        super(timeout);
    }


    @Override
    protected void extendResponse(ServerHttpResponse outputMessage) {
        super.extendResponse(outputMessage);

        HttpHeaders headers = outputMessage.getHeaders();
        if (headers.getContentType() == null) {
            headers.setContentType(TEXT_EVENTSTREAM);
        }
    }

    /**
     * Send the object formatted as a single SSE "data" line. It's equivalent to:
     * <pre>
     * // static import of MySseEmitter.*
     *
     * MySseEmitter emitter = new MySseEmitter();
     * emitter.send(event().data(myObject));
     * </pre>
     * <p>Please, see {@link ResponseBodyEmitter#send(Object) parent Javadoc}
     * for important notes on exception handling.
     * @param object the object to write
     * @throws IOException raised when an I/O error occurs
     * @throws java.lang.IllegalStateException wraps any other errors
     */
    @Override
    public void send(Object object) throws IOException {
        send(object, null);
    }

    /**
     * Send the object formatted as a single SSE "data" line. It's equivalent to:
     * <pre>
     * // static import of MySseEmitter.*
     *
     * MySseEmitter emitter = new MySseEmitter();
     * emitter.send(event().data(myObject, MediaType.APPLICATION_JSON));
     * </pre>
     * <p>Please, see {@link ResponseBodyEmitter#send(Object) parent Javadoc}
     * for important notes on exception handling.
     * @param object the object to write
     * @param mediaType a MediaType hint for selecting an HttpMessageConverter
     * @throws IOException raised when an I/O error occurs
     */
    @Override
    public void send(Object object, @Nullable MediaType mediaType) throws IOException {
        send(event().data(object, mediaType));
    }

    /**
     * Send an SSE event prepared with the given builder. For example:
     * <pre>
     * // static import of MySseEmitter
     * MySseEmitter emitter = new MySseEmitter();
     * emitter.send(event().name("update").id("1").data(myObject));
     * </pre>
     * @param builder a builder for an SSE formatted event.
     * @throws IOException raised when an I/O error occurs
     */
    public void send(MySseEmitter.SseEventBuilder builder) throws IOException {
        Set<DataWithMediaType> dataToSend = builder.build();
        synchronized (this) {
            for (DataWithMediaType entry : dataToSend) {
                super.send(entry.getData(), entry.getMediaType());
            }
        }
    }

    @Override
    public String toString() {
        return "MySseEmitter@" + ObjectUtils.getIdentityHexString(this);
    }


    public static MySseEmitter.SseEventBuilder event() {
        return new MySseEmitter.SseEventBuilderImpl();
    }


    /**
     * A builder for an SSE event.
     */
    public interface SseEventBuilder {

        /**
         * Add an SSE "id" line.
         */
        MySseEmitter.SseEventBuilder id(String id);

        /**
         * Add an SSE "event" line.
         */
        MySseEmitter.SseEventBuilder name(String eventName);

        /**
         * Add an SSE "retry" line.
         */
        MySseEmitter.SseEventBuilder reconnectTime(long reconnectTimeMillis);

        /**
         * Add an SSE "comment" line.
         */
        MySseEmitter.SseEventBuilder comment(String comment);

        /**
         * Add an SSE "data" line.
         */
        MySseEmitter.SseEventBuilder data(Object object);

        /**
         * Add an SSE "data" line.
         */
        MySseEmitter.SseEventBuilder data(Object object, @Nullable MediaType mediaType);

        /**
         * Return one or more Object-MediaType  pairs to write via
         * {@link #send(Object, MediaType)}.
         * @since 4.2.3
         */
        Set<DataWithMediaType> build();
    }


    /**
     * Default implementation of SseEventBuilder.
     */
    private static class SseEventBuilderImpl implements MySseEmitter.SseEventBuilder {

        private final Set<DataWithMediaType> dataToSend = new LinkedHashSet<>(4);

        @Nullable
        private StringBuilder sb;

        @Override
        public MySseEmitter.SseEventBuilder id(String id) {
            append("id:").append(id).append("\n");
            return this;
        }

        @Override
        public MySseEmitter.SseEventBuilder name(String name) {
            append("event:").append(name).append("\n");
            return this;
        }

        @Override
        public MySseEmitter.SseEventBuilder reconnectTime(long reconnectTimeMillis) {
            append("retry:").append(String.valueOf(reconnectTimeMillis)).append("\n");
            return this;
        }

        @Override
        public MySseEmitter.SseEventBuilder comment(String comment) {
            append(":").append(comment).append("\n");
            return this;
        }

        @Override
        public MySseEmitter.SseEventBuilder data(Object object) {
            return data(object, null);
        }

        @Override
        public MySseEmitter.SseEventBuilder data(Object object, @Nullable MediaType mediaType) {
            saveAppendedText();
            this.dataToSend.add(new DataWithMediaType(object, mediaType));
            append("\n");
            return this;
        }

        MySseEmitter.SseEventBuilderImpl append(String text) {
            if (this.sb == null) {
                this.sb = new StringBuilder();
            }
            this.sb.append(text);
            return this;
        }

        @Override
        public Set<DataWithMediaType> build() {
            if (!StringUtils.hasLength(this.sb) && this.dataToSend.isEmpty()) {
                return Collections.emptySet();
            }
            append("\n");
            saveAppendedText();
            return this.dataToSend;
        }

        private void saveAppendedText() {
            if (this.sb != null) {
                this.dataToSend.add(new DataWithMediaType(this.sb.toString(), TEXT_PLAIN));
                this.sb = null;
            }
        }
    }
}
