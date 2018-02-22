/*
 *  TeleStax, Open Source Cloud Communications
 *  Copyright 2011-2018, Telestax Inc and individual contributors
 *  by the @authors tag.
 *
 *  This is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU Lesser General Public License as
 *  published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this software; if not, write to the Free
 *  Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.restcomm.media.server.standalone.bootstrap.ioc.spring;

import org.restcomm.media.resource.player.audio.CachedRemoteStreamProvider;
import org.restcomm.media.resource.player.audio.DirectRemoteStreamProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Henrique Rosa (henrique.rosa@telestax.com) created on 22/02/2018
 */
@Configuration
public class SpringMediaConfiguration {

    @Bean
    @ConditionalOnProperty(name = "mediaserver.resources.player.cache.enabled", havingValue = "true")
    public CachedRemoteStreamProvider cachedRemoteStreamProvider(@Value("${mediaserver.resources.player.connectionTimeout}") int connectionTimeout, @Value("${mediaserver.resources.player.cache.size}") int cacheSize) {
        return new CachedRemoteStreamProvider(cacheSize, connectionTimeout);
    }

    @Bean
    @ConditionalOnProperty(name = "mediaserver.resources.player.cache.enabled", havingValue = "false")
    public DirectRemoteStreamProvider directRemoteStreamProvider(@Value("${mediaserver.resources.player.connectionTimeout}") int connectionTimeout) {
        return new DirectRemoteStreamProvider(connectionTimeout);
    }

}