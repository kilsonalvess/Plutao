package br.edu.ifpb.pweb2.plutao.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service<T, ID> {

    public Page<T> findAll(Pageable p);

    public T findById(ID id);

    public T save(T t);

    public void deleteById(ID id);
}
