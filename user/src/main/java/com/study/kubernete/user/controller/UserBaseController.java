package com.study.kubernete.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.study.kubernete.user.bllserver.*;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;



@Api(tags = "用户")
@RestController
@Validated
@RequestMapping("/api/userBase")
class UserBaseController {
    @Autowired
    private  UserBaseServiceImpl userBaseService;
    @Autowired
    private Sequence sequence;
    @ApiOperation("UserBase添加")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody @Validated UserBaseAddReq entity) {
        //if (entity.getId() == null) {
           // entity.setId(sequence.nextId());
      //  }
        return userBaseService.save(entity);
    }
    @ApiOperation("UserBase修改(id)")
    @PostMapping("/updateid")
    public Result<Boolean> updateById(@RequestBody @Validated UserBaseEditReq entity) {
        return userBaseService.updateById(entity);
    }
    @ApiOperation("UserBase修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated List<UserBaseEditReq> entities) {

        return userBaseService.update(entities);
    }
    @ApiOperation("UserBase查询(id)")
    @GetMapping("/getid/{id}")
    public Result<UserBaseResp> getById(@PathVariable("id") Long id) {
        return userBaseService.getById(id);
    }
    @ApiOperation("UserBase单条查询")
    @PostMapping("/getone")
    public Result<UserBaseResp> getOne(@RequestBody @Validated UserBaseQueryReq entity) {
        return userBaseService.getOne(entity);
    }
    @ApiOperation("UserBase删除")
    @PutMapping("/delid/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return userBaseService.deleteById(id);
    }
    @ApiOperation("UserBase查询")
    @PostMapping("/select")
    public Result<List<UserBaseResp>> selectPage(@RequestBody @Validated QueryPage<UserBaseQueryReq> entity) {
        return userBaseService.selectPage(entity);
    }
    @ApiOperation("UserBase In查询")
    @PostMapping("/selectin")
    public Result<List<UserBaseResp>> selectIn(@RequestBody @Validated QueryIn<UserBaseQueryReq, Long> entity) {
        return userBaseService.selectIn(entity);
    }
    @ApiOperation("UserBase Between查询")
    @PostMapping("/selectbetween")
    public Result<List<UserBaseResp>> selectBetween(@RequestBody @Validated QueryBetween<UserBaseQueryReq> entity) {
        return userBaseService.selectBetween(entity);
    }
    @ApiOperation("UserBase查询(组合)")
    @PostMapping("/selectquery")
    public Result<List<UserBaseResp>> selectQuery(@RequestBody @Validated QueryFull<UserBaseQueryReq, Long> entity) {
        return userBaseService.selectQuery(entity);
    }
    @ApiOperation("UserBase like 查询")
    @PostMapping("/like")
    public Result<List<UserBaseResp>> selectLike(@RequestBody @Validated QueryLike<UserBaseQueryReq> entity) {
        return userBaseService.selectLike(entity);
    }
    @ApiOperation("UserBase list 查询")
    @PostMapping("/list")
    public Result<List<UserBaseResp>> selectList(@RequestBody @Validated UserBaseQueryReq entity) {
        return userBaseService.selectList(entity);
    }
}