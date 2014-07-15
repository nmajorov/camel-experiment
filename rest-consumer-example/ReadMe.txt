Camel Router Project for Apache CXF code-first using Blueprint (OSGi)
=========================================================================

To build this project use

    mvn install

To deploy the project in OSGi. For example using Apache ServiceMix
or Apache Karaf. You need to install the following features first:

    features:install camel-jaxb
    features:install camel-cxf

You don't need to do this step for JBoss Fuse 6.x-    


And then you can install this example from its shell:

    osgi:install -s mvn:biz.majorov.camel/rest-consumer-example/1.0.0-SNAPSHOT

The web services from Apache CXF is usually listed at:

    http://localhost:8181/cxf

And the WADL file for this example at:

   http://localhost:8181/cxf/inc/?_wadl


