package com.selimsahin.foreignexchangeapplication.repository;

import com.selimsahin.foreignexchangeapplication.entity.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    List<Conversion> findAllById(Long id);

    List<Conversion> findAllByCreateTimeContains(String date);

    Page<Conversion> findAllByTransactionId(String id, Pageable pageable);

    Page<Conversion> findAllByCreateTime(LocalDate localDate, Pageable pageable);
}
