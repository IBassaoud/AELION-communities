package org.aelion.community.community;

import org.springframework.http.ResponseEntity;

public interface CommunityService {
    ResponseEntity<?> fetchCommunities();
    ResponseEntity<?> fetchById(String id);
    ResponseEntity<?> createCommunity(Community community);
}
