package com.example.crawlingex.Controller;

import com.example.crawlingex.Service.jsoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class jsoupController {
    @Autowired
    private jsoupService jsoupService;

    @GetMapping("/main")
    public String search(){
        return "crawling";
    }

    @PostMapping("/searchSubmit")
    public String handleSubmit(@RequestParam("searchKeyword") String searchKeyword) throws IOException {
        jsoupService.searchPlant(searchKeyword);
        return "result";
    }

}
