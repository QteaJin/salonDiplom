package com.salon.controller.discount;


import com.salon.repository.bean.discount.DiscountBean;
import com.salon.service.discount.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    private DiscountService discountService;

    @Autowired
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DiscountBean findById(@PathVariable("id") long id) {
        return discountService.findById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<DiscountBean> findAllDiscount() {
        return discountService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public DiscountBean createDiscount(@RequestBody DiscountBean discountBean) {
        return discountService.save(discountBean);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id) {
        discountService.delete(discountService.findById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public DiscountBean updateDiscount(@RequestBody DiscountBean discountBean) {
        return discountService.update(discountBean);
    }
}
