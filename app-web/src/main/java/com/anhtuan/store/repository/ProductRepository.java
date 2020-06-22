package com.anhtuan.store.repository;

import com.anhtuan.store.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, QuerydslPredicateExecutor<ProductEntity> {
    Integer countByCategoryIdAndStatusEquals(Integer categoryId, Integer status);

    Optional<ProductEntity> findByIdAndDeleteFlagAndStatus(Integer productId, Integer deletedFlag, Integer status);

    Optional<ProductEntity> findByIdAndDeleteFlag(Integer productId, Integer deletedFlag);

    @Query("SELECT max(salePrice) from ProductEntity")
    Integer findTopByOrderBySalePriceDesc();

    @Query("SELECT min(salePrice) from ProductEntity")
    Integer findTopByOrderBySalePriceAsc();

    @Query(value = "select * from product join ( \n" +
            "            select product_id \n" +
            "            from (select oi.product_id, sum(oi.quantity) quantity \n" +
            "                  from store.order_items oi \n" +
            "            join orders o on oi.order_id = o.id \n" +
            "            where o.status = 2 \n" +
            "                  group by product_id \n" +
            "                  order by quantity desc) as temp \n" +
            "            limit  :limit) as temp2 on product.id = temp2.product_id where product.status = 2 and delete_flag = 0", nativeQuery = true)
    List<ProductEntity> findByBestSellingProduct(@Param("limit") Integer limit);
}
