package knu.networksecuritylab.appserver.app.entity.github;

import knu.networksecuritylab.appserver.app.repository.github.dto.LanguageRateInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LanguageRate {

    @Id
    private String languageName;
    private double languageRate;

    public LanguageRate(String languageName, double languageRate) {
        this.languageName = languageName;
        this.languageRate = languageRate;
    }

    public static LanguageRate from(LanguageRateInterface languageRateInterface) {
        return new LanguageRate(languageRateInterface.getName(), languageRateInterface.getRate());
    }
}
