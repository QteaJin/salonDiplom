package com.salon.repository.dao.discount;

import com.salon.repository.entity.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountDAO extends JpaRepository<Discount, Long> {
}
