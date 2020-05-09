package gg.boardgame.bdgg.example.controller;

import gg.boardgame.bdgg.example.dto.ExampleDTO;
import gg.boardgame.bdgg.example.service.ExampleService;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;
    private Logger logger = LoggerFactory.getLogger(ExampleController.class);

    /*
    * curl -XGET -H "Accept: application/json"
    *            http://server.addr:port/example/{pathParam}?queryParam={queryParam}
    * */

    @RequestMapping(value = "/{pathParam}", method = RequestMethod.GET)
    public @ResponseBody ExampleDTO getExampleData(
            @PathVariable("pathParam") String pathParam,
            @RequestParam("queryParam") String queryParam) {

        logger.info(String.format("urlParam: %s, queryParam: %s", pathParam, queryParam));

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
        logger.info(String.format("name: %s, age: %d", ex.getName(), ex.getAge()));
        return ex;
    }
}
