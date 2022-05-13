package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import junit.framework.TestCase;

public class CategoryCommandToCategoryTest extends TestCase {

    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = 1L;
    CategoryCommandToCategory converter;

    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    public void testConvert() {
        //given
        CategoryCommand source = new CategoryCommand();
        source.setId(LONG_VALUE);
        source.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(source);

        //then
        assertNotNull(category);
        assertEquals(LONG_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}