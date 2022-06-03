package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest extends TestCase {
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;
    @Mock
    UnitOfMeasureRepository repository;

    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
    }

    public void testListAllUoms() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom=new UnitOfMeasure();
        unitOfMeasures.add(new UnitOfMeasure().setId(1L));
        unitOfMeasures.add(new UnitOfMeasure().setId(2L));

        when(repository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> unitOfMeasureCommands=service.listAllUoms();

        //then
        assertEquals(2,unitOfMeasureCommands.size());
        verify(repository,times(1)).findAll();
    }
}