package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.service.ProfileService;

// notes: needs a GET method getProfile(), and a PUT method create(). create() is already built in service class.
// needs an @autowired constructor to inject ProfileService (do we need to inject UserService too??)
@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController
{
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService)
    {
        this.profileService = profileService;
    }
}

