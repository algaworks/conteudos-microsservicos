package com.example.algaworks.spring.cloud.stream.rssconsumer;

import com.rometools.rome.feed.synd.SyndEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.messaging.Message;

import java.net.MalformedURLException;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class FeedSupplierConfig {

    @Bean
    public FeedEntryMessageSource feedEntryMessageSource(FeedSupplierProperties properties)
            throws MalformedURLException {
        return new FeedEntryMessageSource(
                properties.getFeedUri().toURL(),
                properties.getMetadataKey()
        );
    }

    @Bean
    public Supplier<FeedEvent> feedSupplier(FeedEntryMessageSource source) {
        return () -> {
            Message<SyndEntry> message = source.receive();
            if (message != null) {
                SyndEntry payload = message.getPayload();
                log.info("Nova mensagem recebida do feed " + payload.getLink());
                return FeedEvent.builder()
                        .guid(payload.getLink())
                        .link(payload.getLink())
                        .pubDate(payload.getPublishedDate().toString())
                        .description(payload.getDescription().getValue())
                        .autor(payload.getAuthor())
                        .build();
            } else {
                log.info("Mensagem vazia");
                return null;
            }
        };
    }

}
