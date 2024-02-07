package org.aelion.community.community.Impl;

import org.aelion.community.community.Community;
import org.aelion.community.community.CommunityController;
import org.aelion.community.community.CommunityRepository;
import org.aelion.community.community.CommunityService;
import org.aelion.community.community.dto.City;
import org.aelion.community.community.dto.CommunityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    private final static String CITY_API = "http://localhost:4001/api/v1/";

    /**
     * @return
     */
    @Override
    public ResponseEntity<?> fetchCommunities() {
        List<Community> communities = repository.findAll();
        return ResponseEntity.ok(communities);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<?> fetchById(String id) {
        Optional<Community> oCommunity = repository.findById(id);
        if (oCommunity.isPresent()){
            String endpoint = CITY_API + "cities/" + oCommunity.get().getCityCode();
            City city = restTemplate.getForObject(
                    endpoint,
                    City.class
            );
            // Build a CommunityResponse Object
            CommunityResponse response = new CommunityResponse();
            response.setId(oCommunity.get().getId());
            response.setName(oCommunity.get().getName());
            response.setCity(city);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("No community was found", HttpStatus.NOT_FOUND);
    }

    /**
     * @param community
     * @return
     */
    @Override
    public ResponseEntity<?> createCommunity(Community community) {
        try {
            return new ResponseEntity<Community>(
                    repository.save(community),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save community", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
