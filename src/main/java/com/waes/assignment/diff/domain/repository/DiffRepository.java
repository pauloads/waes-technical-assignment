package com.waes.assignment.diff.domain.repository;

import com.waes.assignment.diff.domain.model.Diff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiffRepository extends JpaRepository<Diff, Long> {
}
