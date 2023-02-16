package knu.networksecuritylab.appserver.config.secure;

import knu.networksecuritylab.appserver.common.JsonToBookRegisterRequestDtoObject;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class WebConfig extends WebMvcConfigurationSupport {

    private final JsonToBookRegisterRequestDtoObject jsonToBookRegisterRequestDtoObject;

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(jsonToBookRegisterRequestDtoObject);
    }
}
