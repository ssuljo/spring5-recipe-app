package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import junit.framework.TestCase;

public class UnitOfMeasureToUnitOfMeasureCommandTest extends TestCase {

    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = 1L;

    UnitOfMeasureToUnitOfMeasureCommand converter;

    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    public void testConvert() {
        //given
        UnitOfMeasure source = new UnitOfMeasure();
        source.setId(LONG_VALUE);
        source.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uom = converter.convert(source);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}