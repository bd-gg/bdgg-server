package gg.boardgame.bdgg.mapper;

import gg.boardgame.bdgg.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Helloworld {
    @Autowired
    TestMapper testMapper;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "<html><body>"+testMapper.getUser()+"</body></html>";
    }
}
