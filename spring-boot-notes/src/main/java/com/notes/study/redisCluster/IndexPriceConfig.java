package com.notes.study.redisCluster;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author jidn
 * @Date 2020/10/21
 */
@Configuration
public class IndexPriceConfig {

    public static final String CHANNEL = "COIN58:MORTGAGE:CURRENT:INDEX:PRICE";


    @Bean
    public RedisMessageListenerContainer container(@Qualifier("redisTemplate2") StringRedis2Template redisTemplate2,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate2.getConnectionFactory());
        container.addMessageListener(listenerAdapter, new PatternTopic(CHANNEL));
        return container;
    }


    @Bean
    public MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new Message(), "receiveMessage");
    }


    class Message{
        public void receiveMessage(String msg){
            System.out.println("接收到的消息："+msg);
        }
    }


}
