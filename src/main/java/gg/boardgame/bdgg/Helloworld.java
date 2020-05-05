package gg.boardgame.bdgg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Helloworld {

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "<html><body>test code</body></html>";
    }
}
