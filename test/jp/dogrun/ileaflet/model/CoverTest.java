package jp.dogrun.ileaflet.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CoverTest extends AppEngineTestCase {

    private Cover model = new Cover();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
