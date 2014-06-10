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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a my endpoint.
 */
public class NMEndpoint extends DefaultEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(NMEndpoint.class);


    public NMEndpoint() {
    }

    public NMEndpoint(String uri, NMComponent component) {
    	
        super(uri, component);
        
        LOG.info("endpoint created");
    }

    public Producer createProducer() throws Exception {
    	LOG.info("creating Producer");
        return new NMProducer(this);
    }

    public Consumer createConsumer(Processor processor,String uri) throws Exception {
    	LOG.info("creating consumer with uri");
        return new NMConsumer(this, processor, uri);
    }

    public boolean isSingleton() {
        return true;
    }

	public Consumer createConsumer(Processor processor) throws Exception {
		LOG.info("creating consumer");
		return new NMConsumer(this, processor);
	}
}
