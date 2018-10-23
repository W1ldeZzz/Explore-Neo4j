package com.tgtech.explore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtech.explore.connector.Neo4jConnector;
import com.tgtech.explore.entity.ImCheckReq;
import com.tgtech.explore.entity.ImCheckSheet;
import com.tgtech.explore.entity.ImPatientZy;
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

    @PostMapping("insertParam")
    @ResponseBody
    public String insertParam(@RequestParam("sfzh")String sfzh, @RequestParam("jzkh")String jzkh, @RequestParam("bah")String bah,
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
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("insertIPZ")
    @ResponseBody
    public String insertIPZ(@RequestBody ImPatientZy ipz) {
        System.out.println(ipz);
        try (Session session = connector.getDriver().session()) {
            try (Transaction tx = session.beginTransaction()) {
                tx.run("CREATE (a:IPZ) " +
                                "SET a.sfzh = $sfzh, " +
                                "a.jzkh = $jzkh, " +
                                "a.bah = $bah, " +
                                "a.zyh = $zyh, " +
                                "a.mzh = $mzh, " +
                                "a.phone = $phone, " +
                                "a.empi = $empi, " +
                                "a.type = $type"
                        , parameters("sfzh", ipz.getSfzh(), "jzkh", ipz.getJzkh(), "bah", ipz.getBah(),
                                "zyh", ipz.getZyh(), "mzh", ipz.getMzh(), "phone", ipz.getPhone(), "empi", ipz.getEmpi(),
                                "type", ipz.getType()));
                tx.success();
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("insertICQ")
    @ResponseBody
    public String insertICQ(@RequestBody ImCheckReq icq) {
        System.out.println(icq);
        try (Session session = connector.getDriver().session()) {
            try (Transaction tx = session.beginTransaction()) {
                tx.run("CREATE (a:ICQ) " +
                                "SET a.sfzh = $sfzh, " +
                                "a.jzkh = $jzkh, " +
                                "a.bah = $bah, " +
                                "a.zyh = $zyh, " +
                                "a.mzh = $mzh, " +
                                "a.phone = $phone, " +
                                "a.empi = $empi, " +
                                "a.type = $type, " +
                                "a.jcdxh = $jcdxh, " +
                                "a.yzxh = $yzxh "
                        , parameters("sfzh", icq.getSfzh(), "jzkh", icq.getJzkh(), "bah", icq.getBah(),
                                "zyh", icq.getZyh(), "mzh", icq.getMzh(), "phone", icq.getPhone(), "empi", icq.getEmpi(),
                                "type", icq.getType(), "jcdxh", icq.getJcdxh(), "yzxh", icq.getYzxh()));
                tx.success();
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("insertICS")
    @ResponseBody
    public String insertICS(@RequestBody ImCheckSheet ics) {
        System.out.println(ics);
        try (Session session = connector.getDriver().session()) {
            try (Transaction tx = session.beginTransaction()) {
                tx.run("CREATE (a:ICS) " +
                                "SET a.sfzh = $sfzh, " +
                                "a.jzkh = $jzkh, " +
                                "a.bah = $bah, " +
                                "a.zyh = $zyh, " +
                                "a.mzh = $mzh, " +
                                "a.phone = $phone, " +
                                "a.empi = $empi, " +
                                "a.type = $type, " +
                                "a.jcdxh = $jcdxh, " +
                                "a.jcjgxh = $jcjgxh "
                        , parameters("sfzh", ics.getSfzh(), "jzkh", ics.getJzkh(), "bah", ics.getBah(),
                                "zyh", ics.getZyh(), "mzh", ics.getMzh(), "phone", ics.getPhone(), "empi", ics.getEmpi(),
                                "type", ics.getType(), "jcdxh", ics.getJcdxh(), "jcjgxh", ics.getJcjgxh()));
                tx.success();
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("hello")
    @ResponseBody
    public String hello(@RequestParam("jsonStr") String jsonStr) {
        System.out.println(jsonStr);
        return "Roger";
    }
}
