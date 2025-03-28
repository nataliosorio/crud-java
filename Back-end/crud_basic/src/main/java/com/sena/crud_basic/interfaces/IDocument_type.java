package com.sena.crud_basic.interfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.Document_type;

@Repository
public interface IDocument_type extends JpaRepository<Document_type, Integer> {

}
