/*
 * Copyright (C) 2016 Jens Reimann <jreimann@redhat.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.dentrassi.camel.iec60870.server;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.Value;

import de.dentrassi.camel.iec60870.internal.server.ServerInstance;

public class ServerProducer extends DefaultProducer {

	private final ServerEndpoint endpoint;
	private final ServerInstance server;

	public ServerProducer(final ServerEndpoint endpoint, final ServerInstance server) {
		super(endpoint);
		this.endpoint = endpoint;
		this.server = server;
	}

	@Override
	public void process(final Exchange exchange) throws Exception {
		final Value<?> value = mapToCommand(exchange);
		this.server.notifyValue(this.endpoint.getAddress(), value);
	}

	private Value<?> mapToCommand(final Exchange exchange) {
		final Object body = exchange.getIn().getBody();

		if (body instanceof Value<?>) {
			return (Value<?>) body;
		}

		if (body instanceof Float || body instanceof Double) {
			return Value.ok(((Number) body).floatValue());
		}

		if (body instanceof Boolean) {
			return Value.ok((Boolean) body);
		}

		if (body instanceof Short || body instanceof Byte || body instanceof Integer || body instanceof Long) {
			return convertToShort(((Number) body).longValue());
		}

		throw new IllegalArgumentException("Unable to map body to a value: " + body);
	}

	private Value<?> convertToShort(final long value) {
		if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
			throw new IllegalArgumentException(
					String.format("Value must be between %s and %s", Short.MIN_VALUE, Short.MAX_VALUE));
		}
		return Value.ok((short) value);
	}
}
