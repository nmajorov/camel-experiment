
package biz.majorov.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Logger implements Processor {
	private static final Log logger = LogFactory.getLog("NM");
    public void process(Exchange exchange) throws Exception {
    	
        logger.info("We just get: " 
                + exchange.getIn().getBody());
    }
}
