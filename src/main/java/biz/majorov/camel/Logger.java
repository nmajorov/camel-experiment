
package biz.majorov.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Logger implements Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println("We just get: " 
                + exchange.getIn().getHeader("CamelFileName"));
    }
}
