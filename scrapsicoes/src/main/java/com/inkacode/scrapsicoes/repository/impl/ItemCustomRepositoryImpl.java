package com.inkacode.scrapsicoes.repository.impl;

import com.inkacode.scrapsicoes.domain.Item;
import com.inkacode.scrapsicoes.repository.ItemCustomRepository;
import com.inkacode.scrapsicoes.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ItemCustomRepositoryImpl implements ItemCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Item> searchItems(String search, Pageable pageable) {
        Query searchQuery = new Query();
        Criteria criteria = new Criteria();
        for (String field : Constants.ITEMS_FIELDS_CRITERIA) {
            criteria.orOperator(
                    Criteria.where(field).regex(search, "i")
            );
        }
        searchQuery.addCriteria(criteria);
        Long totalCount = mongoTemplate.count(searchQuery, Item.class);
        searchQuery.with(pageable);
        Streamable<Item> streamable = Streamable.of(mongoTemplate.find(searchQuery, Item.class));
        return PageableExecutionUtils.getPage(streamable.toList(), pageable, () -> totalCount);
    }
}
