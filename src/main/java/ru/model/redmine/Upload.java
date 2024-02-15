package ru.model.redmine;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Upload {
    private String token;
    private String filename;
    private String contentType;
}
