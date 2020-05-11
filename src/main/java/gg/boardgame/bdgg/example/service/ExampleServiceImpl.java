package gg.boardgame.bdgg.example.service;

import gg.boardgame.bdgg.example.db.User;
import gg.boardgame.bdgg.example.db.UserRepository;
import gg.boardgame.bdgg.example.dto.ExampleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ExampleDTO getExampleData(String name) {
        // If there is found data, return the data. If not, throw error
        User user = userRepository.findByName(name).orElseThrow(()-> new IllegalArgumentException("No such data"));
        ExampleDTO ex = new ExampleDTO();
        ex.setName(user.getName());
        ex.setAge(Integer.valueOf(user.getPassword()));

        return ex;
    }

    @Override
    public void addExampleData(ExampleDTO ex) {
        User user = new User();
        user.setName(ex.getName());
        user.setPassword(Integer.toString(ex.getAge()));
        user = userRepository.save(user);
    }
}
