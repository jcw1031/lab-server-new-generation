package knu.networksecuritylab.appserver.app.config;

import knu.networksecuritylab.appserver.app.common.BookRegisterRequestDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class ConverterConfig extends WebMvcConfigurationSupport {

    private final BookRegisterRequestDtoConverter bookRegisterRequestDtoConverter;

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(bookRegisterRequestDtoConverter);
    }
}
