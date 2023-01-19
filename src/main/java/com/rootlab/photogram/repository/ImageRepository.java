package com.rootlab.photogram.repository;

import com.rootlab.photogram.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}