package com.metehan.SpringORM.deneme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DenemeController {

    @GetMapping("/deneme")
    @ResponseBody
    public String getDeneme(){


        return "Deneme Sayfası";
    }
}
