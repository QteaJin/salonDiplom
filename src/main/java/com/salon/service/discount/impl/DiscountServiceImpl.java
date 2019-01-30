package com.salon.service.discount.impl;

import com.salon.repository.bean.discount.DiscountBean;
import com.salon.repository.dao.discount.DiscountDAO;
import com.salon.repository.entity.discount.Discount;
import com.salon.service.discount.DiscountService;
import com.salon.utility.EnumStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountServiceImpl.class);

    @Autowired
    private DiscountDAO discountDAO;

    public DiscountDAO getDiscountDAO() {
        return discountDAO;
    }

    @Override
    public DiscountBean save(DiscountBean bean) {
        LOGGER.debug("Discount save start");

        Discount discount = discountDAO.save(toDomain(bean));

        LOGGER.debug("Discount save finish");

        return toBean(discount);
    }

    @Override
    public List<DiscountBean> findAll() {
        LOGGER.debug("find all Discount start");

        List<Discount> discount = discountDAO.findAll();

        LOGGER.debug("find all Discount finish");

        return toBean(discount);
    }

    @Override
    public DiscountBean findById(Long id) {
        LOGGER.debug("find by id Discount start");

        Optional<Discount> discount = discountDAO.findById(id);

        LOGGER.debug("find by id Discount finish");

        return toBean(discount.get());
    }

    @Override
    public DiscountBean update(DiscountBean bean) {
        LOGGER.debug("update Discount start");

        Discount discount = discountDAO.saveAndFlush(toDomain(bean));

        LOGGER.debug("update Discount finish");

        return toBean(discount);
    }

    @Override
    public void delete(DiscountBean bean) {
        discountDAO.delete(toDomain(bean));

    }

    @Override
    public List<DiscountBean> findByExample(DiscountBean bean) {
        LOGGER.debug("find by example Discount start");

        List<Discount> discount = discountDAO.findAll(Example.of(toDomain(bean)));

        LOGGER.debug("find by example Discount finish");
        return toBean(discount);
    }

    @Override
    public DiscountBean toBean(Discount domain) {
        DiscountBean discountBean = new DiscountBean();
        discountBean.setDiscountId(domain.getDiscountId());
        discountBean.setName(domain.getName());
        discountBean.setDiscount(domain.getDiscount());
        discountBean.setDescription(domain.getDescription());
        discountBean.setStatus(domain.getStatus());
        return discountBean;
    }

    @Override
    public Discount toDomain(DiscountBean bean) {
        Discount discount = new Discount();
        discount.setDiscountId(bean.getDiscountId());
        discount.setName(bean.getName());
        discount.setDiscount(bean.getDiscount());
        discount.setDescription(bean.getDescription());

        if (Objects.isNull(bean.getStatus())) {
            discount.setStatus(EnumStatus.NOACTIVE);
        } else {
            discount.setStatus(bean.getStatus());
        }
        return discount;
    }

    private List<DiscountBean> toBean(List<Discount> discounts) {
        List<DiscountBean> discountBeans = new ArrayList<>();
        for (Discount discount : discounts) {
            discountBeans.add(toBean(discount));
        }
        return discountBeans;
    }
}
