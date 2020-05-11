package gg.boardgame.bdgg.example.service;

import gg.boardgame.bdgg.example.dto.ExampleDTO;
import org.springframework.stereotype.Service;

public interface ExampleService {
    public ExampleDTO getExampleData(String name);
    public void addExampleData(ExampleDTO ex);
}
