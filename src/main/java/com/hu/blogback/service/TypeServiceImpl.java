package com.hu.blogback.service;

import com.hu.blogback.dao.TypeRepository;
import com.hu.blogback.exception.NotFoundException;
import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {

        Type t = typeRepository.save(type);
        return t;
    }

    @Override
    public Type getType(Long id) {

        return typeRepository.getOne(id);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {

        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    public List<Type> listTypeTopByPublished(Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        List<Type> types = typeRepository.findTop(pageable);
        int count = 0;
        for (Type type : types) {
            for (Blog blog : type.getBlogs()) {
                if (blog.isPublished()) {
                    count++;
                }
            }
            type.setPublishedBlogCount(count);
            count = 0;
        }
        return types;
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {

        Type t = typeRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("未查到该类");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {

        typeRepository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {

        return typeRepository.findByName(name);
    }
}
