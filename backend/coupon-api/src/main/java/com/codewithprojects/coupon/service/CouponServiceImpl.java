package com.codewithprojects.coupon.service;

import com.codewithprojects.coupon.dto.ConsumeRequestDTO;
import com.codewithprojects.coupon.dto.CouponDTO;
import com.codewithprojects.coupon.dto.CouponConsumptionHistoryDTO;
import com.codewithprojects.coupon.dto.CouponValidationResponseDTO;
import com.codewithprojects.coupon.model.Coupon;
import com.codewithprojects.coupon.model.CouponConsumptionHistory;
import com.codewithprojects.coupon.repository.CouponConsumptionHistoryRepository;
import com.codewithprojects.coupon.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponConsumptionHistoryRepository historyRepository;

    @Transactional
    @Override
    public CouponDTO addCoupon(CouponDTO couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setCode(couponDTO.getCode());
        coupon.setUsageCount(couponDTO.getUsageCount());
        coupon.setMaxUsages(couponDTO.getMaxUsages());
        coupon.setExpiryDate(couponDTO.getExpiryDate());
        coupon.setValue(couponDTO.getValue());
        coupon.setValueType(couponDTO.getValueType());
        Coupon savedCoupon = couponRepository.save(coupon);
        return convertToDTO(savedCoupon);
    }

    @Transactional
    @Override
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code);
        if (coupon != null) {
            couponRepository.delete(coupon);
        }
    }

    @Override
    public List<CouponDTO> getAllCoupons() {
        return couponRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponConsumptionHistoryDTO> getCouponHistory() {
        return historyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CouponValidationResponseDTO validateCoupon(String code) {
        Coupon coupon = couponRepository.findByCode(code);

        if (coupon == null) {
            return new CouponValidationResponseDTO(false, 0, null, "Coupon not found.");
        }

        if (coupon.getUsageCount() >= coupon.getMaxUsages()) {
            return new CouponValidationResponseDTO(false, 0, null, "Coupon usage limit reached.");
        }

        if (coupon.getExpiryDate().isBefore(LocalDate.now())) {
            return new CouponValidationResponseDTO(false, 0, null, "Coupon expired.");
        }


        return new CouponValidationResponseDTO(true, coupon.getValue(), coupon.getValueType(), null);
    }

    @Transactional
    @Override
    public boolean consumeCoupon(ConsumeRequestDTO consumeRequestDTO) {
        CouponValidationResponseDTO validationResponse = validateCoupon(consumeRequestDTO.getCode());

        if (!validationResponse.isValid()) {
            return false; // Coupon is not valid
        }

        Coupon coupon = couponRepository.findByCode(consumeRequestDTO.getCode());
        // Update coupon usage count
        coupon.setUsageCount(coupon.getUsageCount() + 1);
        couponRepository.save(coupon);

        // Save consumption history
        CouponConsumptionHistory history = new CouponConsumptionHistory();
        history.setCoupon(coupon);
        history.setCustomer_id(consumeRequestDTO.getCustomer_id());
        history.setOrder_id(consumeRequestDTO.getOrder_id());
        history.setConsumptionDate(LocalDateTime.now());
        historyRepository.save(history);

        return true; // Coupon successfully consumed
    }

    @Override
    public CouponDTO getCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code);
        return coupon != null ? convertToDTO(coupon) : null;
    }

    private CouponDTO convertToDTO(Coupon coupon) {
        return new CouponDTO(
                coupon.getId(),
                coupon.getCode(),
                coupon.getUsageCount(),
                coupon.getMaxUsages(),
                coupon.getExpiryDate(),
                coupon.getValue(),
                coupon.getValueType()
        );
    }

    private CouponConsumptionHistoryDTO convertToDTO(CouponConsumptionHistory history) {
        return new CouponConsumptionHistoryDTO(
                history.getId(),
                history.getCoupon().getId(),
                history.getCustomer_id(),
                history.getOrder_id(),
                history.getConsumptionDate()
        );
    }
}
