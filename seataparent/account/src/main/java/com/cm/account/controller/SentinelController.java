package com.cm.account.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class SentinelController {


    @RequestMapping(value = "test1")
    public void b() {
        instanceFlowRules();
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
            System.out.println("HelloWorld");
        } catch (BlockException e) {
            System.out.println("block...");
        } finally {
            if(entry!=null){
                entry.exit();
            }
        }
    }

    private static void instanceFlowRules() {
        List<FlowRule> list = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("HelloWorld");
        //20qps
        flowRule.setCount(3);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        list.add(flowRule);
        FlowRuleManager.loadRules(list);
    }
}
