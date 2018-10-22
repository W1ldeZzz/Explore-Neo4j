package com.tgtech.explore.controller;

import com.tgtech.explore.connector.Neo4jConnector;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.neo4j.driver.v1.Values.parameters;

@RestController
@RequestMapping("neo4j")
public class Neo4jController {
    @Autowired
    private Neo4jConnector connector;

    @PostMapping("insert")
    @ResponseBody
    public String insert(@RequestParam("sfzh")String sfzh, @RequestParam("jzkh")String jzkh, @RequestParam("bah")String bah,
                         @RequestParam("zyh")String zyh, @RequestParam("mzh")String mzh, @RequestParam("phone")String phone,
                         @RequestParam("empi")String empi) {
        try (Session session = connector.getDriver().session()) {
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    StatementResult result = tx.run("CREATE (a:PI) " +
                            "SET a.sfzh = $sfzh, " +
                            "a.jzkh = $jzkh, " +
                            "a.bah = $bah, " +
                            "a.zyh = $zyh, " +
                            "a.mzh = $mzh, " +
                            "a.phone = $phone, " +
                            "a.empi = $empi "
                            , parameters("sfzh", sfzh, "jzkh", jzkh, "bah", bah,
                            "zyh", zyh, "mzh", mzh, "phone", phone, "empi", empi));
                    return "success";
                }
            });
            return "success";
        }
    }

    @PostMapping("hello")
    @ResponseBody
    public String hello(@RequestParam("jsonStr") String jsonStr) {
        System.out.println(jsonStr);
        return "Roger";
    }
}
