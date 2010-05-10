package net.timandersen;

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
        int countOfPodcasts = jdbcTemplate.queryForInt("select count(*) from Podcasts");
        assertEquals(212L, (long) countOfPodcasts);
    }

    @Test
    public void testRunGroovy() {
        Main.main(new String[]{"groovy"});
        int countOfPodcasts = jdbcTemplate.queryForInt("select count(*) from Podcasts");
        assertEquals(212L, (long) countOfPodcasts);
    }

    @Test
    public void testRunWithNoArgs() {
        try {
            Main.main(new String[]{});
        }
        catch (Exception e) {
            assertEquals("valid parsers are sax, groovy", e.getMessage());
        }
    }

}
