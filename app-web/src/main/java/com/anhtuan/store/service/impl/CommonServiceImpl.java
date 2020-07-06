package com.anhtuan.store.service.impl;

import com.anhtuan.store.dto.request.ProductAddEditDto;
import com.anhtuan.store.dto.response.CategoryResponseDto;
import com.anhtuan.store.dto.response.DiscountResponseDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.DiscountEntity;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.service.CommonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private ModelMapper modelMapper;

    public static String UPLOAD_IMAGE_DIR = System.getProperty("user.dir") + "\\app-web\\src\\main\\resources\\static\\images\\product\\";

    @Override
    public ProductResponseDto transformProductEntityToDto(ProductEntity productEntity) {
        ProductResponseDto res = modelMapper.map(productEntity, ProductResponseDto.class);
        CategoryResponseDto categoryResponseDto = modelMapper.map(productEntity.getCategory(), CategoryResponseDto.class);
        DiscountEntity discountEntity = productEntity.getDiscount();

        Integer salePrice = productEntity.getSalePrice();
        res.setSalePrice(String.format("%,d", salePrice));
        res.setCategory(categoryResponseDto);

        if (isValidDiscount(discountEntity)) {
            res.setIsDiscount(true);
            res.setDiscountPrice(String.format("%,d", (int) salePrice * (100 - discountEntity.getPercent()) / 100));
        } else {
            res.setIsDiscount(false);
        }

        return res;
    }

    @Override
    public ProductAddEditDto transformToAdminProductDtoUpdate(ProductEntity productEntity) {
        ProductAddEditDto res = modelMapper.map(productEntity, ProductAddEditDto.class);
        res.setCategoryId(productEntity.getCategory().getId());
        res.setDescription(productEntity.getDescription());
        if (Objects.nonNull(productEntity.getDiscount())) {
            res.setDiscountId(productEntity.getDiscount().getId());
        }

        return res;
    }

    @Override
    public Boolean isValidDiscount(DiscountEntity discountEntity) {
        if (!Objects.nonNull(discountEntity)) return false;

        return discountEntity.getStartAt().getTime() <= System.currentTimeMillis() && System.currentTimeMillis() <= discountEntity.getEndAt().getTime();
    }

    @Override
    public String saveImageToFolder(MultipartFile file, String folder) throws IOException {
        if (file.isEmpty()) {
            throw Exception.dataConflict().build("image is empty");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            throw e;
        }
        return fileName;
    }

    @Override
    public DiscountResponseDto transformDiscountDto(DiscountEntity entity) {
        DiscountResponseDto dto = modelMapper.map(entity, DiscountResponseDto.class);
        return dto;
    }
}
