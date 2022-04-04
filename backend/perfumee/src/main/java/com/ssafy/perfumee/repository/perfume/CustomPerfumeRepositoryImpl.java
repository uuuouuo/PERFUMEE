package com.ssafy.perfumee.repository.perfume;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.SearchDto;
import com.ssafy.perfumee.model.entity.perfume.Perfume;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.perfumee.model.entity.perfume.QContain.contain;
import static com.ssafy.perfumee.model.entity.perfume.QPerfume.perfume;
import static com.ssafy.perfumee.model.entity.perfume.QNote.note;

@Repository
public class CustomPerfumeRepositoryImpl implements CustomPerfumeRepository {

    private JPAQueryFactory jpaQueryFactory;
    public CustomPerfumeRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

//    select distinct *
//    from perfume
//    where brand in (Brands) &&
//    gender in (Genders) &&
//    perfume.perfume_no in (
//        select distinct perfume_no
//        from contain
//        where contain.note_no in (
//            select distinct note.note_no
//            from note
//            where note.name in (Notes)
//        )
//    );
    @Override
    public List<Perfume> search(SearchDto searchDto) {
        List<Perfume> query = jpaQueryFactory
                .selectDistinct(perfume)
                .from(perfume)
                .where(searchCheck(searchDto.getBrand(), searchDto.getNote(), searchDto.getGender()))
                .fetch();
        return query;
    }

    private BooleanBuilder searchCheck(List<String> brands, List<String> notes, List<String> genders){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(notes != null){
            List<Perfume> subQuery = createNoteSubQuery(notes);
            booleanBuilder.and(perfume.in(subQuery));
        }

        if(brands != null){
            booleanBuilder.and(perfume.brand.in(brands));
        }

        if(genders != null){
            booleanBuilder.and(perfume.gender.in(genders));
        }

        return booleanBuilder;
    }

    private List<Perfume> createNoteSubQuery(List<String> notes) {
        List<Perfume> fetch = jpaQueryFactory.selectDistinct(contain.perfume)
                .from(contain)
                .where(contain.note.in(
                        JPAExpressions
                                .selectDistinct(note)
                                .from(note)
                                .where(note.name.in(notes))
                )).fetch();
        return fetch;
    }

}
