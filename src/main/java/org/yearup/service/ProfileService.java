package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

@Service
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }

    // notes: - looks like we need to make a get method. getById() pass in userId?
    //          we are just fetching the userId in the repo class
    //        - need to create update() that the controller connects to

    public Profile getByUserId(int userId)
    {
        return profileRepository.findByUserId(userId);
    }

    public Profile update(int userId, Profile profile)
    {
        profile.setUserId(userId);
        return profileRepository.save(profile);
    }
}
