package ru.snovit.tutorbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.snovit.tutorbot.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
