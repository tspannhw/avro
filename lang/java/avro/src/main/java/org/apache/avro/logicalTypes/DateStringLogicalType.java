/*
 * Copyright 2016 The Apache Software Foundation.
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
package org.apache.avro.logicalTypes;

import java.time.LocalDate;
import java.util.Collections;
import org.apache.avro.AbstractLogicalType;
import org.apache.avro.Schema;

/**
 * Date representation as String with format "YYYY-mm-dd".
 */
public final class DateStringLogicalType extends AbstractLogicalType<LocalDate> {

  DateStringLogicalType(Schema schema) {
    super(schema.getType(), Collections.EMPTY_SET, "date",
            Collections.EMPTY_MAP, LocalDate.class);
  }

  @Override
  public LocalDate deserialize(Object object) {
    CharSequence val = ((CharSequence) object);
    return LocalDate.parse(val);
  }

  @Override
  public Object serialize(LocalDate temporal) {
    return  temporal.toString();
  }

}
