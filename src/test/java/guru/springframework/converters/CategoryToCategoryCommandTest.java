package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import junit.framework.TestCase;

public class CategoryToCategoryCommandTest extends TestCase {

    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = 1L;
    CategoryToCategoryCommand converter;

    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(converter.convert(new Category()));
    }

    public void testConvert() {
        //given
        Category source = new Category();
        source.setId(LONG_VALUE);
        source.setDescription(DESCRIPTION);

        //when
        CategoryCommand category = converter.convert(source);

        //then
        assertNotNull(category);
        assertEquals(LONG_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}