package knu.networksecuritylab.appserver.entity.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    LANGUAGE("Programming Language"),
    JAVA("Java"),
    C("C"),
    CPP("C++"),
    PYTHON("Python"),
    WEB("Web Programming"),
    GRAPHICS("Graphics"),
    ARCHITECTURE("Architecture"),
    NETWORK("Network"),
    SECURITY("Security"),
    CS("Computer Science");

    private final String categoryName;
}
