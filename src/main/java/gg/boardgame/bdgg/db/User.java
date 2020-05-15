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

    @OneToOne(mappedBy = "user")
    private UserGameHistory gameHistory;

    @OneToMany(mappedBy = "user")
    private List<UserMatch> userMatches = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GroupMember> groupMembers = new ArrayList<>();

    @Builder
    public User(long id, String name, String password, String email, String imageUrl) {
        Assert.notNull(id, "id must be not null");
        Assert.notNull(name, "name must be not null");
        Assert.notNull(password, "password must be not null");
        Assert.notNull(email, "email must be not null");
        Assert.notNull(imageUrl, "imageUrl must be not null");
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
    }

}
