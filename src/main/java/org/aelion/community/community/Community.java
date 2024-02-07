package org.aelion.community.community;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document(collection = "communities")
public class Community {
    @Id
    private String id;

    private String name;

    private String cityCode;
}
