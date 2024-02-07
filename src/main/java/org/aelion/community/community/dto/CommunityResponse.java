package org.aelion.community.community.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
public class CommunityResponse {
    private String id;
    private String name;
    private City city;
}
