package com.saralam.schgrql.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.pcm.*;
import com.portal.pcm.fields.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyServ {


    public List<Object> robj(int z, String y) {
        JSONObject json = null;
        String c = null;
        String[] answer = new String[0];

//        Map<String, Object> asd = null;
        List<Object> asd = null;
        try {
            // Get the input flist
//			FList inflist =  createSearchFList();
            FList sFlist = new FList();

            // resflist, argsflist, sFlag, template
            FList resFlist = new FList();
            FList argsFlist = new FList();
            String template = "select X from " + y + " where F1 = V1 ";
            int sFlag = 256;

            //create search poid
            //  Poid sPoid = new Poid(1, 309074, "/account");
            Poid sPoid = new Poid(1, -1, "/search");
            sPoid.getDb();

            sFlist.set(FldPoid.getInst(), sPoid);

            // add data to the flist
            sFlist.set(FldPoid.getInst(), sPoid);
            sFlist.set(FldFlags.getInst(), sFlag);
            sFlist.set(FldTemplate.getInst(), template);

            // argument flist and adding it to main flist
            Poid accPoid = new Poid(1, z, "" + y + "");
            argsFlist.set(FldPoid.getInst(), accPoid);
            sFlist.setElement(FldArgs.getInst(), 1, argsFlist);

            // result flist
//			 Poid accPoid1 = new Poid(1, -1, "/account");
            // resFlist.set(FldPoid.getInst(), accPoid1);
            sFlist.setElement(FldResults.getInst(), -1, resFlist);
            String xmlFlist = sFlist.toXMLString();
            System.out.println(xmlFlist);


            // Print input flist
            System.out.println("Input flist:");
            // System.out.println(inflist);
            sFlist.dump(); //alternate way to print

            // Create PCM connext necessary for connecting to the server.
            // A valid Infranet.properties file should be in the classpath.
            // See context examples for additional information.
            PortalContext ctx = new PortalContext();
            ctx.connect();

            // Call the opcode
            FList searchoutflist = ctx.opcode(PortalOp.SEARCH, sFlist);

            // Close PCM connection
            boolean logout = true;
            ctx.close(logout);

            // Print the return flist
            System.out.println("Output flist:");
            searchoutflist.dump(); // this is an alternate way to print out an flist
            String xml = searchoutflist.toXMLString();
            json = org.json.XML.toJSONObject(xml);

            System.out.println(json);
            String jsonString = json.toString(5);


            ArrayList a = new ArrayList();
            a.add(jsonString);
            JSONArray l = new JSONArray(a);
            answer = Arrays.copyOf(
                    a.toArray(), a.size(), String[].class);
            c = Arrays.toString(answer);
            System.out.println(c);
            ObjectMapper obj = new ObjectMapper();


            asd = obj.readValue(c, new TypeReference<List<Object>>() {
            });
//            myRepo.saveAll(rootlist);
            System.out.println(asd);


            System.out.println("Success!");
//		System.out.println(root.getFlist().getrESULTS());
//		System.out.println(root.getClass());

        } catch (JsonProcessingException | EBufException ebuf) {
            System.out.println("You weren't able to call the PCM_OP_TEST_LOOPBACK opcode.");
            System.out.println(" * Do you have a correct Infranet.properties file in the classpath?");
            System.out.println(" * Is the Infranet server CM up?");
            System.out.println("Here's the error:");
            System.out.println(ebuf);
        }

        return asd;
    }
}
