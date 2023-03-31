package com.saralam.schgrql.controller;

//import com.example.withoutpojo.service.MyServ;
import com.saralam.schgrql.service.MyServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableAutoConfiguration
@RestController
@Controller
public class MyControl {
    @Autowired
    public MyServ myServ;
    @QueryMapping("search")
    public List<Object> the(@Argument Long poid, @Argument String object){
        return myServ.robj(poid,object);
    }
}
