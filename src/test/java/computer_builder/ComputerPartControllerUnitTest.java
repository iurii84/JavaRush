package computer_builder;

import computer_builder.controllers.ComputerPartsController;
import computer_builder.entities.ComputerPart;
import computer_builder.repositories.ComputerPartsRepository;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComputerPartControllerUnitTest {
    private static ComputerPartsController mockedComputerPartsController;
    private static ComputerPartsRepository mockedComputerPartsRepository;
    private static Model mockedModel;
    private static BindingResult mockedBindingResult;


    @BeforeClass
    public static void setUpControllerInstances() {
        mockedComputerPartsRepository = mock(ComputerPartsRepository.class);
        mockedComputerPartsController = new ComputerPartsController(mockedComputerPartsRepository);
        mockedModel = mock(Model.class);
        mockedBindingResult =  mock(BindingResult.class);
    }

    @Test
    public void whenCalledAddPartWithValidPart(){
        ComputerPart part1 = new ComputerPart("monitor", 5, false);
        ComputerPart part2 = new ComputerPart("RAM", 74, true);

        when(mockedBindingResult.hasErrors()).thenReturn(false);
        assertEquals(mockedComputerPartsController.addPart(part1, mockedBindingResult, mockedModel), "redirect:/");
        assertEquals(mockedComputerPartsController.addPart(part2, mockedBindingResult, mockedModel), "redirect:/");
    }

    @Test
    public void whenCalledAddPartWithInvalidPart(){
        ComputerPart part1 = new ComputerPart("", 5, false);
        ComputerPart part2 = new ComputerPart("RAM", -1, true);

        when(mockedBindingResult.hasErrors()).thenReturn(true);
        assertEquals(mockedComputerPartsController.addPart(part1, mockedBindingResult, mockedModel), "add-part");
        assertEquals(mockedComputerPartsController.addPart(part2, mockedBindingResult, mockedModel), "add-part");
    }


}
