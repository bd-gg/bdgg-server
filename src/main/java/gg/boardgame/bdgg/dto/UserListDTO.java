package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserListDTO {
    private List<UserDTO> items = new ArrayList<>();

    public UserListDTO(List<User> users) {
        users.forEach(u -> items.add(new UserDTO(u)));
    }
}
