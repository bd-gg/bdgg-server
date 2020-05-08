package gg.boardgame.bdgg.example.service;

import gg.boardgame.bdgg.example.dto.ExampleDTO;
import org.springframework.stereotype.Service;

public interface ExampleService {
    public ExampleDTO getExampleData();
    public void addExampleData(ExampleDTO ex);
}
