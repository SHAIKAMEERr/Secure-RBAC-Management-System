package com.example.userprofile.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.userprofile.dto.UserProfileDTO;
import com.example.userprofile.model.UserProfile;
import com.example.userprofile.repository.UserProfileRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final ModelMapper modelMapper;

    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = modelMapper.map(userProfileDTO, UserProfile.class);
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        return modelMapper.map(savedUserProfile, UserProfileDTO.class);
    }

    public List<UserProfileDTO> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        return userProfiles.stream()
                .map(userProfile -> modelMapper.map(userProfile, UserProfileDTO.class))
                .collect(Collectors.toList());
    }

    public UserProfileDTO getUserProfileById(Long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        return userProfile.map(profile -> modelMapper.map(profile, UserProfileDTO.class))
                          .orElseThrow(() -> new RuntimeException("UserProfile not found"));
    }

    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        UserProfile existingProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProfile not found"));

        existingProfile.setFirstName(userProfileDTO.getFirstName());
        existingProfile.setLastName(userProfileDTO.getLastName());
        existingProfile.setEmail(userProfileDTO.getEmail());
        existingProfile.setPhoneNumber(userProfileDTO.getPhoneNumber());

        UserProfile updatedUserProfile = userProfileRepository.save(existingProfile);
        return modelMapper.map(updatedUserProfile, UserProfileDTO.class);
    }

    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
}
O