package com.cm.elkdemo.controller;

import com.cm.elkdemo.common.CommonResult;
import com.cm.elkdemo.entity.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
//    @Autowired
//    private PmsBrandService brandService;
    public static List<PmsBrand> list = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
//        brandService.listAllBrand()

        return CommonResult.success(list);
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        boolean b = list.add(pmsBrand);
//        int count = brandService.createBrand(pmsBrand);
        if (b) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }
//
//    @ApiOperation("更新指定id品牌信息")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
//        CommonResult commonResult;
////        int count = brandService.updateBrand(id, pmsBrandDto);
//        if (count == 1) {
//            commonResult = CommonResult.success(pmsBrandDto);
//            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
//        } else {
//            commonResult = CommonResult.failed("操作失败");
//            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
//        }
//        return commonResult;
//    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Integer id) {
//        int count = brandService.deleteBrand(id);
        boolean b = list.remove(id);
        if (b) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

//    @ApiOperation("分页查询品牌列表")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
//                                                        @ApiParam("页码") Integer pageNum,
//                                                        @RequestParam(value = "pageSize", defaultValue = "3")
//                                                        @ApiParam("每页数量") Integer pageSize) {
//        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
//        return CommonResult.success(CommonPage.restPage(brandList));
//    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Integer id) {
        PmsBrand pmsBrand = list.get(id);
        boolean remove = list.remove(id);
        return CommonResult.success(pmsBrand);
    }
}