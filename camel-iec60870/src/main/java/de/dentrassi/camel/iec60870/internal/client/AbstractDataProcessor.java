/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *     Red Hat Inc - refactoring
 *******************************************************************************/
package de.dentrassi.camel.iec60870.internal.client;

import org.eclipse.neoscada.protocol.iec60870.asdu.message.DoublePointInformationSequence;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.DoublePointInformationSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.DoublePointInformationTimeSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueScaledSequence;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueScaledSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueScaledTimeSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueShortFloatingPointSequence;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueShortFloatingPointSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueShortFloatingPointTimeSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.SinglePointInformationSequence;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.SinglePointInformationSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.SinglePointInformationTimeSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.ASDUAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.DoublePoint;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationEntry;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationObjectAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.Value;
import org.eclipse.neoscada.protocol.iec60870.client.data.DataHandler;

public abstract class AbstractDataProcessor implements DataHandler {
	protected abstract void fireEntry(final ASDUAddress asduAddress, final InformationObjectAddress address,
			final Value<?> value);

	@Override
	public void process(final SinglePointInformationTimeSingle msg) {
		for (final InformationEntry<Boolean> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final SinglePointInformationSingle msg) {
		for (final InformationEntry<Boolean> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final SinglePointInformationSequence msg) {
		int i = msg.getStartAddress().getAddress();

		for (final Value<Boolean> value : msg.getValues()) {
			fireEntry(msg.getHeader().getAsduAddress(), InformationObjectAddress.valueOf(i), value);
			i++;
		}
	}

	@Override
	public void process(final DoublePointInformationTimeSingle msg) {
		for (final InformationEntry<DoublePoint> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final DoublePointInformationSingle msg) {
		for (final InformationEntry<DoublePoint> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final DoublePointInformationSequence msg) {
		int i = msg.getStartAddress().getAddress();

		for (final Value<DoublePoint> value : msg.getValues()) {
			fireEntry(msg.getHeader().getAsduAddress(), InformationObjectAddress.valueOf(i), value);
			i++;
		}
	}

	@Override
	public void process(final MeasuredValueShortFloatingPointTimeSingle msg) {
		for (final InformationEntry<Float> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final MeasuredValueShortFloatingPointSingle msg) {
		for (final InformationEntry<Float> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final MeasuredValueShortFloatingPointSequence msg) {
		int i = msg.getStartAddress().getAddress();

		for (final Value<Float> value : msg.getValues()) {
			fireEntry(msg.getHeader().getAsduAddress(), InformationObjectAddress.valueOf(i), value);
			i++;
		}
	}

	@Override
	public void process(final MeasuredValueScaledTimeSingle msg) {
		for (final InformationEntry<Short> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final MeasuredValueScaledSingle msg) {
		for (final InformationEntry<Short> entry : msg.getEntries()) {
			fireEntry(msg.getHeader().getAsduAddress(), entry.getAddress(), entry.getValue());
		}
	}

	@Override
	public void process(final MeasuredValueScaledSequence msg) {
		int i = msg.getStartAddress().getAddress();

		for (final Value<Short> value : msg.getValues()) {
			fireEntry(msg.getHeader().getAsduAddress(), InformationObjectAddress.valueOf(i), value);
			i++;
		}
	}

}
