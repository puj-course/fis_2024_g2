package org.musify.repository;

import org.musify.model.GeneroMusical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository <GeneroMusical, String> {
}
