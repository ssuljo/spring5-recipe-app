package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import junit.framework.TestCase;

public class NotesCommandToNotesTest extends TestCase {

    private static final String RECIPE_NOTES = "notes";
    private static final Long ID_VALUE = 1L;

    NotesCommandToNotes converter;

    public void setUp() throws Exception {
        converter=new NotesCommandToNotes();
    }

    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    public void testConvert() {

        //given
        NotesCommand source = new NotesCommand();
        source.setId(ID_VALUE);
        source.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(source);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}