package knu.networksecuritylab.appserver.entity.github;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;
    private String languageName;
    private Long languageBytes;

    @Builder
    public ProgrammingLanguage(String languageName, Long languageBytes) {
        this.languageName = languageName;
        this.languageBytes = languageBytes;
    }
}
