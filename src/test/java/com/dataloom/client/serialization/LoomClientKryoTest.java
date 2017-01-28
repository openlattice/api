package com.dataloom.client.serialization;

import com.dataloom.client.LoomClient;
import com.dataloom.client.RetrofitFactory;
import com.dataloom.edm.EdmApi;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.ClosureSerializer;
import org.junit.Assert;
import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class LoomClientKryoTest {
    private static Kryo kryo = new Kryo();

    static {
        kryo.setInstantiatorStrategy(
                new Kryo.DefaultInstantiatorStrategy(
                        new StdInstantiatorStrategy() ) );
        kryo.register( Object[].class );
        kryo.register( java.lang.Class.class );
        kryo.register( ClosureSerializer.Closure.class,
                new ClosureSerializer() );
    }

    @Test
    public void testSerdes() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Output output = new Output( os );
        LoomClient expected = new LoomClient( RetrofitFactory.Environment.TESTING, () -> "sometoken" );
        kryo.writeClassAndObject( output, expected );
        output.flush();

        byte[] bytes = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream( bytes );
        Input input = new Input( is );
        LoomClient actual = (LoomClient) kryo.readClassAndObject( input );

        //This will null pointer if custom initialization isn't handled correctly.
        Assert.assertNotNull( actual.get().create( EdmApi.class ) );
    }
}
