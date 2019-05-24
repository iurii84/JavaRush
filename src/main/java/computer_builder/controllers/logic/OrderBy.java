package computer_builder.controllers.logic;

import computer_builder.entities.ComputerPart;
import computer_builder.repositories.ComputerPartsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class OrderBy {
    private Page<ComputerPart> page;

    public Page<ComputerPart> sort(String mode, ComputerPartsRepository repository, Pageable pageable) {

        switch (mode) {
            case "orderByTitleAsc" : titleAsc(repository, pageable);
            break;

            case "orderByTitleDesc" : titleDesc(repository, pageable);
            break;

            case "orderByQuantityAsc" : quantityAsc(repository, pageable);
            break;

            case "orderByQuantityDesc" : quantityDesc(repository, pageable);
            break;

            case "orderByRequiredTrue" : requiredTrue(repository, pageable);
            break;

            case "orderByRequiredFalse" : requiredFalse(repository, pageable);
            break;

            default : defaultOrder(repository, pageable);
        }







        return page;
    }


    private void titleAsc(ComputerPartsRepository repository, Pageable pageable) {
        page = repository.findAllByOrderByTitleAsc(pageable);
    }

    private void titleDesc(ComputerPartsRepository repository, Pageable pageable) {
        page = repository.findAllByOrderByTitleDesc(pageable);
    }

    private void requiredTrue(ComputerPartsRepository repository, Pageable pageable) {
        page = repository.findAllByRequiredIsTrue(pageable);
    }

    private void requiredFalse(ComputerPartsRepository repository, Pageable pageable) {
        page = repository.findAllByRequiredIsFalse(pageable);
    }

    private void quantityAsc(ComputerPartsRepository repository, Pageable pageable) {
        page = repository.findAllByOrderByQuantityAsc(pageable);
    }

    private void quantityDesc(ComputerPartsRepository repository, Pageable pageable) {
        page = repository.findAllByOrderByQuantityDesc(pageable);
    }

    private void defaultOrder (ComputerPartsRepository repository, Pageable pageable) {
        page = repository.findAll(pageable);
    }


}
