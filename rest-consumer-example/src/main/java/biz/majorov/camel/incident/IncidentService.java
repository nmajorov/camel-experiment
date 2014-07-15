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
package biz.majorov.camel.incident;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface with the services we want to expose as web services using code first.
 * <p/>
 * This is a basic example, you can use the JAX-WS annotations to control the contract.
 */

@Path("/incidentservice/")
public class IncidentService {
     private static final Logger LOG = LoggerFactory.getLogger(IncidentService.class);
      long LOWER_RANGE = 0; //assign lower range value
 	 long UPPER_RANGE = 1000000; //assign upper range value
 	 Random random = new Random();

 	Map<Long, String> storage = new HashMap<Long, String>();

     public IncidentService(){}
    /**
     * Operation to report an incident
     */
    @POST
    @Path("/reportincident/{description}/")
    @Consumes({"text/plain,text/html"})
    public Response reportIncident(@PathParam("description") String description){
        LOG.info("reportIncident : " + description);
        long randomValue = LOWER_RANGE + 
                           (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
        storage.put(randomValue, description);
        return Response.ok().build();
    }

    /**
     * Operation to get the status of an incident
     */
    @GET
    @Path("/incident/{id}/")
    @Produces("text/plain")
    public String statusIncident(@PathParam("id") String id){
        LOG.info("get incident by: " + id);
        String r;
        String descr = storage.get(id);
        
        if (descr == null){
        	r =  "Not Found";
        }else{
        	r =descr;
        }
        return r;
    }
}
