package gg.boardgame.bdgg.example.controller;

import gg.boardgame.bdgg.example.dto.ExampleDTO;
import gg.boardgame.bdgg.example.service.ExampleService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    /*
    * curl -XGET -H "Accept: application/json"
    *            http://server.addr:port/example/{pathParam}?queryParam={queryParam}
    * */

    @RequestMapping(value = "/{pathParam}", method = RequestMethod.GET)
    public @ResponseBody ExampleDTO getExampleData(
            @PathVariable("pathParam") String pathParam,
            @RequestParam("queryParam") String queryParam) {

        System.out.println(String.format("urlParam: %s, queryParam: %s", pathParam, queryParam));

        return exampleService.getExampleData();
    }

    /*
    * curl -XPOST -H "Accept: application/json"
    *             -H "Content-Type:application/json"
    *             --data '{"name":"SomeoneName","age":28}'
    *             http://server.addr:port/example
    * */

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ExampleDTO addExampleData(@RequestBody ExampleDTO ex) {
        exampleService.addExampleData(ex);
        System.out.println(String.format("name: %s, age: %d", ex.getName(), ex.getAge()));
        return ex;
    }
}
