package com.example.userprofile.controller;

import com.example.userprofile.dto.UserProfileDTO;
import com.example.userprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userprofiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfileDTO> createUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO createdProfile = userProfileService.createUserProfile(userProfileDTO);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles() {
        List<UserProfileDTO> userProfiles = userProfileService.getAllUserProfiles();
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable Long id) {
        UserProfileDTO userProfileDTO = userProfileService.getUserProfileById(id);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updatedProfile = userProfileService.updateUserProfile(id, userProfileDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
O