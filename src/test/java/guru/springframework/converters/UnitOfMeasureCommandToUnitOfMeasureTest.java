package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import junit.framework.TestCase;

public class UnitOfMeasureCommandToUnitOfMeasureTest extends TestCase {

    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = 1L;

    UnitOfMeasureCommandToUnitOfMeasure converter;

    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    public void testConvert() {
        //given
        UnitOfMeasureCommand source = new UnitOfMeasureCommand();
        source.setId(LONG_VALUE);
        source.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(source);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}