package gg.boardgame.bdgg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private long id;

    private String name;
    private String password;
    private String email;
    private String imageUrl;
}
