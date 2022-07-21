package com.example.eshop.service;

import com.example.eshop.domain.Bucket;
import com.example.eshop.domain.User;
import com.example.eshop.dto.BucketDto;

import java.security.Principal;
import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(String name);
    void commitBucketToOrder(String username);
}
