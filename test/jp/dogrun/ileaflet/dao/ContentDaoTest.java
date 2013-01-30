package jp.dogrun.ileaflet.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ContentDaoTest extends AppEngineTestCase {

    private ContentDao dao = new ContentDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
