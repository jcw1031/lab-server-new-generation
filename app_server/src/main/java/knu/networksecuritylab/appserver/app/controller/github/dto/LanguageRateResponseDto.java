package knu.networksecuritylab.appserver.app.controller.github.dto;

import knu.networksecuritylab.appserver.app.entity.github.LanguageRate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LanguageRateResponseDto {

    private String languageName;
    private String languageRate;

    @Builder
    public LanguageRateResponseDto(String languageName, String languageRate) {
        this.languageName = languageName;
        this.languageRate = languageRate;
    }

    public static LanguageRateResponseDto from(final LanguageRate languageRate) {
        double rate = languageRate.getLanguageRate();
        return LanguageRateResponseDto.builder()
                .languageName(languageRate.getLanguageName())
                .languageRate(String.format("%.1f", rate) + "%")
                .build();
    }
}
