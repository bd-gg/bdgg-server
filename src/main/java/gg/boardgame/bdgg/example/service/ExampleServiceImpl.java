package gg.boardgame.bdgg.example.service;

import gg.boardgame.bdgg.example.dto.ExampleDTO;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Override
    public ExampleDTO getExampleData() {
        ExampleDTO ex = new ExampleDTO();
        ex.setName("ExamplePerson");
        ex.setAge(22);
        return ex;
    }

    @Override
    public void addExampleData(ExampleDTO ex) {
        // do something with DTO
    }
}
