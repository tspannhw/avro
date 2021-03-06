/*
 * Copyright 2014 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.avro.joda;

import java.util.Collections;
import org.apache.avro.AbstractLogicalType;
import org.apache.avro.Schema;
import org.joda.time.Interval;
import org.joda.time.chrono.ISOChronology;

public class IsoInterval extends AbstractLogicalType<Interval> {

  public IsoInterval(Schema.Type type) {
    super(type, Collections.EMPTY_SET, "isointerval", Collections.EMPTY_MAP, Interval.class);
    // validate the type
    if (type != Schema.Type.STRING) {
      throw new IllegalArgumentException(
              "Logical type " + this + " must be backed by long or string");
    }
  }

  @Override
  public Interval deserialize(Object object) {
    return Interval.parseWithOffset(object.toString()).withChronology(ISOChronology.getInstanceUTC());
  }

  @Override
  public Object serialize(Interval object) {
    return object.withChronology(ISOChronology.getInstanceUTC()).toString();
  }

}
