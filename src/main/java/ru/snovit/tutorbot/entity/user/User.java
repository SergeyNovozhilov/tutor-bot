package ru.snovit.tutorbot.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "users")
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private Long chatId;
//
//    @Enumerated(EnumType.STRING)
//    Role role;
//
//    @Enumerated(EnumType.STRING)
//    Action action;
}
