package jp.dogrun.ileaflet.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ContentTest extends AppEngineTestCase {

    private Content model = new Content();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
