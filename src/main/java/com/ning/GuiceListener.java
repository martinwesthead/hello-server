/*
 * Copyright 2010-2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.ning.jetty.base.modules.ServerModuleBuilder;
import com.ning.jetty.core.listeners.SetupServer;

public class GuiceListener extends SetupServer {
    Logger logger = LoggerFactory.getLogger(GuiceListener.class);
    
    protected Module getModule() {
        return new SimpleServerModule();
    }

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        final ServerModuleBuilder builder = new ServerModuleBuilder()
                .addModule(getModule())
                .addJerseyResource("com.ning.resources");


        guiceModule = builder.build();

        super.contextInitialized(event);

        logger.info("GuiceListener : contextInitialized");
        /*
                ObjectMapper mapper = theInjector.getInstance(ObjectMapper.class);
                mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());
        */

       }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        super.contextDestroyed(sce);

        logger.info("GuiceListener : contextDestroyed");
        // Stop services
        // Guice error, no need to fill the screen with useless stack traces
     }
}
