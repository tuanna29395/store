package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.commons.enums.StatusType;
import com.anhtuan.store.commons.enums.UserStatus;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.ProductAddEditDto;
import com.anhtuan.store.dto.response.ReviewResponseDto;
import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.dto.request.ReviewReqDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.CategoryEntity;
import com.anhtuan.store.model.DiscountEntity;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.model.QProductEntity;
import com.anhtuan.store.model.QReviewEntity;
import com.anhtuan.store.model.ReviewEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.CategoryRepository;
import com.anhtuan.store.repository.DiscountRepository;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.repository.ReviewRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.CommonService;
import com.anhtuan.store.service.ProductService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class
ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CommonService commonService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DiscountRepository discountRepository;

    public static String UPLOAD_IMAGE_DIR = System.getProperty("user.dir") + "\\app-web\\src\\main\\resources\\static\\images\\product\\";

    @Override
    public Page<ProductResponseDto> search(ProductSearchRqDto searchRqDto, Pageable pageable) {
        return productRepository.findAll(buildCondition(searchRqDto), pageable).map(entity -> commonService.transformProductEntityToDto(entity));
    }

    @Override
    public ProductResponseDto findById(Integer productId) {
        ProductEntity productEntity = productRepository.findByIdAndDeleteFlagAndStatus(productId, DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
                .orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, productId), HttpStatus.NOT_FOUND.value()));

        return commonService.transformProductEntityToDto(productEntity);
    }

    @Override
    public ProductAddEditDto getProductAdminDetail(Integer productId) {
        ProductEntity productEntity = productRepository.findByIdAndDeleteFlag(productId, DeleteFlag.NOT_DELETE.getVal())
                .orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, productId), HttpStatus.NOT_FOUND.value()));

        return commonService.transformToAdminProductDtoUpdate(productEntity);
    }

    @Override
    public Long getMaxPrice() {
        return (long) productRepository.findTopByOrderBySalePriceDesc();
    }

    @Override
    public Long getMinPrice() {
        return (long) productRepository.findTopByOrderBySalePriceAsc();
    }

    @Override
    public void addReview(Principal principal, ReviewReqDto dto, Integer productId) {
        ReviewEntity reviewEntity = new ReviewEntity();

        UserEntity userEntity = userRepository.findByEmailAndDeletedFlag(principal.getEmail(), UserStatus.NOT_DELETE.getValue())
                .orElseThrow(() -> Exception.dataNotFound()
                        .build(String.format(ErrorMessage.User.USER_NOT_FOUND, principal.getEmail())));

        ProductEntity productEntity = productRepository.findByIdAndDeleteFlagAndStatus(productId, DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
                .orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, productId), HttpStatus.NOT_FOUND.value()));

        reviewEntity.setUser(userEntity);
        reviewEntity.setProduct(productEntity);
        reviewEntity.setNumberStar(dto.getNumberStar());
        reviewEntity.setReviewContent(dto.getContent());

        reviewRepository.save(reviewEntity);

    }

    @Override
    public Page<ReviewResponseDto> listReview(Integer productId, Pageable pageable) {
        BooleanBuilder condition = new BooleanBuilder();
        QReviewEntity qReviewEntity = QReviewEntity.reviewEntity;
        condition.and(qReviewEntity.product.id.eq(productId));
        return reviewRepository.findAll(condition, pageable).map(reviewEntity -> modelMapper.map(reviewEntity, ReviewResponseDto.class));
    }

    @Override
    public List<ProductResponseDto> getAll(ProductSearchRqDto dto) {
        BooleanBuilder condition = new BooleanBuilder();
        QProductEntity productEntity = QProductEntity.productEntity;

        if (dto.getStatus() != 0) {
            condition.and(productEntity.status.eq(dto.getStatus()));
        }

        condition.and(productEntity.deleteFlag.eq(StatusType.NOT_DELETE.getVal()));
        List<ProductEntity> entities = (List<ProductEntity>) productRepository.findAll(condition);
        return entities.stream().map(entity -> commonService.transformProductEntityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Integer productId, ProductAddEditDto dto) {
        ProductEntity productEntity = productRepository.findByIdAndDeleteFlag(productId, DeleteFlag.NOT_DELETE.getVal())
                .orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, productId), HttpStatus.NOT_FOUND.value()));

        productEntity.setStatus(dto.getStatus());
        productRepository.save(productEntity);
    }

    @Override
    public void createProduct(ProductAddEditDto dto) throws IOException {
        CategoryEntity categoryEntity = categoryRepository.findByIdAndDeleteFlag(dto.getCategoryId(), StatusType.NOT_DELETE.getVal());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(dto.getName());
        productEntity.setOriginalPrice(dto.getOriginalPrice());
        productEntity.setSalePrice(dto.getSalePrice());
        productEntity.setStatus(dto.getStatus());
        productEntity.setCategory(categoryEntity);
        productEntity.setDescription(dto.getDescription());
        productEntity.setDeleteFlag(DeleteFlag.NOT_DELETE.getVal());

        if (Objects.nonNull(dto.getDiscountId())) {
            DiscountEntity discountEntity = discountRepository.findByIdAndDeleteFlag(dto.getDiscountId(), DeleteFlag.NOT_DELETE.getVal());
            productEntity.setDiscount(discountEntity);
        }
        String imageName = saveImageToFolder(dto.getFileImage());
        productEntity.setImageUrl(imageName);
        productRepository.save(productEntity);
    }

    @Override
    public String saveImageToFolder(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw Exception.dataConflict().build("image is empty");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_IMAGE_DIR + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            throw e;
        }
        return fileName;
    }

    @Override
    public void update(ProductAddEditDto dto, Integer id) {
        ProductEntity productEntity = productRepository.findByIdAndDeleteFlag(id, DeleteFlag.NOT_DELETE.getVal())
                .orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, id), HttpStatus.NOT_FOUND.value()));

        CategoryEntity categoryEntity = categoryRepository.findByIdAndDeleteFlag(dto.getCategoryId(), StatusType.NOT_DELETE.getVal());

        productEntity.setName(dto.getName());
        productEntity.setOriginalPrice(dto.getOriginalPrice());
        productEntity.setSalePrice(dto.getSalePrice());
        productEntity.setStatus(dto.getStatus());
        productEntity.setCategory(categoryEntity);
        productEntity.setDescription(dto.getDescription());
        productEntity.setDeleteFlag(DeleteFlag.NOT_DELETE.getVal());

        if (Objects.nonNull(dto.getDiscountId())) {
            if (dto.getDiscountId() != 0) {
                DiscountEntity discountEntity = discountRepository.findByIdAndDeleteFlag(dto.getDiscountId(), DeleteFlag.NOT_DELETE.getVal());
                productEntity.setDiscount(discountEntity);
            } else {
                productEntity.setDiscount(null);
            }
        }
        String imageName = dto.getImageUrl();
        try {
            if (!dto.getFileImage().isEmpty()) {
                imageName = saveImageToFolder(dto.getFileImage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        productEntity.setImageUrl(imageName);
        productRepository.save(productEntity);

    }

    @Override
    public void delete(Integer id) {
        ProductEntity productEntity = productRepository.findByIdAndDeleteFlag(id, DeleteFlag.NOT_DELETE.getVal())
                .orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, id), HttpStatus.NOT_FOUND.value()));
        productEntity.setDeleteFlag(StatusType.DELETED.getVal());
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductResponseDto> litProductBestSelling(Integer numberProduct) {
        return productRepository.findByBestSellingProduct(numberProduct)
                .stream().map(product -> commonService.transformProductEntityToDto(product))
                .collect(Collectors.toList());
    }

    private BooleanBuilder buildCondition(ProductSearchRqDto searchRqDto) {
        BooleanBuilder condition = new BooleanBuilder();
        QProductEntity productEntity = QProductEntity.productEntity;
        if (Objects.nonNull(searchRqDto.getCategoryId())) {
            condition.and(productEntity.category.id.eq(searchRqDto.getCategoryId()));
        }
        if (Objects.nonNull(searchRqDto.getName())) {
            condition.and(productEntity.name.containsIgnoreCase(searchRqDto.getName()));
        }
        if (Objects.nonNull(searchRqDto.getMinPrice())) {
            condition.and(productEntity.salePrice.goe(searchRqDto.getMinPrice()));
        }
        if (Objects.nonNull(searchRqDto.getMaxPrice())) {
            condition.and(productEntity.salePrice.loe(searchRqDto.getMaxPrice()));
        }
        condition.and(productEntity.category.status.eq(StatusType.ENABLE.getVal())
                .and(productEntity.deleteFlag.eq(DeleteFlag.NOT_DELETE.getVal())))
                .and(productEntity.status.eq(StatusType.ENABLE.getVal()));
        return condition;
    }

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("Working Directory = " + FileSystems.getDefault().getPath("/product"));
    }

}
