package com.cm.elkdemo.controller;

import com.cm.elkdemo.common.R;
import com.cm.elkdemo.entity.MemberReadHistory;
import com.cm.elkdemo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 * Created by macro on 2018/8/3.
 */
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
    @Autowired
    private MemberReadHistoryService memberReadHistoryService;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public R create() {

        MemberReadHistory memberReadHistory = new MemberReadHistory();
        memberReadHistory.setMemberId(1l);
        memberReadHistory.setMemberIcon("1");
        memberReadHistory.setMemberNickname("1");
        memberReadHistory.setCreateTime(new Date());
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return R.ok().code(count);
        } else {
            return R.error();
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public R delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return R.ok().code(count);
        } else {
            return R.error();
        }

    }


  @GetMapping("list/{id}")
    public R list(@PathVariable Long id) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(id);
        return R.ok().list(memberReadHistoryList);
    }
}
