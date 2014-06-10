/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package biz.majorov.camel.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.majorov.camel.UDPReceiver;

/**
 * The Custom made Camel Consumer.
 */
public class NMConsumer extends DefaultConsumer  {
   
	
    private static final Log logger = LogFactory.getLog(NMConsumer.class);

    public NMConsumer(NMEndpoint endpoint, Processor processor,String uri) {
        super(endpoint, processor);
        
    }

    public NMConsumer(NMEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
    }
    
    @Override
    protected void doStart() throws Exception {
        super.doStart();
        logger.info("start ");
         
        SocketHandler handler = new ReceiverHandler();
        UDPReceiver receiver = new UDPReceiver();
        receiver.listen(handler);
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        logger.info("do stop");
    }


	public void run() {
		logger.info("run");
		
		try {
			Exchange exchange = getEndpoint().createExchange();
			exchange.getIn().setBody("");
		} catch (Exception ex) {
			log.error("Error", ex);
		}
	}
   
	 private final class ReceiverHandler implements SocketHandler{

		public void messageReceived(Object message) throws Exception {
			
                Object in = message;
                if (in instanceof byte[]) {
                    // byte arrays is not readable so convert to string
                    in = getEndpoint().getCamelContext().getTypeConverter().convertTo(String.class, in);
                }
                logger.info("Received body:" +  in);
                
                Exchange exchange = getEndpoint().createExchange();
                exchange.getIn().setBody(message);
                getProcessor().process(exchange);
		}
		 
	 }

}
