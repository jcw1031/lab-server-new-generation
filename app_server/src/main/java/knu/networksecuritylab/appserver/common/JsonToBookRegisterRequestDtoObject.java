package knu.networksecuritylab.appserver.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonToBookRegisterRequestDtoObject implements Converter<String, BookRegisterRequestDto> {

    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public BookRegisterRequestDto convert(String source) {
        return mapper.readValue(source, new TypeReference<>() {
        });
    }
}
