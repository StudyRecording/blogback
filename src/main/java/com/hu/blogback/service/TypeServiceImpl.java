package com.hu.blogback.service;

import com.hu.blogback.dao.TypeRepository;
import com.hu.blogback.exception.NotFoundException;
import com.hu.blogback.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
