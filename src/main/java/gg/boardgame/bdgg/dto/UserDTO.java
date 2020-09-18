package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private Long id;

    private String name;
    private String password;
    private String email;
    private String imageUrl;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
    }
}
