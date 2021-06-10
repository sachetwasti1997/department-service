package com.sachet.departmentservice.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import java.util.Queue;

@Configuration
public class JmsConfig {

    //Serialize message content to json using Text Messages
    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        // anonymous class
        factory.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable t) {
                System.err.println("An error has occurred in the transaction: " + t.getMessage());
                t.printStackTrace();
            }
        });

        // lambda function
        //factory.setErrorHandler(t -> System.out.println("An error has occurred in the transaction: " ));

        configurer.configure(factory, connectionFactory);
        return factory;
    }

    // Only required due to defining myFactory1 in another receiver
    @Bean
    public JmsListenerContainerFactory<?> myFactory1(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        // anonymous class
        factory.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable t) {
                System.err.println("An error has occurred in the transaction: " + t.getMessage());
                t.printStackTrace();
            }
        });

        // lambda function
        //factory.setErrorHandler(t -> System.out.println("An error has occurred in the transaction: " ));

        configurer.configure(factory, connectionFactory);
        return factory;
    }

}
