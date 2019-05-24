package computer_builder.repositories;

import computer_builder.entities.ComputerPart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerPartsRepository extends PagingAndSortingRepository<ComputerPart, Long> {

    Page<ComputerPart> findAll(Pageable pageable);

    Page<ComputerPart> findAllByOrderByTitleAsc (Pageable pageable);
    Page<ComputerPart> findAllByOrderByTitleDesc (Pageable pageable);

    Page<ComputerPart> findAllByOrderByQuantityAsc (Pageable pageable);
    Page<ComputerPart> findAllByOrderByQuantityDesc (Pageable pageable);

    Page<ComputerPart> findAllByRequiredIsTrue (Pageable pageable);
    Page<ComputerPart> findAllByRequiredIsFalse (Pageable pageable);

    List<ComputerPart> findAllByRequiredIsTrueOrderByQuantity();

    Page<ComputerPart> findComputerPartsByTitleContaining (Pageable pageable, String title);

    ComputerPart findFirstById(Long id);



}
