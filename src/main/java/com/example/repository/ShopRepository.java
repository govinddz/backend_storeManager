package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.example.dto.ShopSearchDto;
import com.example.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Repository
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long>, JpaSpecificationExecutor<Shop> {

    @Query(value = "select s.id from shop s "
            + "join address a on s.address_id = a.id "
            + "where ST_DWithin(ST_MakePoint(a.longitude,a.latitude), ST_MakePoint(:longitude, :latitude), :radius, true)", nativeQuery = true)
    List<Long> findNearest(Double longitude, Double latitude, Integer radius);

    default Page<Shop> findAll(ShopSearchDto shopSearchDto, Pageable pageable) {
        Specification<Shop> specification = new Specification<Shop>() {
            @Override
            public Predicate toPredicate(
                    final Root root, final CriteriaQuery query, final CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(isNotBlank(shopSearchDto.getShopName())) {
//                    predicates.add(criteriaBuilder.like(root.get("shopName"), "%" + shopSearchDto.getShopName() + "%"));
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("shopName")), "%"+ shopSearchDto.getShopName().toUpperCase() +"%"));
                }

                if(nonNull(shopSearchDto.getLocation()) && shopSearchDto.getLocation().getLatitude() > 0 && shopSearchDto.getLocation().getLongitude() > 0) {
                    // H2 GEO QUERY
                    predicates.add(criteriaBuilder.and(root.get("id").in(findNearest(shopSearchDto.getLocation().getLongitude(), shopSearchDto.getLocation().getLatitude(), 2500)))); // radius in meters
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        return findAll(specification, pageable);

    }

}
