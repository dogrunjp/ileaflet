package jp.dogrun.ileaflet.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ActorTest extends AppEngineTestCase {

    private Actor model = new Actor();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
