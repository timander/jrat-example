package net.timandersen;

import net.timandersen.podcast.Main;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.*;


public class MainTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        ApplicationContext context = Main.getContext();
        jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcTemplate.execute("delete from Podcasts");

    }

    @Test
    public void testRunSax() {
        Main.main(new String[]{"sax"});
        assertCount(212L);
    }

    @Test
    public void testRunDom() {
        Main.main(new String[]{"dom"});
        assertCount(212L);
    }

    @Test
    public void testRunVtd() {
        Main.main(new String[]{"vtd"});
        assertCount(212L);
    }

    @Test
    public void testRunGroovy() {
        Main.main(new String[]{"groovy"});
        assertCount(212L);
    }

    @Test
    public void testRunInvalid() {
        try {
            Main.main(new String[]{"invalid"});
        }
        catch (Exception e) {
            assertEquals("valid parsers are [dom, sax, vtd, groovy]", e.getMessage());
        }
    }

    private void assertCount(long count) {
        int countOfPodcasts = jdbcTemplate.queryForInt("select count(*) from Podcasts");
        assertEquals(count, (long) countOfPodcasts);
    }

}
