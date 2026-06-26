package org.yearup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yearup.models.Profile;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>
{
    Profile findByUserId(int userId);
}

// notes: create a method that gets all the profiles by calling its primary key
// (changed return type to Profile instead of List<Profile> since there is only one instance of each profile.)