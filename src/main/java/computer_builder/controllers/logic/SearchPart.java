package computer_builder.controllers.logic;

import computer_builder.entities.ComputerPart;
import computer_builder.repositories.ComputerPartsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SearchPart {
    public Page<ComputerPart> execute(String searchString, ComputerPartsRepository repository, Pageable pageable) {
        return repository.findComputerPartsByTitleContaining(pageable, searchString);
    }
}
