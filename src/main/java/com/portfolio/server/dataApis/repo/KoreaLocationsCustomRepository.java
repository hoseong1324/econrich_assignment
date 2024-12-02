package com.portfolio.server.dataApis.repo;

import com.portfolio.server.dataApis.dto.KoreaLocationsDto;
import com.portfolio.server.dataApis.entity.KoreaLocations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class KoreaLocationsCustomRepository {

    private final EntityManager entityManager;

    public List<KoreaLocations> findAllByLocation(KoreaLocationsDto.LocationsCondition condition){
        String stringSql = "select kl from KoreaLocations kl " +
                "where kl.si like concat('%', :si, '%')";
        if(condition.getGu() != null){
            stringSql += " and kl.gu like concat('%', :gu, '%')";
        }
        if(condition.getDong() != null){
            stringSql += " and kl.dong like concat('%', :dong, '%')";
        }

        TypedQuery<KoreaLocations> query = entityManager.createQuery(stringSql, KoreaLocations.class);

        query.setParameter("si", condition.getSi());
        if(condition.getGu() != null){
            query.setParameter("gu", condition.getGu());
        }
        if(condition.getDong() != null){
            query.setParameter("dong", condition.getDong());
        }

        return query.getResultList();
    }
}
