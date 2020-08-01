package gg.boardgame.bdgg.db;

import lombok.*;
import org.springframework.util.Assert;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "provider")
    private String provider;
    @Column(name = "snsId")
    private String snsId;

    @OneToMany(mappedBy = "user")
    private List<UserGameHistory> gameHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMatch> userMatches = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GroupMember> groupMembers = new ArrayList<>();

    @Builder
    public User(String name, String password, String email, String imageUrl, String provider, String snsId) {
        Assert.notNull(name, "name must be not null");
        Assert.notNull(password, "password must be not null");
        Assert.notNull(email, "email must be not null");
        Assert.notNull(imageUrl, "imageUrl must be not null");
        Assert.notNull(provider, "provider must be not null");
        Assert.notNull(snsId, "snsId must be not null");
        this.name = name;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.snsId = snsId;
    }

}
