package computer_builder.controllers;

import computer_builder.controllers.logic.OrderBy;
import computer_builder.controllers.logic.SearchPart;
import computer_builder.entities.ComputerPart;
import computer_builder.repositories.ComputerPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
public class ComputerPartsController {

    private final ComputerPartsRepository repository;

    private String orderMode = "default";
    private OrderBy orderBy = new OrderBy();
    private SearchPart searchPart = new SearchPart();

    private String stringToSearch = "";

    private boolean requiredOrderInverted = false;
    private boolean titleOrderInverted = false;
    private boolean quantityOrderInverted = false;

    private boolean searchModeIsActive = false; //true when searching item

    @Autowired
    public ComputerPartsController(ComputerPartsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String showAllParts(Model model, @PageableDefault(sort = {"id"},
                                                             direction = Sort.Direction.DESC) Pageable pageable) {

        int quantity = getQuantity();
        Page<ComputerPart> pages;

        if (searchModeIsActive) {
            pages = searchPart.execute(stringToSearch, repository, pageable);
        }
        else {
            pages = orderBy.sort(orderMode, repository, pageable);
        }

        model.addAttribute("searchMode", searchModeIsActive);
        model.addAttribute("parts", pages);
        model.addAttribute("quantity", quantity);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("products", pages.getContent());
        return "index";
    }

    @GetMapping("/new_part_form")
    public String showAddNewPartForm(ComputerPart part) {

        return "add-part";
    }



    @PostMapping("/add_part")
    public String addPart(@Valid ComputerPart computerPart,
                          BindingResult result,
                          Model model){

        if (result.hasErrors()) {
            return ("add-part");
        }
        repository.save(computerPart);
        model.addAttribute("parts", repository.findAll());
        return "redirect:/";
}

    @GetMapping("/delete_part/{id}")
    public String deletePart(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit_part/{id}")
    public String editPart(@PathVariable Long id,
                           Model model) {
        ComputerPart partToEdit = repository.findFirstById(id);
        model.addAttribute("edit_part", partToEdit);

        return "/edit-part";
    }


    @PostMapping("/update_part")
    public String saveProduct(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam Integer quantity,
            @RequestParam(value = "required", required = false) boolean isRequired) {


        ComputerPart updatedPart = repository.findFirstById(id);
        updatedPart.setTitle(title);
        updatedPart.setQuantity(quantity);
        updatedPart.setRequired(isRequired);

        repository.save(updatedPart);
        return "redirect:/";
    }


    @GetMapping("/orderByTitle")
    public String orderByTitle() {
        //orderMode = "orderByTitleAsc";
        if (titleOrderInverted) {
            orderMode = "orderByTitleDesc";
            titleOrderInverted = false;
        }
        else {
            orderMode = "orderByTitleAsc";
            titleOrderInverted = true;
        }
        return "redirect:/";
    }

    @GetMapping("/orderByRequired")
    public String orderByRequired() {

        if (requiredOrderInverted) {
            orderMode = "orderByRequiredFalse";
            requiredOrderInverted = false;
        }
        else {
            orderMode = "orderByRequiredTrue";
            requiredOrderInverted = true;
        }
        return "redirect:/";
    }

    @GetMapping("/orderByQuantity")
    public String orderByQuantity() {

        if (quantityOrderInverted) {
            orderMode = "orderByQuantityDesc";
            quantityOrderInverted = false;
        }
        else {
            orderMode = "orderByQuantityAsc";
            quantityOrderInverted = true;
        }
        return "redirect:/";
    }

    @PostMapping("/search_part")
    public String searchPart(@RequestParam String search){
        searchModeIsActive = true;
        stringToSearch = search;
        return "redirect:/";
    }

    @GetMapping("/back")
    public String backToListView() {
        searchModeIsActive = false;
        orderMode = "default";
        return "redirect:/";
    }


    private int getQuantity() {
        List<ComputerPart> products = repository.findAllByRequiredIsTrueOrderByQuantity();
        return products == null || products.size() == 0 ? 0 : products.get(0).getQuantity();
    }
}
