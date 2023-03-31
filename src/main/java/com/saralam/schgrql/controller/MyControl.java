package com.saralam.schgrql.controller;

//import com.example.withoutpojo.service.MyServ;

import com.saralam.schgrql.service.MyServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MyControl {
    @Autowired
    public MyServ myServ;

    @QueryMapping("search")
    public List<Object> the(@Argument int z, @Argument String y) {
        return myServ.robj(z, y);
    }
}
